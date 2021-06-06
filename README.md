
<!-- PROJECT LOGO -->
<br />
<p align="center">

  <h1 align="center">Meow Book Store</h1>
  <br />
  <p align="center">
    🐈 Meows can buy and read book here! 🐈
    <br />
    <br />
    <a href="https://github.com/tsangwai2009/meowStore"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/tsangwai2009/meowStore">View demo</a>
    ·
    <a href="https://github.com/tsangwai2009/meowStore">Report Bug</a>
    ·
    <a href="https://github.com/tsangwai2009/meowStore">Request Feature</a>
  </p>
  <br />
</p>

<!-- ABOUT THE PROJECT -->
## About The Project

You may want to develop a online store with a demo code. Here is a Book Store demo for you for quick start! 

![Screen Shot](https://i.imgur.com/eLvL8Sj.png)

Frameworks: Spring + Hibernate + AngularJS

Before start, you may need to:
* Setup connection with Oracle SQL
* Create moderator account in Database
* Use Java 1.8 to compile the project

You can refer to this ER diagram to study the database structure:

![ER diagram](https://i.imgur.com/oQ0USno.png)


### Built With

Here list the major software & frameworks that this project using:

[Oracle Database Express Edition](https://www.oracle.com/database/technologies/appdev/xe.html)

[Oracle SQL Developer](https://www.oracle.com/database/technologies/appdev/sqldeveloper-landing.html)

[Spring Framework](http://docs.spring.io/spring/docs/4.1.0.BUILD-SNAPSHOT/spring-framework-reference/htmlsingle/)

 [Hibernate](https://www.tutorialspoint.com/hibernate/hibernate_overview.htm)

Spring Boot is the foundation of this sample project, you could read the below tutorials: 

https://en.wikipedia.org/wiki/Spring_Framework#Spring_Boot 

http://projects.spring.io/spring-boot/

The user interface is built by using AngularJS with RESTful services:

http://www.w3schools.com/angular/

http://www.tutorialspoint.com/angularjs/

<!-- System Feature -->
## System Features

![System Features](https://i.imgur.com/XBVc69D.png)

<!-- GETTING STARTED -->
## Getting Started

To get a demo code and run in localhost with follow these simple steps:



1. Clone the repo
   ```sh
   git clone https://github.com/tsangwai2009/meowStore.git
   ```
2. Import as Maven Project in IDE

3. Create database with ER diagram or execute the SQL statement (mentioned in SQL.txt) to create tables

4. Configurate database connection 
![Database connection](https://i.imgur.com/dH1Itae.png)

5. Run "SampleApplication.java"

6. Open the website with `http://localhost:12345/main#/home`
   

<!-- Program Flow -->
## Program Flow

To connect your prepared data structure to the Java world, you have to configure a data source by create beans and DAO objects.

![Program Flow](https://i.imgur.com/9btGCcf.png)

DAO layer, which refers to Data Access Layer, consists of source code closely related to the database. For example, all the CRUD (i.e. Create, Read, Update and Delete) instructions to the database are specified in the DAO layer. ORM generates SQLs based on the setup of Java Beans/Entity (i.e. beans), and CRUD instructions
are converted from the definition of beans, which are usually specified by some class files and annotations.

The Service Layer contains source code related to the business logic, such as transaction and querying data depending on the state of users. 

The Presentation Layer refers to user interfaces, which interacting with the business logic in the Service Layer.

<!-- CONTRIBUTING -->
## Contribution

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.
