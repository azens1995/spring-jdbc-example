### Spring JDBC Example

#### Technology
1. Spring boot
2. Spring web
3. Spring JDBC
4. H2 Database

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

#### Plain JDBC

In the Plain JDBC, following things is required to retrieve the data:
1. Get the connection to the database using datasource
2. Prepare the SQL statement for execution
3. Execute the SQL statement
4. Get and map the resultSet retrieved from the database
5. Close the connection prepared statement and database connection
6. Return the retrieved mapped data


If exception happens, the connection does not get closed.
The connection remains opened which is the memory leakage.

#### Retrieving all objects from table
a. Create the entity class that can hold the data of the returned result from the database.
`Person.class`

```java
package dev.eklak.springjdbcexample.entity;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private String location;
    private Date birthDate;

    public Person() {
    }

    public Person(int id, String name, String location, Date birthDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

```

b. Create the Dao class that will use JDBC to perform operations in database.
For this, `JdbcTemplate` bean provided by the Spring is used. This bean will get the database connection, and will perform the necessary execution as intended by the user.

```java
package dev.eklak.springjdbcexample.jdbc;

import dev.eklak.springjdbcexample.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PersonJdbcDao is used to handle the CRUD operation.
 * This will provide the DAO of the operations.
 */
@Repository
public class PersonJdbcDao {

    // Normally JDBC connection has to be made to make the connection to the Database
    // In case of the Spring, the bean is provided by the Spring as JdbcTemplate
    // This bean executes the database configuration, and keep the connection open for queries
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Function to retrieve all the Person from the database table
     * @return List of Person Object from Database
     */
    public List<Person> findAll() {
        // Note: BeanPropertyRowMapper requires the no args constructor while mapping the result set to the dto/model
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper(Person.class));
    }

    /**
     * Method to find the person based on the ID of the person
     * @param id Id of the person
     * @return Person object matched with the ID provided
     */
    public Person findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PERSON WHERE ID=?",
                new BeanPropertyRowMapper<>(Person.class),
                new Object[]{id});
    }

}

```
Note that, we have annotated this class as  `@Repository` to let the spring create the bean for us.

c. To view the execution, we will be converting the spring application into command line application, but implementing `CommandlineRunner` in the main class. Inside the overridden `run()` method, we will be executing the method from the `dao` class.

```java
// Find all list of the person in the table
var personList = personJdbcDao.findAll();
System.out.println("The list of the person are: " + personList.toString());

var person = personJdbcDao.findById(10001);
System.out.println("The person with ID 10001 is " + person.toString());
```