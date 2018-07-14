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

        List<User> allUsers = adapter.getAllUsers();
        allUsers.forEach(System.out::println);
        List<Family> allFamilies = adapter.retrieveFamilies(allUsers);

        User user = Teacher.builder().name("new").email("new").build();
        adapter.saveOrUpdateUser(user);
        allUsers = adapter.getAllUsers();
        allUsers.forEach(System.out::println);
    }



    @Override
    public LinkedList<Family> getFamilies() {
        return null;
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
        return null;
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

    @Override
    public LinkedList<Post> getPosts(UsersList users) {
        return null;
    }

    @Override
    public void addFamily(Family family) {

    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    //Two above methods combined in one
    public void saveOrUpdateUser(User user) {
        Transaction tx = null;
        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public void addHomeworkReply(String homeworkId, HomeworkReply reply) {

    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public void updateHomeworkReply(HomeworkReply reply) {

    }

    @Override
    public void deleteFamily(Family family) {

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public void deletePost(String postID) {

    }
}