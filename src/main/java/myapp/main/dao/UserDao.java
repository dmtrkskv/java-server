package myapp.main.dao;

import myapp.main.models.User;
import myapp.main.utils.HibernateSessionFactoryUtil;

public class UserDao {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }
}