# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app
RUN cd /app

# Copiar el archivo JAR del microservicio al contenedor
COPY target/account-movement-0.0.1-SNAPSHOT.jar account-movement-microservice.jar

# Exponer el puerto que usa el microservicio
EXPOSE 8081

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "account-movement-microservice.jar"]