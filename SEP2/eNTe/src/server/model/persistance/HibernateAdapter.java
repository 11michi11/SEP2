package server.model.persistance;

import model.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import server.model.persistance.DBPersistence;

import javax.persistence.metamodel.EntityType;

import java.util.*;

public class HibernateAdapter implements DBPersistence {

    private final SessionFactory ourSessionFactory;

    public HibernateAdapter() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    @Override
    public List<User> getUsers() {
        Transaction tx = null;
        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<User> films = session.createQuery("FROM User ").list();
            tx.commit();
            return films;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    @Override
    public List<Post> getPosts() {
        Transaction tx = null;
        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<Post> posts = session.createQuery("FROM Post ").list();
            tx.commit();
            return posts;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    @Override
    public void addFamily(Family family) {
        addObject(family);
    }

    @Override
    public void addUser(User user) {
        addObject(user);
    }

    @Override
    public void updateUser(User user) {
        updateObject(user);
    }

    @Override
    public void addPost(Post post) {
        addObject(post);
    }

    @Override
    public void addHomeworkReply(HomeworkReply reply) {
        addObject(reply);
    }

    @Override
    public void updatePost(Post post) {
        updateObject(post);
    }

    @Override
    public void updateHomeworkReply(HomeworkReply reply) {
        updateObject(reply);
    }

    @Override
    public void deleteFamily(Family family) {
        deleteObject(family);
    }

    @Override
    public void deleteUser(User user) {
        deleteObject(user);
    }

    @Override
    public void deletePost(Post post) {
        deleteObject(post);
    }

    private void updateObject(Object obj){
        Transaction tx = null;
        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    private void addObject(Object obj){
        Transaction tx = null;
        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    private void deleteObject(Object obj){
        Transaction tx = null;
        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}