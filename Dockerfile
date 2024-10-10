FROM eclipse-temurin:21-jdk-ubi9-minimal
RUN mkdir /app
COPY target/microservicio-producto-0.0.1-SNAPSHOT.jar app/microservicio-producto-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","app/microservicio-producto-0.0.1-SNAPSHOT.jar","--spring.profiles.active=docker"]
EXPOSE 8070-8079
# docker build --tag=microservicio-producto:latest .
# docker run -d --net=docker-network --name=microservicio-producto  microservicio-producto:latest