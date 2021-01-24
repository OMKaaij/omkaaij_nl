package com.omkaaij.omkaaij_nl.data.mapper;

import com.omkaaij.omkaaij_nl.model.Visitor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorRowMapper implements RowMapper<Visitor> {
    @Override
    public Visitor mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Visitor(
                resultSet.getLong("visitorID"),
                resultSet.getString("userName"),
                resultSet.getString("password"),
                resultSet.getString("company")
        );
    }
}
