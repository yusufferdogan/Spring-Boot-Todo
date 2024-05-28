package com.example.springboottodo;

import com.example.springboottodo.DAO.UserDAO;
import com.example.springboottodo.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringBootTodoApplication.class)
class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    @Transactional
    void testCreateUser() {
        User newUser = new User("random","test.email@gmail.com","password");
        User savedUser = userDAO.save(newUser);
        assertNotNull(savedUser);
        userDAO.delete(newUser);
    }

    @Test
    void testFindAllUsers() {
        Iterable<User> users = userDAO.findAll();
        assertNotNull(users);
        assertTrue(users.iterator().hasNext());
    }

    @Test
    void testFindUserById() {
        User user = userDAO.findById(1); // assuming a user with ID 1 exists
        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    @Transactional
    void testUpdateUser() {
        User user = userDAO.findById(1); // assuming a user with ID 1 exists
        user.setUsername("updatedUsername");
        userDAO.save(user);
        User updatedUser = userDAO.findById(1);
        assertEquals("updatedUsername", updatedUser.getUsername());
    }

    @Test
    @Transactional
    void testDeleteUser() {
        User user = userDAO.findById(1); // assuming a user with ID 1 exists
        userDAO.delete(user);
        User deletedUser = userDAO.findById(1);
        assertNull(deletedUser);
    }


    // Add more tests for other UserDAO methods here
}