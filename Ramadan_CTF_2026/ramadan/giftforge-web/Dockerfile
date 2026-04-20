FROM python:3.9-slim

WORKDIR /app

COPY ./src/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY ./src .

EXPOSE 5000

ENV FLASK_APP=app.py
ENV FLAG=VBD{F4k3_fl4g_f0r_t3st1ng}

CMD ["python", "app.py"]
