# OpenJDK runtime oficial como imagen base
FROM openjdk:17-jdk-alpine

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el wrapper Gradle y archivos de projecto al contenedor
COPY gradlew gradlew
# Dar permiso de ejecutable al wrappper Gradle
RUN chmod +x gradlew

COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src

# Build usando wrapper Gradle
RUN sh ./gradlew clean build -x test

# Exponer puerto 8090 para la app
EXPOSE 8090

# Setear el comando para correr la applicación
CMD ["java", "-jar", "build/libs/Caliban-0.0.1-SNAPSHOT.jar"]