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

    public static void main(final String[] args) throws Exception {
        HibernateAdapter adapter = new HibernateAdapter();

        List<Post> posts = adapter.getPosts();
        posts.forEach(System.out::println);
//        List<User> allUsers = adapter.getAllUsers();
//        allUsers.forEach(System.out::println);
//        List<Family> allFamilies = adapter.retrieveFamilies(allUsers);

    }



    @Override
    public List<Family> getFamilies() {
        throw new AssertionError("Not implemented");
    }

    //Future getAllFamilies
    public List<Family> retrieveFamilies(List<User> users) {
        Set<Family> families = new HashSet<>();
        users.stream().filter(user -> user instanceof IFamily)
                .map(user -> (IFamily) user)
                .forEach(iFamily -> families.add(iFamily.getFamily()));
        return new LinkedList<>(families);
    }

    @Override
    public LinkedList<User> getUsers(FamilyList families) {
        throw new AssertionError("Not implemented");
    }

    //Future getUsers
    public List<User> getAllUsers() {
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

    //TODO change method signature
    @Override
    public List<Post> getPosts(UsersList users) {
        throw new AssertionError("Method to be deleted");
    }
   // @Override
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

    //TODO
    @Override
    public void addHomeworkReply(String homeworkId, HomeworkReply reply) {
        throw new AssertionError("Not implemented");
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
    public void deleteUser(String id) {
        //need to change method signature TODO
    }

    @Override
    public void deletePost(String postID) {
        //need to change method signature TODO
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