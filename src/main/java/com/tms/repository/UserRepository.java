package com.tms.repository;

import com.tms.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository {

    private Session session = null;
    private CriteriaBuilder cb = null;

    public UserRepository() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        cb = session.getCriteriaBuilder();
    }

    public List<User> findAll() {
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        return session.createQuery(criteria).getResultList();
    }

    public User findById(Long id) {
        try {
            CriteriaQuery<User> criteria = cb.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root).where(cb.equal(root.get("id"),id));
            return session.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //Criteria cann't create line
    public boolean createUser(User user) {
        try {
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public boolean updateUser(User user) {
        try {
            CriteriaUpdate<User> criteria = cb.createCriteriaUpdate(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.set("username",user.getUsername());
            //etc. all field what you need !
            criteria.where(cb.equal(root.get("id"),user.getId()));

            session.getTransaction().begin();
            session.createMutationQuery(criteria).executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public boolean updateUserPassword(String password, Long id) {
        try {
            CriteriaUpdate<User> criteria = cb.createCriteriaUpdate(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.set("password",password);
            //etc. all field what you need !
            criteria.where(cb.equal(root.get("id"),id));

            session.getTransaction().begin();
            session.createMutationQuery(criteria).executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        try {
            CriteriaDelete<User> criteria = cb.createCriteriaDelete(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.where(cb.equal(root.get("id"),id));

            session.getTransaction().begin();
            session.createMutationQuery(criteria).executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        return false;
    }
}