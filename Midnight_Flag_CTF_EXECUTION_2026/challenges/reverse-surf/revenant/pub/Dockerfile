FROM ubuntu:22.04

RUN apt-get update -q && \
    apt-get install -y --no-install-recommends socat && \
    rm -rf /var/lib/apt/lists/*

RUN useradd -m -s /bin/sh -u 1000 ctf
WORKDIR /home/ctf
COPY game /home/ctf/
RUN chmod +rx /home/ctf/game
RUN echo "MCTF{FAKE_FLAG}" > flag.txt && chmod 444 flag.txt
EXPOSE 1337

USER ctf

CMD ["socat", \
     "TCP-LISTEN:1337,reuseaddr,fork", \
     "EXEC:su ctf -c ./game,pty,stderr,echo=0,raw"]
