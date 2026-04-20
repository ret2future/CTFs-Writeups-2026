# Smart_Brick_v2

## Challenge Summary
- Given: `smart-brick-v2.kicad_pcb` — KiCad PCB design file for a "Smart Brick v2" LEGO controller
- Goal: Determine what the circuit does / what flag it outputs
- Hints: eCAD = KiCad; 7 inputs → 7-bit ASCII; use `kiutils` Python library

## Initial Recon / Triage
- PCB has 112 footprints: 74LS-series TTL logic ICs, 2N7002 MOSFETs, LEDs, resistors
- Connector J1 (8-pin): pins 1-7 = /IN0–/IN6 (inputs), pin 8 = GND
- Connector J2 (2-pin): +5V / GND (power)
- 19 transistors (Q1–Q19), each with gate driven by a logic sub-circuit
- Each transistor gate net is driven to HIGH by exactly one 7-bit input value

## Hypotheses & Approach
- The circuit is a combinational decoder: each 7-bit input value (0–127) activates exactly one transistor
- Each transistor corresponds to one character (7-bit = ASCII)
- Reading Q1→Q19 output characters in order spells the flag

## Execution Steps (Reproducible)

### Stage 1 — Parse PCB netlist with kiutils
```python
# artifacts/netlist.py
# Loads the .kicad_pcb, iterates all footprints, maps (ref, pad) -> net_name
# Prints connector pin assignments and all signal nets
```
Key finding: J1 pins 1-7 → /IN0–/IN6; 19 transistors Q1-Q19 with gate nets /G59, /G62, /G13, ...

### Stage 2 — Simulate all 128 input combinations
```python
# artifacts/simulate.py
# Builds gate list from IC footprints using 74LS pin definitions
# Iterative evaluation (30 passes) for combinational convergence
# For each Q, finds which input value v ∈ [0,127] drives its gate HIGH
```

Gate types used: 74LS04 (NOT), 74LS00 (NAND), 74LS02 (NOR), 74LS08 (AND),
74LS32 (OR), 74LS86 (XOR), 74LS21 (4-AND), 74LS20 (4-NAND), 74LS27 (3-NOR)

Result: every transistor activates for exactly one input value:
```
Q1=85('U') Q2=77('M') Q3=65('A') Q4=83('S') Q5=83('S') Q6=123('{')
Q7=73('I') Q8=110('n') Q9=95('_') Q10=84('T') Q11=104('h') Q12=51('3')
Q13=95('_') Q14=71('G') Q15=52('4') Q16=116('t') Q17=51('3') Q18=115('s') Q19=125('}')
```

## Artifacts Produced
- `artifacts/netlist.py` — kiutils-based netlist extractor
- `artifacts/netlist.txt` — full signal net dump
- `artifacts/simulate.py` — combinational circuit simulator; outputs flag
- `starting_files/smart-brick-v2.kicad_pcb` — original PCB file

## Flag
```
UMASS{In_Th3_G4t3s}
```
