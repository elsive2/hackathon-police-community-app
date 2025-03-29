# Этап сборки
FROM maven:3.9-eclipse-temurin-21 as builder

WORKDIR /app
COPY pom.xml .
# Скачиваем зависимости (кешируем этот слой)
RUN mvn dependency:go-offline

# Копируем исходный код и собираем приложение
COPY src src
RUN mvn clean package -DskipTests

# Финальный образ
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
# Копируем собранный JAR из builder-этапа
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 6206
ENTRYPOINT ["java", "-jar", "app.jar"]