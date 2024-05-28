package com.example.springboottodo;

import com.example.springboottodo.DAO.TodoDAO;
import com.example.springboottodo.DAO.UserDAO;
import com.example.springboottodo.Entity.User;
import com.example.springboottodo.utils.GenerateRandomUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootTodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTodoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TodoDAO todoDAO, UserDAO userDao) {
        return runner -> {
//            createMultipleTodos(todoDAO);
//            createMultipleUser(userDao);

            removeUser(userDao);
            getAllUsers(userDao);
        };
    }

    private void removeUser(UserDAO userDAO) {
        System.out.println("Removing user");
        User user = userDAO.findById(32);
        if (user != null) {
            userDAO.delete(user);
        } else {
            System.out.println("User not found");
        }
    }

    private void getAllTodos(TodoDAO todoDAO) {
        System.out.println("Getting all todos");
        todoDAO.findAll().forEach(System.out::println);
    }

    //raises `DataIntegrityViolationException`
    private void createUser(UserDAO userDAO) {
        System.out.println("Creating user");
        User newUser = new User("random","zane.reyes@hotmail.com","password");
        userDAO.save(newUser);
    }

    private void createMultipleUser(UserDAO userDAO) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Creating user");
            User newUser = GenerateRandomUser.generateRandomUser();
            userDAO.save(newUser);
        }
    }

    private void getAllUsers(UserDAO userDAO) {
        System.out.println("Getting all users");
        userDAO.findAll().forEach(System.out::println);
    }

}
