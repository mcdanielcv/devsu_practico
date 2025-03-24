# Practico Devsu

## Ejecución Test

- Para ejecutar el test ClientPersonApplicationTests correctamente se debe tener una instancia de la base con la estuctura de la base creada y en el archivo mvn clean install y en el archivo application.properties debe estar configurado la ip y puerto de conexión.
- Para ejecutar el test ClientTests(endpoint client con karate) correctamente se debe tener arriba el endpoint de clientes.


## Despliegue en docker

- Cambiar en el archivo application.properties el valor del parametro spring.datasource.url con el valor jdbc:mysql://db:3306/microservicios_db , donde db es el contenedor de la base de datos en los dos microservicios.
- Ingresar en el microservicio client-person se debe ejecutar los comandos:
    - mvn clean package 
    - docker build -t client-person-microservice .
- Ingresar en el microservicio account-movement se debe ejecutar los comandos:
    - mvn clean package 
    - docker build -t account-movement-microservice .
- Abrir el docker desktop 
- Ir al directorio raiz del proyecto en la consola y ejecutar el comando:  docker-compose up --build -d 

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/mcdanielcv/devsu_practico)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mario-castellanos-b6554152/)
