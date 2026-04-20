#!/usr/bin/env python3
"""
Search for known patterns in the raw audio data
Look for: flag{, CTF{, text patterns, file signatures, etc.
"""
import scipy.io.wavfile as wavfile
import numpy as np

def search_raw_data():
    """Search for text patterns in raw audio"""
    
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    # Get the raw bytes
    y_bytes = y.tobytes()
    
    print(f"Total audio bytes: {len(y_bytes)}")
    
    # Search for common patterns
    patterns = [
        b'flag{',
        b'FLAG{',
        b'CTF{',
        b'ctf{',
        b'FLAG',
        b'flag',
        b'The flag',
        b'The answer',
        b'echo',
        b'ECHO',
        b'bifrost',
        b'BIFROST',
    ]
    
    print("Searching for patterns in raw data:")
    for pattern in patterns:
        positions = []
        start = 0
        while True:
            pos = y_bytes.find(pattern, start)
            if pos == -1:
                break
            positions.append(pos)
            start = pos + 1
        
        if positions:
            print(f"  Found '{pattern.decode('latin1')}' at positions: {positions[:5]}")
            # Show context
            for pos in positions[:2]:
                start_ctx = max(0, pos - 20)
                end_ctx = min(len(y_bytes), pos + len(pattern) + 50)
                context = y_bytes[start_ctx:end_ctx]
                try:
                    print(f"    Context: {context}")
                except:
                    print(f"    Context: [binary data]")
    
    # Also look for null-terminated strings
    print("\nSearching for null-terminated strings of reasonable length...")
    strings = []
    current_str = b''
    for byte in y_bytes:
        if 32 <= byte <= 126:  # Printable
            current_str += bytes([byte])
        else:
            if len(current_str) >= 10:  # At least 10 characters
                strings.append(current_str)
            current_str = b''
    
    print(f"Found {len(strings)} potential strings")
    for s in strings[:20]:
        try:
            decoded = s.decode('ascii')
            print(f"  '{decoded}'")
        except:
            pass

def analyze_as_16bit():
    """Analyze 16-bit values"""
    audio_path = 'Echoes_in_the_Aether.wav'
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y_int = np.int16(y)
    
    print("\n=== 16-bit interpretation ===")
    
    # Look for patterns in 16-bit values that might encode text
    # Try taking every Nth 16-bit value as ASCII
    for stride in [1, 2, 4, 8, 16]:
        ascii_chars = []
        for i in range(0, min(10000, len(y_int)), stride):
            val = y_int[i]
            # Try as unsigned
            val_unsigned = val & 0xFF
            if 32 <= val_unsigned <= 126:
                ascii_chars.append(chr(val_unsigned))
            # Try as signed wrapped
            elif 32 <= (val_unsigned + 128) % 256 <= 126:
                ascii_chars.append(chr((val_unsigned + 128) % 256))
            else:
                if ascii_chars and len(ascii_chars) > 5:
                    text = ''.join(ascii_chars)
                    if any(c not in '.' for c in text[:10]):
                        print(f"Stride {stride}: {text[:50]}")
                ascii_chars = []

def check_stft_phases():
    """Check STFT phase for encoding"""
    from scipy import signal
    import scipy.io.wavfile as wavfile
    
    audio_path = 'Echoes_in_the_Aether.wav'  
    sr, y = wavfile.read(audio_path)
    
    if len(y.shape) > 1:
        y = y[:, 0]
    
    y = np.float32(y) / np.max(np.abs(y))
    
    print("\n=== STFT Phase Analysis ===")
    
    # Get STFT
    f, t, Zxx = signal.stft(y, sr, nperseg=2048)
    
    # Extract phase
    phase = np.angle(Zxx)
    
    # Look for patterns in phase
    # Quantize phase to bits
    phase_bits = []
    for row in phase:
        for val in row:
            # 0-2pi -> 0-1
            bit = int((val % (2*np.pi)) / np.pi)
            phase_bits.append(bit)
    
    phase_bits = np.array(phase_bits)
    print(f"Phase bits length: {len(phase_bits)}")
    print(f"First 500 bits: {''.join(str(b) for b in phase_bits[:500])}")
    
    # Try to decode as ASCII
    for chunk_size in [7, 8]:
        chars = []
        for i in range(0, len(phase_bits) - chunk_size, chunk_size):
            val = int(''.join(str(phase_bits[i+j]) for j in range(chunk_size)), 2)
            if 32 <= val <= 126:
                chars.append(chr(val))
            else:
                chars.append('.')
        
        text = ''.join(chars[:200])
        if '.' not in text[:50]:
            print(f"Decoded from phase ({chunk_size}-bit): {text}")

if __name__ == '__main__':
    search_raw_data()
    analyze_as_16bit()
    check_stft_phases()
