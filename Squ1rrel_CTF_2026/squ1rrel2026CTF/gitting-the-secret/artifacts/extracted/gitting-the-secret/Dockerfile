FROM httpd:2.4

COPY apache/000-default.conf /usr/local/apache2/conf/extra/ctf.conf
RUN echo "Include /usr/local/apache2/conf/extra/ctf.conf" \
    >> /usr/local/apache2/conf/httpd.conf

COPY . /var/www/html/