package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.setUpHibernate();
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String SQL_CREATE = "CREATE TABLE user " +
                "(id INTEGER auto_increment, " +
                "name VARCHAR(32) not null, " +
                "last_name VARCHAR(32) not null, " +
                "age INTEGER(3), " +
                "constraint `PRIMARY` " +
                "PRIMARY KEY (id))";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(SQL_CREATE).executeUpdate();
        } catch (HibernateException ignored) {}
    }

    @Override
    public void dropUsersTable() {
        String SQL_DROP = "DROP TABLE user";
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(SQL_DROP).executeUpdate();
        } catch (PersistenceException ignored) {}
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();

        } catch (PersistenceException ignored) {}
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (PersistenceException ignored) {}
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> criteria = session.getCriteriaBuilder().createQuery(User.class);
            criteria.from(User.class);
            return session.createQuery(criteria).getResultList();
        } catch (PersistenceException ignored) {}
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = getAllUsers();
            users.forEach(session::delete);
            session.getTransaction().commit();
        } catch (PersistenceException ignored) {}
    }
}
