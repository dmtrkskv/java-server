package myapp.main.dao;

import myapp.main.models.User;
import myapp.main.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    public void create(User user) {
        Session session = this.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User findById(int id) {
        return this.openSession().get(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public User findByUsername(String username) {
        Query<User> query = this.openSession().createQuery("FROM User u where u.username = :username");
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) this.openSession().createQuery("From User").list();
    }

    public void update(User user) {
        Session session = this.openSession();

        User foundUser = session.get(User.class, user.getId());
        Transaction tx1 = session.beginTransaction();
        // Обновление части полей, чтобы не затереть роль
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());

        session.update(foundUser);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = this.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    private Session openSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
}