# Cinema Service 
This project represents the work of a cinema.

It has N-tier architecture with layers:
* Database;
* DAO;
* Service;
* Controllers.

**Visitors have different functions. It is depends on roles that they have.**

|          Without role    |                 USER              |                        ADMIN                   |
|--------------------------|-----------------------------------|------------------------------------------------|
|Authorization             |View data about movies             |View data and add movies                        |
|Registration              |View data about movie-sessions     |View data, delete, update and add movie-sessions|
|                          |View data about cinema-halls       |View data and add cinema-halls                  |
|                          |Add movie-sessions to shopping cart|                                                |
|                          |Complete order                     |                                                |

###Technologies:
* Java;
* Spring Core / Security / MVC;
* Maven;
* Hibernate;
* MySQL;
* Tomcat.

###Configuration
1. Download and install the JDK.
2. Download and install Apache Tomcat.
3. Download and install MySQL and create schema.
4. Update file "db.properties" and write your data.