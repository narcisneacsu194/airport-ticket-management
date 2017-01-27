package com.teamtreehouse.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
/*
* This is the class where the main method application resides.
* The main method deploys this spring application, using the static method run of the SpringApplication class.
* The idea of the application is an airline website for managing ticket sales to customers.
* It is not very complicated, you have users that make bookings to certain places.
* For each individual user, all his bookings are displayed in the user detail page.
* You have a form, from which you submit as a user the details of the flight.
* You can add, edit, delete existing users, bookings and places.
* Technologies that I used:
* -Application Deployment: Spring Boot
* -Java ORM Framework: Hibernate
* -Database engine: H2
* -Front-end engine: Thymeleaf
* -Testing: JUnit
* All the static HTML and CSS are from a template I borrowed from the Internet, as I don't generally want to focus
* too much on the looks of a website.
* What I would change to this project ? Instead of H2 database engine, I would use a real world alternative like MySQL, PostgreSQL etc.
* The other thing I would change, is that instead of using Thymeleaf as my dynamic view, I would replace it with AngularJS,
* as it becomes more and more used in the real world projects.
* I would also want to integrate this application within a cloud computing service, like Microsoft Azure.
* One final thing to add would be that I used Tomcat DBCP (Database Connection Pool) for having a faster and more resource friendly
* connectivity to the database.
*/
@EnableAutoConfiguration
@ComponentScan
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
