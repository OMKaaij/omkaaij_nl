package com.omkaaij.omkaaij_nl.data.dao;


import com.omkaaij.omkaaij_nl.data.mapper.VisitorRowMapper;
import com.omkaaij.omkaaij_nl.model.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j(topic = "VisitorDAO")
public class VisitorDAO implements DAO<Visitor, Long> {

    private final JdbcTemplate jdbcTemplate;

    public VisitorDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void create(Visitor visitor) {
        final String sql = "INSERT INTO visitor(userName, password, company) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"visitorID"});
            preparedStatement.setString(1, visitor.getUserName());
            preparedStatement.setString(2,visitor.getPassWord());
            preparedStatement.setString(3, visitor.getCompany());
            return preparedStatement;
        }, keyHolder);
        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        visitor.setVisitorID(id);
    }

    @Override
    public Optional<Visitor> read(Long visitorID) {
        final String sql = "SELECT * FROM visitor WHERE visitorID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new VisitorRowMapper(), visitorID));
    }

    @Override
    public void update(Visitor visitor) {
        final String sql = "UPDATE visitor SET userName = ?, password = ? WHERE visitorID = ?";
        jdbcTemplate.update(sql, visitor.getUserName(), visitor.getPassWord(), visitor.getVisitorID());
    }

    @Override
    public void delete(Visitor visitor) {
        jdbcTemplate.update("DELETE FROM visitor WHERE visitorID = ?", visitor.getVisitorID());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM visitor WHERE visitorID = ?", id);
    }

    @Override
    public Optional<List<Visitor>> List() {
        return Optional.of(jdbcTemplate.query("SELECT * FROM visitor", new VisitorRowMapper()));
    }

    public Optional<Visitor> read (String userName) {
        String sql = "SELECT * FROM visitor WHERE userName = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new VisitorRowMapper(), userName));
    }
}
