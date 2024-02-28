package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;



import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.openSessionFactory();


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        String sql = """
                CREATE TABLE IF NOT EXISTS users(
                id SERIAL PRIMARY KEY,
                name VARCHAR(100),
                lastname VARCHAR(100),
                age INT)
                """;
        try{session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();}
            e.printStackTrace();}
        finally {session.close();}

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        String sql = """
                DROP TABLE IF EXISTS users
                """;
        try{session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();}
            e.printStackTrace();}
        finally {session.close();}

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        try {session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
           if (session.getTransaction() != null) {
                session.getTransaction().rollback();}
            e.printStackTrace();}
        finally {session.close();}

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        try {session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }catch (Exception e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();}
            e.printStackTrace();}
        finally {session.close();}
    }
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        String hql = """
                FROM User
                """;
        List<User> users = new ArrayList<>();

        try {session.beginTransaction();
            users = session.createQuery(hql).getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();}
            e.printStackTrace();}
        finally {session.close();}
        return users;

    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        String hql = """
                DELETE FROM User
                """;
        try {session.beginTransaction();
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();}
            e.printStackTrace();}
        finally {session.close();}
    }
}
