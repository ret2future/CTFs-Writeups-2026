FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends \
        python3 \
        python3-pip \
        git \
        libgl1 \
        libglib2.0-0 \
        procps \
        curl \
        ca-certificates \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir --break-system-packages \
        --extra-index-url https://download.pytorch.org/whl/cpu \
        -r requirements.txt

RUN git clone --depth=1 https://github.com/WongKinYiu/yolov7.git /app/yolov7 \
    && rm -rf /app/yolov7/.git

RUN curl -sSL -o /app/yolov7-tiny.pt \
    https://github.com/WongKinYiu/yolov7/releases/download/v0.1/yolov7-tiny.pt

COPY server.py      /app/server.py
COPY templates/     /app/templates/
COPY static/        /app/static/

COPY yolo_status /app/yolo_status
COPY flag.txt /flag.txt

RUN groupadd -r ctf && useradd  -r -g ctf -u 1000 -M -s /usr/sbin/nologin ctf \
 && chown root:root /flag.txt       && chmod 0400 /flag.txt \
 && chown root:root /app/yolo_status && chmod 4755 /app/yolo_status \
 && chown -R ctf:ctf /app/yolov7 /tmp

EXPOSE 5000

USER ctf
WORKDIR /app

CMD ["gunicorn", "-b", "0.0.0.0:5000", \
     "--workers", "1", "--threads", "4", \
     "--timeout", "120", \
     "--chdir", "/app", \
     "server:app"]
