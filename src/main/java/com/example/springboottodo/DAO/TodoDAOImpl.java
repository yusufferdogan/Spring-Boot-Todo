package com.example.springboottodo.DAO;

import com.example.springboottodo.Entity.Todo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDAOImpl implements TodoDAO {

    private final EntityManager entityManager;

    @Autowired
    public TodoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Todo save(Todo todo) {
        entityManager.persist(todo);
        return todo;
    }

    @Override
    public Todo findById(int id) {
        return entityManager.find(Todo.class, id);
    }

    @Override
    public List<Todo> findAll() {
        try {
            return entityManager.createQuery("SELECT t FROM Todo t order by id", Todo.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Todo> findByTitle(String title) {
        try {
            return entityManager.createQuery("SELECT t FROM Todo t WHERE t.title = :title order by id", Todo.class)
                    .setParameter("title", title)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Todo update(Todo todo) {
        entityManager.merge(todo);
        return todo;
    }

    @Override
    public void delete(Todo todo) {
        entityManager.remove(todo);
    }

    @Override
    public void deleteById(int id) {
        try {
            entityManager.createQuery("DELETE FROM Todo t WHERE t.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
