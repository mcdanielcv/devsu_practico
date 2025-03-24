@echo off

REM Navegar al directorio client-person y ejecutar los comandos
cd client-person
mvn clean package -DskipTests
docker build -t client-person-microservice .

REM Navegar al directorio account-movement y ejecutar los comandos
cd ..\account-movement
mvn clean package -DskipTests
docker build -t account-movement-microservice .

REM Navegar al directorio ejercicio-practico y ejecutar docker-compose
cd ..\ejercicio-practico
docker-compose up --build -d