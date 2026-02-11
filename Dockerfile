# Imagen base con JDK 17 (puedes ajustar según tu versión de Java)
ARG JAVA_IMAGE=eclipse-temurin:17-jdk
# Etapa de construcción
FROM ${JAVA_IMAGE} AS runtime
# Etiqueta de mantenimiento
VOLUME /tmp
# Directorio de trabajo dentro del contenedor
WORKDIR /app
# Copiamos el JAR generado por Maven/Gradle
COPY target/app-ibk-weather-v1.jar app.jar
# Exponemos el puerto donde corre Spring Boot
EXPOSE 8080
# Instalar redis-tools (incluye redis-cli)
RUN apt-get update && apt-get install -y redis-tools && rm -rf /var/lib/apt/lists/*
# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]