### Spring JDBC Example

#### Things to Do
1. Create the project
2. Add dependency required for the Spring JDBC configuration and other dependencies
3. Handling configuration for Database connection
4. Table creation and initial data loading
5. Simple CRUD operations


#### Steps
A. Create the project

You can create the project by going to the [spring initializer web site](https://start.spring.io/).

B. Add Dependencies
For the case of learning Spring JDBC, we will be adding following dependencies to the spring boot project.

1. Spring Web
2. Spring JDBC
3. H2 Database for temporary database

C. Database configuration

To configure the database, add following in the `application.properties` file.
```text
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.data.jpa.repositories.bootstrap-mode=default
spring.jpa.defer-datasource-initialization=true
```

D. Database setup and table creation
We will be using the `data.sql` file to create the table and load the initial data to the database table.
1. Create a file `data.sql` in `/src/main/resources` folder. This file will be executed automatically each time when the application runs.
2. Add script for table creation.
3. Add script for data insertion.

Final `data.sql` file will be:
```sql
-- Create person table
CREATE TABLE person
(
    id INTEGER NOT NULL ,
    name VARCHAR(255) NOT NULL ,
    location VARCHAR(255),
    birth_date TIMESTAMP,
    primary key (id)
);

-- Insert data into the person table
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
    VALUES (10001, 'Eklak', 'Mahendranagar', CURRENT_DATE());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES (10002, 'Romeo', 'Lalitpur', CURRENT_DATE());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES (10003, 'Raskin', 'Sanagaun', CURRENT_DATE());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES (10004, 'Sushil', 'Machhegaun', CURRENT_DATE());

```

In the above sql file, we create the person table having attribute id (which will be primary key), name, location, and birthdate.

