package myapp.main.models;

import javax.persistence.*;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.setPassword(password);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encoder().encode(password);
    }    

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}