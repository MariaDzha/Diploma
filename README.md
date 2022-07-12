# Запуск автотестов:
1. Склонировать репозиторий Diploma
2. Запустить контейнер с двумя базами данных и симулятором банковских сервисов командой:
   docker-compose up
3. Запустить приложение командами:
   - для Mysql

    java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar
   
   - для Postgresql

java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar

4. Запустить тесты командами:
- для Mysql

    gradlew clean test allureReport -Ddb.url=jdbc:mysql://localhost:3306/app -Ddb.user=app -Ddb.password=pass

- для Postgresql

    gradlew clean test allureReport -Ddb.url=jdbc:postgresql://localhost:5432/app -Ddb.user=app -Ddb.password=pass

5. Сформировать отчет командой:
gradlew allureServe

План тестирования: 

Отчет по результатам тестирования: (https://github.com/MariaDzha/Diploma/blob/master/documentation/Report.md)

Отчет по результатам автоматизации: https://github.com/MariaDzha/Diploma/blob/master/documentation/Summary.md