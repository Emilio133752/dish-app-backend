# Etapa de build
FROM maven:3.8.7-openjdk-17 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml primeiro para baixar as dependências
COPY pom.xml .

# Baixe as dependências
RUN mvn dependency:go-offline -B

# Copie o restante do código
COPY src ./src

# Compile e construa o JAR
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim

# Porta exposta para a aplicação
EXPOSE 8080

# Copie o JAR gerado da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
