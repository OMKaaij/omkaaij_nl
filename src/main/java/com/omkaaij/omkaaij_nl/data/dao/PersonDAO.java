package com.omkaaij.omkaaij_nl.data.dao;

import com.omkaaij.omkaaij_nl.data.mapper.PersonRowMapper;
import com.omkaaij.omkaaij_nl.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;


@Repository
public class PersonDAO implements DAO<Person, Long> {

    private JdbcTemplate jdbcTemplate;

    public PersonDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Person person) {
        String sql = "INSERT INTO person (visitorID, initials, firstName, preposition, surName, postalCode, homeNumber, street, residence" +
                ",email, phone) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, person.getVisitorID());
            preparedStatement.setString(2, person.getInitials());
            preparedStatement.setString(3, person.getFirstName());
            preparedStatement.setString(4, person.getPreposition());
            preparedStatement.setString(5, person.getSurName());
            preparedStatement.setString(6, person.getPostalCode());
            preparedStatement.setString(7, person.getHomeNumber());
            preparedStatement.setString(8, person.getStreet());
            preparedStatement.setString(9, person.getResidence());
            preparedStatement.setString(10, person.getEmail());
            preparedStatement.setString(11, person.getPhone());
            return preparedStatement;
        });
    }

    @Override
    public Optional<Person> read(Long id) {
        String sql = "SELECT * FROM person WHERE visitorID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new PersonRowMapper(), id));
    }

    @Override
    public void update(Person person) {
        String sql = "UPDATE person SET initials = ?, firstNAme = ?, preposition = ?, surName = ?, postalCode = ?, homeNumber = ?," +
                "street = ?, residence = ?, email = ?, phone = ? WHERE visitorID = ?";
        jdbcTemplate.update(sql, person.getInitials(), person.getFirstName(), person.getPreposition(), person.getSurName(),
                person.getPostalCode(), person.getHomeNumber(), person.getStreet(), person.getResidence(), person.getEmail(),
                person.getPhone(), person.getVisitorID());
    }

    @Override
    public void delete(Person person) {
        jdbcTemplate.update("DELETE FROM person WHERE visitorID = ?", person.getVisitorID());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM person WHERE visitorID = ?", id);

    }

    @Override
    public Optional<List<Person>> List() {
        String sql = "SELECT * FROM person";
        return Optional.of(jdbcTemplate.query(sql, new PersonRowMapper()));
    }
}
