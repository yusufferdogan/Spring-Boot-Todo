package com.example.springboottodo.DAO;

import com.example.springboottodo.Entity.Todo;

import java.util.List;

public interface TodoDAO {
    public Todo save(Todo todo);
    Todo update(Todo todo);

    public Todo findById(int id);
    List<Todo> findAll();
    List<Todo> findByTitle(String title);

    void delete(Todo todo);
    void deleteById(int id);
}
