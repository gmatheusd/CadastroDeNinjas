FROM eclipse-temurin:21-jre
LABEL maintainer="https://github.com/gmatheusd" authors="M.Douglas"

RUN groupadd -r ninjas && useradd -r -g ninjas ninjas

WORKDIR /app
COPY target/CadastroDeNinjas-0.0.1-SNAPSHOT.jar /app/cadastro-de-ninjas.jar

USER ninjas

ENTRYPOINT ["java", "-jar", "cadastro-de-ninjas.jar"]