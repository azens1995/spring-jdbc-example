package dev.eklak.springjdbcexample;

import dev.eklak.springjdbcexample.jdbc.PersonJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJdbcExampleApplication implements CommandLineRunner {

    // JDBC
    @Autowired
    private PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Find all list of the person in the table
        var personList = personJdbcDao.findAll();
        System.out.println("The list of the person are: " + personList.toString());

        var person = personJdbcDao.findById(10001);
        System.out.println("The person with ID 10001 is " + person.toString());
    }
}
