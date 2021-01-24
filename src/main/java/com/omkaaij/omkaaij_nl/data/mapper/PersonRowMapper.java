package com.omkaaij.omkaaij_nl.data.mapper;

import com.omkaaij.omkaaij_nl.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Person(
                resultSet.getLong("visitorID"),
                resultSet.getString("initials"),
                resultSet.getString("firstName"),
                resultSet.getString("preposition"),
                resultSet.getString("surName"),
                resultSet.getString("postalCode"),
                resultSet.getString("homeNumber"),
                resultSet.getString("street"),
                resultSet.getString("residence"),
                resultSet.getString("email"),
                resultSet.getString("phone")
        );
    }
}
