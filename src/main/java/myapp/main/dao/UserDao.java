package myapp.main.dao;

import myapp.main.models.User;
import myapp.main.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDao {

    public User findById(int id) {
        return this.openSession().get(User.class, id);
    }

    @SuppressWarnings("unchecked") 
    public List<User> findAll() {
        return (List<User>) this.openSession().createQuery("From User").list();
    }

    public void create(User user) {
        Session session = this.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    private Session openSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
}