package myapp.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myapp.main.config.security.UserPrincipal;
import myapp.main.dao.UserDao;
import myapp.main.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    private UserDao usersDao = new UserDao();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }        
        return new UserPrincipal(user);
    }
    
}