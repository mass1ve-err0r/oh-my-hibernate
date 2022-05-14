# oh-my-hibernate
> Opinionated Hibernate and Liquibase introduction for Java 8


## Prelude
This repository serves as base for anyone who's interested in learning how to use Hibernate alongside Liquibase.

It is inevitable to walk through the JPA CriteraBuilder APIs to properly understand how queries are built and executed.
The code shown is somewhat opinionated and may not adhere to "best-practices", feel free to fork and improve.

Please remember: You don't have to understand everything from the getgo. It's okay to take your time, document the code
if you need to.


## Batteries included
The following components are included:
- H2 Database
- Hibernate 5.x
  - Simple and (somewhat) complex queries
  - A ready-to-use, thread-safe `SessionFactory` abstraction layer (_HibernateSessionFactory_)
  - Ready-to-use `Session` abstraction layer (_AbstractService_) to provide safe access and draft good query APIs
- Liquibase 4.x
  - 3 changelogs showcasing commonly used features
  - An exemplary `customChange` implementation (_LiquibaseEchoTask_)
- CRUD example class


## Prerequisites
You just need a fairly modern IDE (or MS Paint if you're feeling fuzzy) and a Java 8 JDK.

I'm not sponsored by Azul Inc. nor am I affiliated with them, but I recommend their JDK since it bundles JavaFX:
[Download](https://www.azul.com/downloads/?package=jdk#download-openjdk)


## License
The content of this repository is hereby licensed after the MIT License. Please enjoy!