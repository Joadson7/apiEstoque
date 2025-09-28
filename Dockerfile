# Etapa 1: Build da aplicação
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Faz o build sem rodar testes
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com JDK
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/target/*.jar app.jar

# Expor a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar
ENTRYPOINT ["java", "-jar", "app.jar"]
