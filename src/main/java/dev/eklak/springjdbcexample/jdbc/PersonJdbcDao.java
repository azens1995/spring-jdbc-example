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
