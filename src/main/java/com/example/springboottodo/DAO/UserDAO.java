package com.example.springboottodo.DAO;

import com.example.springboottodo.Entity.Todo;
import com.example.springboottodo.Entity.User;

import java.util.List;

public interface UserDAO {

    User save(User user);
    User update(User user);

    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);

    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);
    User findByEmailAndPassword(String email, String password);
    User findByUsernameOrEmail(String username, String email);

    void delete(User user);
    void deleteByUsername(String username);
    void deleteByEmail(String email);
    void deleteById(int id);
}
