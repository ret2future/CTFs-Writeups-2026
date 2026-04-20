# Gap Analysis: Missing EFTA Serial Numbers

## Summary

Of the 2,731,812 EFTA serial numbers in the DOJ's tracking range, only **633,842 files (23.2%)** are publicly available. An estimated **2,097,970 EFTA serial numbers (76.8%)** have no corresponding public file.

The DOJ has stated it collected approximately 6 million pages and released approximately 3.5 million. It has not published a serial-level accounting of which EFTA numbers were withheld or why.

## Dataset-Level Breakdown

| Dataset | Serial Range | Files Present | Serial Span | Missing Serials | % Missing |
|---------|-------------|---------------|-------------|-----------------|-----------|
| DS1 | 1 – 3,158 | 3,099 | 3,158 | 59 | 1.9% |
| DS2 | 3,159 – 3,857 | 574 | 699 | 125 | 17.9% |
| DS3 | 3,858 – 5,586 | 61 | 1,729 | 1,668 | 96.5% |
| DS4 | 5,705 – 8,320 | 152 | 2,616 | 2,464 | 94.2% |
| DS5 | 8,409 – 8,528 | 119 | 120 | 1 | 0.8% |
| DS6 | 8,585 – 8,998 | 12 | 414 | 402 | 97.1% |
| DS7 | 9,016 – 9,664 | 17 | 649 | 632 | 97.4% |
| DS8 | 9,676 – 39,022 | 10,980 | 29,347 | 18,367 | 62.6% |
| DS9 | 39,025 – 1,262,781 | 533,110 | 1,223,757 | 690,647 | 56.4% |
| DS10 | 1,262,782 – 1,334,722 | 8,600 | 71,941 | 63,341 | 88.0% |
| DS11 | 2,205,655 – 2,349,420 | 76,969 | 143,766 | 66,797 | 46.5% |
| DS12 | 2,730,265 – 2,731,812 | 149 | 1,548 | 1,399 | 90.4% |
| **TOTAL** | **1 – 2,731,812** | **633,842** | **2,731,812** | **2,097,970** | **76.8%** |

## Key Observations

### Massive Gaps Between Datasets

There are two large serial gaps with no corresponding dataset:

- **Between DS10 and DS11**: Serials 1,334,723 to 2,205,654 — a gap of **870,932 serial numbers** with zero files. This is the single largest gap in the entire EFTA range.
- **Between DS11 and DS12**: Serials 2,349,421 to 2,730,264 — a gap of **380,844 serial numbers** with zero files.

Together these inter-dataset gaps account for **1,251,776 missing serial numbers** — more than half of all missing serials. The DOJ has not explained what these serial ranges represent.

### Dataset 9: The Core Battleground

DS9 contains the emails, private correspondence, and DOJ internal communications — the most substantive material for understanding Epstein's network. Of the 1,223,757 serial numbers in the DS9 range, 690,647 (56.4%) are missing. This is where the DOJ's stated withholding categories (CSAM, privilege, duplicates) would have the most impact, but it is also where selective withholding would be most consequential.

### Nearly Empty Datasets

Datasets 3, 4, 6, and 7 have over 94% of their serial ranges missing. These datasets span FBI interview summaries and police reports from Palm Beach (2005–2008) — the period covering the original investigation and the controversial non-prosecution agreement. DS6 has only 12 files released out of a 414-number range. DS7 has 17 files out of 649.

### Dataset 10: Images and Video

DS10 (images and videos seized from Epstein properties) has 88% of its serial range missing. The DOJ has stated it withheld images containing CSAM and redacted women other than Ghislaine Maxwell from released images. The scale of withholding — 63,341 missing serials — is notable.

## What These Gaps Could Represent

The DOJ has cited several categories for withheld material:

1. **Child sexual abuse material (CSAM)** — Required by law to be withheld
2. **Attorney-client privileged communications** — Legal protections
3. **Internal deliberative process documents** — DOJ internal discussions
4. **Duplicate documents between SDNY and SDFL investigations**
5. **Materials unrelated to the Epstein/Maxwell cases**
6. **Victim-identifying information requiring redaction review**

However, it is also possible that some serial numbers were never assigned to documents — the EFTA numbering system may contain administrative gaps, unused tracking numbers, or batch allocations that were never populated. Without a public serial-level manifest from the DOJ, it is impossible to distinguish withheld documents from unassigned serial numbers.

## What Would Resolve This

The Epstein Files Transparency Act requires the DOJ to explain its redactions to Congress. Representatives Ro Khanna and Thomas Massie have requested access to review unredacted files. A public accounting at the EFTA serial level — specifying which numbers correspond to withheld documents and which were never assigned — would allow the public to assess whether the DOJ has complied with the law.

This gap analysis is derived from the publicly available file index. Anyone can reproduce it from the data in this repository.

## How to Reproduce

```bash
# Decompress the index
gunzip -k epstein_efoia_index.csv.gz

# Load into any tool that reads CSV
# Serial column = EFTA serial number
# Dataset column = DOJ dataset number (1-12)

# Example: find gaps in DS9 serials
python3 -c "
import pandas as pd
df = pd.read_csv('epstein_efoia_index.csv')
ds9 = set(df[df['dataset']==9]['serial'].dropna().astype(int))
full_range = set(range(39025, 1262782))
missing = sorted(full_range - ds9)
print(f'DS9 missing serials: {len(missing):,}')
print(f'First 10 missing: {missing[:10]}')
"
```
