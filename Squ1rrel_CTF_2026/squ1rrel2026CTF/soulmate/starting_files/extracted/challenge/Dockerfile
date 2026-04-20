FROM python:3.11-slim-bookworm

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y --no-install-recommends \
    git \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Reduce OpenMP/BLAS threading (avoids many segfaults in PyTorch+StyleGAN on CPU in Docker)
ENV OMP_NUM_THREADS=1
ENV MKL_NUM_THREADS=1
ENV OPENBLAS_NUM_THREADS=1
ENV NUMEXPR_NUM_THREADS=1

COPY requirements.txt .
RUN pip install --no-cache-dir --upgrade pip && \
    pip install --no-cache-dir \
      torch==2.1.0 torchvision==0.16.0 \
      --index-url https://download.pytorch.org/whl/cpu && \
    pip install --no-cache-dir -r requirements.txt

COPY vendor/ vendor/
COPY models/ models/
COPY backend/ backend/
COPY frontend/ frontend/
COPY checkpoints/ checkpoints/

# Checkpoints and flag are mounted at runtime (see parent README); keep empty dir for PCA path

ENV PCA_BASIS_PATH=/app/checkpoints/pca_basis_d8_tom_weighted.npz

EXPOSE 8000

CMD ["uvicorn", "backend.app:app", "--host", "0.0.0.0", "--port", "8000"]
