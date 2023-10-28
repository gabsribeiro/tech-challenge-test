FROM azul/zulu-openjdk-alpine:17 as builder

WORKDIR /app

COPY ./target/techchallenge02-0.0.1-SNAPSHOT.war /app/tech-challenge.war

FROM azul/zulu-openjdk-alpine:17

WORKDIR /app

COPY --from=builder /app/tech-challenge.war /app/tech-challenge.war

EXPOSE 8080:8080

ENTRYPOINT ["java", "-jar", "/app/tech-challenge.war"]
