package br.com.jobem.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity(name = "tbuser")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    private String email;

    @Size(min = 8)
    private String password;

    private int levelAccess;

    public User(
            String email,
            String password,
            int levelAccess
    ) {
        this.email = email;
        this.password = password;
        this.levelAccess = levelAccess;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLevelAccess() {
        return levelAccess;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevelAccess(int levelAccess) {
        this.levelAccess = levelAccess;
    }
}
