package com.example.springboottodo.DAO;

import jakarta.persistence.EntityManager;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            return entityManager.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password order by id", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        try {
            return entityManager.createQuery("SELECT u FROM Users u WHERE u.email = :email AND u.password = :password order by id", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        try {
            return (User) entityManager.createQuery("SELECT u FROM Users U WHERE u.username =: username OR u.email =: email order by id")
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteByUsername(String username) {
        try {
            entityManager.createQuery("DELETE FROM Users WHERE username =: username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByEmail(String email) {
        try {
            entityManager.createQuery("DELETE FROM Users WHERE email =: email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
