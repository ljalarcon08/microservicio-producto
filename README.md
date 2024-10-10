# microservicio-producto

Contiene controlador para administracion de productos

- Crea, Actualizar y elimina Productos
- Crea, Actualizar y elimina Catalogo
- Crea, Actualizar y elimina Carro

Ademas contiene la conexión, repositorio y servicio a base de datos MongoDB

## Ejecución

1. Crear ejecutable: mvn clean package
2. java -jar microservicio-producto.jar --spring.profiles.active=dev

##Swagger

http://localhost:55087/producto/swagger-ui.html

## Docker

Para creacion en docker revisar Dockerfile
