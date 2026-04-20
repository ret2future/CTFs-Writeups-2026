# Epstein EFTA Complete File Index

A structured, searchable metadata index of **633,842 unique files** released by the U.S. Department of Justice under the [Epstein Files Transparency Act](https://www.congress.gov/bill/118th-congress/house-bill/9326) (Public Law 118-299), signed November 19, 2025.

This is the most comprehensive single-source file index of the DOJ Epstein Library publicly available. Every file has been downloaded, verified, and classified by document type with date extraction where possible.

## Why This Exists

The DOJ released 3.5 million pages across 12 datasets with no organization, no search, and no context. Files were added and removed without explanation. Victim names were left unredacted while perpetrator names were hidden. News organizations with teams of 500 attorneys couldn't process it all.

This index makes every released file findable, sortable, and queryable — so journalists, researchers, survivor advocates, and the public can actually use what was released in their name.

This work is for the girls.

## What's Included

**`epstein_efoia_index.csv.gz`** — Compressed CSV (≈12 MB, ≈84 MB uncompressed) containing metadata for all 633,842 files.

| Column | Description |
|--------|-------------|
| `id` | Sequential row identifier |
| `dataset` | DOJ dataset number (1–12) |
| `title` | Original filename from DOJ (e.g., `EFTA00000001.pdf`) |
| `url` | Direct URL to file on justice.gov |
| `prefix` | File prefix (typically `EFTA`) |
| `serial` | EFTA serial number extracted from filename |
| `file_ext` | File extension as listed |
| `file_size` | File size in bytes |
| `page_count` | Number of pages (PDFs) |
| `doc_type` | Automated document classification |
| `doc_date_earliest` | Earliest date found in document |
| `doc_date_latest` | Latest date found in document |
| `actual_filetype` | Verified file type via magic byte detection |
| `filetype_mismatch` | Flag if extension doesn't match actual content |

### What's NOT Included

No extracted text. No summaries. No victim-identifying information. This is metadata only — a card catalog, not the library itself. The source documents remain on [justice.gov/epstein](https://www.justice.gov/epstein).

## Corpus Statistics

| Metric | Count |
|--------|-------|
| Total unique files | 633,842 |
| Unique URLs verified | 633,842 (URL UNIQUE constraint, zero duplicates) |
| EFTA serial range | 1 – 2,731,812 |
| Files with extracted dates | 362,061 (57%) |
| Total pages (PDFs) | 2.7M+ |

### Dataset Distribution

| Dataset | Files | Description |
|---------|-------|-------------|
| DS9 | 533,110 | Email evidence, private correspondence, DOJ internal communications |
| DS11 | 76,969 | Financial ledgers, flight manifests, property seizure records |
| DS8 | 10,980 | FBI interview summaries, media files, surveillance footage |
| DS10 | 8,600 | Images and videos seized from Epstein properties |
| DS1 | 3,099 | FBI summaries and police reports (Palm Beach 2005–2008) |
| DS2 | 574 | FBI summaries and police reports |
| DS4 | 152 | FBI summaries and police reports |
| DS12 | 149 | Late productions and supplemental items |
| DS5 | 119 | FBI summaries and police reports |
| DS3 | 61 | FBI summaries and police reports |
| DS7 | 17 | FBI summaries and police reports |
| DS6 | 12 | FBI summaries and police reports |

### Document Type Classification

| Type | Count | Significance |
|------|-------|-------------|
| email | 501,024 | Connective tissue — who contacted whom, when |
| document | 59,989 | General documents, memos, reports |
| unknown | 44,550 | Unclassified (often heavily redacted) |
| financial | 10,301 | Bank statements, wire transfers, ledgers |
| court_filing | 6,522 | Legal proceedings, motions, orders |
| letter | 5,055 | Correspondence |
| phone_record | 1,980 | Call logs, phone metadata |
| flight_log | 1,583 | Aircraft movements, passenger manifests |
| subpoena | 1,019 | Legal demands for testimony or documents |
| fax | 703 | Fax transmissions |
| medical | 607 | Medical records |
| deposition | 320 | Sworn testimony transcripts |
| police_report | 107 | Law enforcement reports |

## Data Source and Methodology

All files were obtained from the DOJ Epstein Library at [justice.gov/epstein](https://www.justice.gov/epstein) via publicly available torrent mirrors of the official release. The torrent snapshot preserves the state of the release at download time, which is significant because the DOJ has added and removed files without public notice on multiple occasions.

The pipeline:

1. **Scraping and cataloging**: All 12 DOJ datasets were scraped to build a complete URL inventory. Each file was assigned a unique database record keyed by URL.
2. **Downloading**: Every cataloged file was downloaded and stored locally. File sizes were recorded.
3. **File type verification**: Each file was verified using magic byte detection (file signature analysis), independent of the file extension. Mismatches were flagged.
4. **Text extraction**: PDF text was extracted using PyMuPDF. Pages were classified as digital text, photo, blank, or unknown. Photo pages were flagged for future OCR but not discarded.
5. **Document classification**: An automated classifier categorized each file by document type (email, financial, court filing, flight log, etc.) based on extracted text content and structural patterns.
6. **Date extraction**: Dates were extracted from document text where present, recording the earliest and latest dates found in each file.

**Extraction success rate**: 99.998% (3 errors out of 633,842 files).

See [METHODOLOGY.md](METHODOLOGY.md) for full technical details.

## Usage

```bash
# Decompress
gunzip -k epstein_efoia_index.csv.gz

# Load into SQLite for querying
sqlite3 epstein_index.db <<'EOF'
.mode csv
.import epstein_efoia_index.csv files
CREATE INDEX idx_dataset ON files(dataset);
CREATE INDEX idx_doc_type ON files(doc_type);
CREATE INDEX idx_serial ON files(serial);
EOF

# Find all financial documents
sqlite3 epstein_index.db "SELECT title, url, doc_date_earliest FROM files WHERE doc_type='financial' ORDER BY doc_date_earliest;"

# Find all flight logs
sqlite3 epstein_index.db "SELECT title, url FROM files WHERE doc_type='flight_log';"

# Count files by type in Dataset 11 (financial ledgers, flight manifests)
sqlite3 epstein_index.db "SELECT doc_type, COUNT(*) FROM files WHERE dataset=11 GROUP BY doc_type ORDER BY COUNT(*) DESC;"

# Find EFTA serial gaps (potentially removed or withheld files)
sqlite3 epstein_index.db "SELECT serial+1 as gap_start FROM files WHERE serial+1 NOT IN (SELECT serial FROM files) AND serial < (SELECT MAX(serial) FROM files) LIMIT 20;"
```

## Scope and Limitations

**What this index covers**: Every file publicly accessible on the DOJ Epstein Library as of the torrent snapshot date, across all 12 datasets. This includes the initial December 19, 2025 release, the major January 30, 2026 release of 3+ million pages, and subsequent additions.

**What this index does not cover**: The DOJ identified approximately 6 million total pages. Roughly half were withheld — child sexual abuse material, attorney-client privileged content, internal deliberative documents, duplicate materials, and unrelated files. This index covers only what was publicly released. Files removed by the DOJ after the torrent snapshot are preserved in the index but may no longer be accessible at the listed URL.

**On EFTA serial gaps**: The EFTA serial range extends to 2,731,812 but only 633,842 files are present — **2,097,970 serial numbers (76.8%) have no corresponding public file.** Two inter-dataset gaps alone account for 1.25 million missing serials. Datasets covering the original Palm Beach investigation (DS3, DS4, DS6, DS7) have over 94% of their serial ranges missing. See [GAP_ANALYSIS.md](GAP_ANALYSIS.md) for the full breakdown.

**Comparison with other efforts**: Other notable community projects include [rhowardstone/Epstein-research](https://github.com/rhowardstone/Epstein-research) (519,548 PDFs, 100+ forensic reports, entity extraction, redaction analysis), [yung-megafone/Epstein-Files](https://github.com/yung-megafone/Epstein-Files) (archive mirrors and integrity verification), and [epstein-docs](https://github.com/epstein-docs/epstein-docs.github.io) (OCR and entity extraction). This project contributes the largest single-source file count with structured metadata and document classification. Different projects have different strengths — use them together.

## License

This structured metadata index is released under [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/). The underlying documents are U.S. government works released under the Epstein Files Transparency Act.

If you use this index in research, journalism, or analysis, please cite it.

## Citation

```
Tweeder, R. (2026). Epstein EFTA Complete File Index: Structured metadata 
for 633,842 DOJ Epstein Library files. 
GitHub: https://github.com/randallscott25-star/epstein-EFTA-index
Archive: https://archive.org/details/epstein_efoia_index
```

## Contact

This is independent, pro bono work by an applied data scientist. For questions, corrections, or collaboration, open an issue on this repository.
