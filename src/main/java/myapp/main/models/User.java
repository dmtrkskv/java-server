package myapp.main.models;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    String name;

    public User() {
    }

    public User(String name) {   
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}