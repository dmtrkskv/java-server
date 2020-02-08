package myapp.main.services;

import myapp.main.dao.UserDao;
import myapp.main.models.User;
import java.util.List;

public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findById(int id) {
        return usersDao.findById(id);
    }
    public List<User> findAll() {
        return usersDao.findAll();
    }

    public void create(User user) {
        usersDao.create(user);
    }    
}