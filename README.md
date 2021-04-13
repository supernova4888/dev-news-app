# dev-news-app
<<<<<<< HEAD

=======
>>>>>>> d14dd318ad54b29b1778a60a93b6a39bdcbe6422
13-Apr-2021
@Author: Diana Bao

Introduction

This assignment is all about building the backend API for a developer news site where users can create articles, comment them and post their reactions (likes, dislikes). It doesn't require a graphical user interface so it is enough to be able to make requests and get plain json text responses via curl/Postman.

Objectives:
- Understand the basic structure of a Spring application.
- Practice building, testing and consuming rest APIs.
- Learn about data modelling for real world applications.
- Learn how to interact with a relational database using an ORM tool implementing Spring JPA (Hibernate).

Dependencies:
- Spring Framework
- POSTGRESQL (postgres:13-alpine)
- Docker
- Gradle
<<<<<<< HEAD

=======
 
>>>>>>> d14dd318ad54b29b1778a60a93b6a39bdcbe6422
Implemented functionalities


Testing
> Performed using requests in Postman

How to run?
1. Download or Clone/fork the repository
2. Open in your preffered IDE/Text editor
3. Open the terminal and go to the root folder
4. Determine the state of the schema in file 'application.properties': create (current) or validation
5. Run <docker-compose up>
6. Run <gradle BootRun>
7. Use curl or Postman to run requests
8. Toggle Crtl+C to stop running

CLI commands to enter running container, database and execute psql commands:
<<<<<<< HEAD
1. docker-compose exec database /bin/sh
2. psql devnews demo_user
=======
1. <docker-compose exec database /bin/sh>
2. <psql devnews demo_user>
>>>>>>> d14dd318ad54b29b1778a60a93b6a39bdcbe6422
4. Reference for PostgreSQL syntax https://www.postgresql.org/docs/13/app-psql.html

Credits:
- KTH University lecturers and staff
- Novare

