# Methodology

## Overview

This document describes the technical pipeline used to build the Epstein EFTA Complete File Index. The goal was to create a verified, structured metadata catalog of every publicly released file from the DOJ Epstein Library — without reproducing any document content or exposing victim-identifying information.

## Pipeline Architecture

The pipeline has six stages, executed sequentially against a local SQLite database (`epstein_files.db`).

### Stage 1: URL Inventory

All 12 DOJ datasets were scraped from the Epstein Library at justice.gov/epstein. Each file's URL was recorded with a UNIQUE constraint, ensuring no duplicate entries. Metadata extracted at this stage included dataset number, EFTA prefix, serial number, and file extension.

**Result**: 633,842 unique URLs cataloged across 12 datasets.

### Stage 2: Download and Verification

Every cataloged file was downloaded from DOJ-hosted URLs and publicly available torrent mirrors. File sizes were recorded on disk. The torrent source provides archival integrity — it preserves files that the DOJ later removed or re-uploaded with different redactions.

**Result**: 633,842 files downloaded, local paths and file sizes recorded.

### Stage 3: File Type Verification

Each file was verified using magic byte detection — reading the first bytes of the file to determine actual file type, independent of the file extension. This catches cases where a file labeled `.pdf` is actually an image, video, or corrupted file.

**Result**: Actual file types recorded for all files. Mismatches flagged via the `filetype_mismatch` column.

### Stage 4: Text Extraction

PDF text was extracted using PyMuPDF (fitz). Each page was classified into one of four categories:

| Classification | Method | Description |
|---------------|--------|-------------|
| `digital_text` | PyMuPDF direct extraction | Clean digital text layer present |
| `photo` | Flagged for future OCR | Page contains image content without text layer |
| `blank` | Detected as empty | Page has no meaningful content |
| `unknown` | PyMuPDF fallback | Text extraction returned minimal content |

Photo pages were flagged but not discarded. They are preserved in the database for future OCR processing.

**Extraction statistics (final run)**:
- Files processed: 633,842
- Errors: 3 (99.998% success rate)
- Digital text pages: 242,078+ (88% of classified pages)
- Photo pages: 10,170+ (flagged, not lost)
- Blank pages: 9,125+

### Stage 5: Document Classification

An automated classifier assigned each file a document type based on extracted text content. Classification used keyword patterns, structural markers (headers, formatting), and content heuristics specific to legal and financial document types.

| Document Type | Detection Method |
|--------------|-----------------|
| `email` | To/From/Subject headers, email formatting patterns |
| `financial` | Account numbers, transaction formats, bank headers, wire transfer patterns |
| `court_filing` | Case numbers, court headers, legal formatting |
| `flight_log` | Aircraft identifiers, passenger manifest formats, route data |
| `phone_record` | Call log formats, phone number patterns |
| `deposition` | Q&A formatting, sworn testimony markers |
| `subpoena` | Subpoena headers, legal demand language |
| `police_report` | Law enforcement headers, incident report formatting |
| `medical` | Medical terminology density, clinical formatting |
| `fax` | Fax headers, transmission metadata |
| `letter` | Salutation/closing patterns, correspondence formatting |
| `document` | General document not matching specific types |
| `unknown` | Insufficient text or ambiguous content |

### Stage 6: Date Extraction

Dates were extracted from document text using pattern matching for common date formats found in legal, financial, and correspondence documents. For each file, the earliest and latest dates found were recorded.

**Result**: 362,061 files (57%) have at least one extracted date.

## Database Schema

```sql
CREATE TABLE files (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dataset INTEGER NOT NULL,
    title TEXT,
    url TEXT UNIQUE NOT NULL,
    prefix TEXT,
    serial INTEGER,
    serial_block INTEGER,
    file_ext TEXT,
    downloaded INTEGER DEFAULT 0,
    local_path TEXT,
    file_size INTEGER,
    text_extracted INTEGER DEFAULT 0,
    extraction_method TEXT,
    page_count INTEGER,
    meta_created TEXT,
    meta_modified TEXT,
    meta_author TEXT,
    meta_producer TEXT,
    meta_creator TEXT,
    doc_date_earliest TEXT,
    doc_date_latest TEXT,
    doc_type TEXT,
    summary TEXT,
    actual_filetype TEXT,
    filetype_mismatch INTEGER DEFAULT 0,
    media_duration REAL,
    media_width INTEGER,
    media_height INTEGER,
    media_codec TEXT,
    media_info TEXT
);
```

The published CSV exports a subset of these columns, excluding `summary`, `local_path`, extracted text, and PDF metadata fields that could contain victim-identifying information.

## Data Quality

**Deduplication verification**:
- 633,842 total rows = 633,842 unique URLs (UNIQUE constraint verified)
- Zero duplicate titles within any dataset
- File size overlaps at small byte counts (e.g., 4,977 files at 2,433 bytes) are consistent with single-page redaction notices or DOJ placeholder pages, not content duplicates

**Serial range analysis**:
- EFTA serial range: 1 to 2,731,812
- 633,842 files present out of 2,731,812 possible serial numbers
- Gaps are consistent with DOJ-stated withholding categories (CSAM, attorney-client privilege, duplicates, unrelated material, internal deliberative material)

## Tools and Dependencies

- **Python 3.x**: Pipeline orchestration
- **SQLite**: Database storage and querying
- **PyMuPDF (fitz)**: PDF text extraction and page classification
- **python-magic / file signatures**: File type verification via magic bytes

## Reproducibility

The published CSV can be independently verified by downloading files from the DOJ URLs listed in each row. The URL column provides direct links to the source documents on justice.gov. Note that some files may no longer be accessible if the DOJ has removed them since the torrent snapshot.

## Ethical Considerations

This index was designed to maximize public utility while minimizing harm:

1. **No extracted text is published.** The DOJ's own redaction process has been documented as inconsistent — victim names appearing unredacted, perpetrator names hidden. Publishing extracted text would propagate these failures. The metadata-only approach avoids this entirely.

2. **No summaries are published.** Document summaries generated during processing may reference specific individuals and are excluded from the public export.

3. **Document classification enables targeted research.** By labeling 10,301 financial records, 1,583 flight logs, and 1,980 phone records, researchers can go directly to the document types most relevant to their investigation without wading through 633,842 unsorted files.

4. **Torrent sourcing is documented.** The archival snapshot preserves what was publicly available at a specific point in time, including files the DOJ later removed. This is stated clearly rather than obscured.
