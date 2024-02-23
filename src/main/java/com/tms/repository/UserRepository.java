package com.tms.repository;

import com.tms.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class UserRepository {

    private EntityManager entityManager = null;

    public UserRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        entityManager = factory.createEntityManager();
    }

    public List<User> findAll() {
     //   Query query = entityManager.createNativeQuery("SELECT * FROM users", User.class);
        Query query = entityManager.createQuery("FROM users", User.class); //users не таблица а название entity
        return query.getResultList();
    }

    public User findById(Long id) {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean createUser(User user) {
        try {
            entityManager.getTransaction().begin();  //начинаем транзакцию
            entityManager.persist(user); //метод для сохранения
            entityManager.getTransaction().commit(); //комитаем(фиксируем изменения)
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public boolean updateUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public boolean updateUserPassword(String password, Long id) {
        try {
            User user = entityManager.find(User.class, id);
            user.setUserPassword(password);
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(User.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}