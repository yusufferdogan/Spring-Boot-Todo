package com.example.springboottodo.DAO;

import com.example.springboottodo.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Transactional
    @Override
    public void delete(User user) {
        User managedUser = entityManager.merge(user);
        entityManager.remove(managedUser);
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
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User order by id", User.class);
        return query.getResultList();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password order by id", User.class)
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
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password order by id", User.class)
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
            return (User) entityManager.createQuery("SELECT u FROM User U WHERE u.username =: username OR u.email =: email order by id")
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteByUsername(String username) {
        try {
            entityManager.createQuery("DELETE FROM User WHERE username =: username")
                    .setParameter("username", username)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void deleteByEmail(String email) {
        try {
            entityManager.createQuery("DELETE FROM User WHERE email =: email")
                    .setParameter("email", email)
                    .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
