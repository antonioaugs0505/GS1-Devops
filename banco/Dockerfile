FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=rootpassword 
ENV MYSQL_DATABASE=minha_api_db
ENV MYSQL_USER=dbuser
ENV MYSQL_ROOT_PASSWORD=dbpassword

COPY Cp1_2K24.sql /docker-entrypoint-initdb.d/

EXPOSE 3306