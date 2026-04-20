# Deep Down

Flag: UMASS{1N_A_G1774}

Technique: Palette index steganography. The GIF has two visually identical colors (index 1 and 3, both RGB 11,41,71). The flag text is written in the water area (y=54-66) using index-1 pixels, invisible visually but detectable via raw LZW decoding.
