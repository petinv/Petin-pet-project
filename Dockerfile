# Используем базовый образ с Java
FROM openjdk:17-jdk-alpine

# Установка рабочей директории в /app
WORKDIR /app

# Копирование JAR файла в контейнер
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Определение порта, который будет открыт в контейнере
EXPOSE 8080

# Команда для запуска приложения при старте контейнера
CMD ["java", "-jar", "demo.jar"]
