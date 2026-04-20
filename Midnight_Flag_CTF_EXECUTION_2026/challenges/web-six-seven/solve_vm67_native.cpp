#include <atomic>
#include <cstdint>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <string>
#include <thread>
#include <vector>

static inline uint32_t step(uint32_t x) {
    x ^= x << 13;
    x ^= x >> 17;
    x ^= x << 5;
    x *= 0x2545F491u;
    return x;
}

static inline uint32_t round_out(uint32_t seed) {
    uint32_t x = seed;
    for (int i = 0; i < 1000; i++) {
        x = step(x);
    }
    return x ^ seed;
}

static std::vector<uint8_t> read_all(const std::string& path) {
    std::ifstream f(path, std::ios::binary);
    if (!f) {
        throw std::runtime_error("cannot open file");
    }
    f.seekg(0, std::ios::end);
    std::streamsize n = f.tellg();
    f.seekg(0, std::ios::beg);
    std::vector<uint8_t> data(static_cast<size_t>(n));
    if (!f.read(reinterpret_cast<char*>(data.data()), n)) {
        throw std::runtime_error("cannot read file");
    }
    return data;
}

static std::vector<uint32_t> extract_targets(const std::vector<uint8_t>& data) {
    const size_t base = 0x3020;
    if (data.size() <= base) {
        throw std::runtime_error("binary too small");
    }

    std::vector<uint32_t> targets;
    size_t pc = base;

    while (pc < data.size()) {
        uint8_t d = data[pc] ^ 0x7A;
        if (d > 0x20) {
            break;
        }
        if (d <= 0x0F) {
            throw std::runtime_error("invalid opcode marker");
        }

        uint8_t op = static_cast<uint8_t>(d - 0x10);
        pc += 1;

        if (op == 16) {
            if (pc + 4 > data.size()) {
                throw std::runtime_error("truncated immediate");
            }
            uint32_t imm =
                static_cast<uint32_t>(data[pc + 0] ^ 0x7A) |
                (static_cast<uint32_t>(data[pc + 1] ^ 0x7A) << 8) |
                (static_cast<uint32_t>(data[pc + 2] ^ 0x7A) << 16) |
                (static_cast<uint32_t>(data[pc + 3] ^ 0x7A) << 24);
            targets.push_back(imm);
            pc += 4;
        }
    }

    return targets;
}

struct SolveResult {
    bool found{false};
    uint32_t block24{0};
};

static SolveResult solve_round_mt(uint32_t prev, uint32_t target, unsigned threads) {
    if (threads == 0) {
        threads = 1;
    }

    const uint32_t space = 1u << 24;
    const uint32_t chunk = space / threads;

    std::atomic<bool> done{false};
    std::atomic<uint32_t> best{0};

    std::vector<std::thread> pool;
    pool.reserve(threads);

    for (unsigned t = 0; t < threads; t++) {
        uint32_t start = t * chunk;
        uint32_t end = (t + 1 == threads) ? space : start + chunk;

        pool.emplace_back([=, &done, &best]() {
            for (uint32_t b = start; b < end; b++) {
                if (done.load(std::memory_order_relaxed)) {
                    return;
                }
                uint32_t seed = prev ^ b;
                if (round_out(seed) == target) {
                    best.store(b, std::memory_order_relaxed);
                    done.store(true, std::memory_order_relaxed);
                    return;
                }
            }
        });
    }

    for (auto& th : pool) {
        th.join();
    }

    SolveResult r;
    r.found = done.load(std::memory_order_relaxed);
    r.block24 = best.load(std::memory_order_relaxed);
    return r;
}

int main(int argc, char** argv) {
    try {
        std::string path = "67";
        unsigned threads = std::thread::hardware_concurrency();
        if (threads == 0) {
            threads = 8;
        }

        if (argc >= 2) {
            threads = static_cast<unsigned>(std::stoul(argv[1]));
            if (threads == 0) {
                threads = 1;
            }
        }

        auto blob = read_all(path);
        auto targets = extract_targets(blob);

        std::cerr << "targets=" << targets.size() << " threads=" << threads << "\n";

        std::vector<uint8_t> out;
        out.reserve(targets.size() * 3);

        uint32_t prev = 0xDEADBEEFu;

        for (size_t i = 0; i < targets.size(); i++) {
            SolveResult r = solve_round_mt(prev, targets[i], threads);
            if (!r.found) {
                std::cerr << "round " << i << " not solved\n";
                return 2;
            }

            uint8_t b0 = static_cast<uint8_t>((r.block24 >> 16) & 0xFF);
            uint8_t b1 = static_cast<uint8_t>((r.block24 >> 8) & 0xFF);
            uint8_t b2 = static_cast<uint8_t>(r.block24 & 0xFF);

            out.push_back(b0);
            out.push_back(b1);
            out.push_back(b2);

            prev = targets[i];

            if ((i + 1) % 16 == 0 || i + 1 == targets.size()) {
                std::cerr << "solved " << (i + 1) << "/" << targets.size() << "\n";
            }
        }

        std::ofstream fbin("recovered_flag.bin", std::ios::binary);
        fbin.write(reinterpret_cast<const char*>(out.data()), static_cast<std::streamsize>(out.size()));

        std::ofstream ftxt("recovered_flag.txt", std::ios::binary);
        ftxt.write(reinterpret_cast<const char*>(out.data()), static_cast<std::streamsize>(out.size()));

        std::cout << "len=" << out.size() << "\n";
        std::cout << "hex=";
        for (uint8_t c : out) {
            std::cout << std::hex << std::setw(2) << std::setfill('0') << static_cast<unsigned>(c);
        }
        std::cout << std::dec << "\n";

        std::cout << "ascii=";
        for (uint8_t c : out) {
            if (c >= 0x20 && c <= 0x7E) {
                std::cout << static_cast<char>(c);
            } else if (c == '\n' || c == '\r' || c == '\t') {
                std::cout << static_cast<char>(c);
            } else {
                std::cout << '.';
            }
        }
        std::cout << "\n";

        return 0;
    } catch (const std::exception& e) {
        std::cerr << "error: " << e.what() << "\n";
        return 1;
    }
}
