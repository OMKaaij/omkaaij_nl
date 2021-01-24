package com.omkaaij.omkaaij_nl.data.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DAO<T, ID> {

    @Transactional
    void create(T entity);

    Optional<T> read(ID id);

    void update(T entity);

    void delete(T entity);

    void deleteById(ID id);

    Optional<List<T>> List();
}
