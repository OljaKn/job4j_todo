                         *Приложение TODO - Список задач*

Описание проекта:
- TODO - "Список задач" - WEB приложение, которое позволяет:
- записать задачи(дела), 
- изменять их,
- удалять,
- просмотреть все или только выполненные/невыполненные;

Стек технологий:
- Java 17,
- Spring boot,
- Thymeleaf,
- Bootstrap, 
- Hibernate, 
- PostgreSq

Требования к окружению:
Java 17, Maven 3.9, PostgreSQL 16

Запуск проекта:
- Для запуска необходимо скачать проект из репозитория job4j_todo по команде: '''Code -> Download ZIP''';
- Создать базу данных в PostgreSq с помощью команды '''create database todo''';
- Открыть в IntelliJ IDEA проект и собрать его при помощи '''Maven liquibase:update''';
- запустить проект в классе Main;
- в браузере ввести http://localhost:8080/;

Скриншоты:
Главная страница
![index.png](files%2Findex.png)

Список всех задач
![allTasks.png](files%2FallTasks.png)

Создание новой задачи
![create.png](files%2Fcreate.png)

Список выполненных задач
![completed.png](files%2Fcompleted.png)

Список новых задач
![new.png](files%2Fnew.png)

Страница для редактирование 
![update.png](files%2Fupdate.png)

Просмотр задачи
![findId.png](files%2FfindId.png)

