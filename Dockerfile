# ---------- Stage 1: Build ----------
FROM maven:3.9-eclipse-temurin-17 AS builder

# Mejora de cache: primero dependencias
WORKDIR /app
COPY pom.xml ./
# Descarga dependencias sin compilar código (mejor cache)
RUN --mount=type=cache,target=/root/.m2 mvn -B -q dependency:go-offline

# Copia el código y compila
COPY src ./src
# Construye el JAR (omite tests en CI/CD, ajusta a tu necesidad)
RUN --mount=type=cache,target=/root/.m2 mvn -B -q package -DskipTests

# Detecta el JAR final (Spring Boot crea un *-SNAPSHOT o con versión)
# Si tienes un nombre fijo, puedes reemplazar el patrón.
RUN JAR_FILE=$(ls target/*-SNAPSHOT.jar 2>/dev/null || ls target/*.jar) && \
    cp "$JAR_FILE" /app/app.jar


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jre-jammy AS runtime
# Alternativas ligeras:
# FROM eclipse-temurin:17-jre-alpine

ENV APP_HOME=/opt/app \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0" \
    SPRING_PROFILES_ACTIVE=prod \
    TZ=America/La_Paz

WORKDIR $APP_HOME

# Usuario no-root por seguridad
RUN useradd --system --create-home --home-dir $APP_HOME appuser
USER appuser

# Copiamos solo el JAR final
COPY --from=builder /app/app.jar ./app.jar

# Puerto típico de Spring Boot
EXPOSE 8080

# Healthcheck simple (ajusta la ruta si tienes actuator/health)
HEALTHCHECK --interval=30s --timeout=3s --start-period=20s --retries=3 \
  CMD sh -c "wget -qO- http://127.0.0.1:8080/actuator/health | grep -q '\"status\":\"UP\"'" || exit 1

# Para logs legibles en contenedor
ENV JAVA_TOOL_OPTIONS="-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8"

# Arranque
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]