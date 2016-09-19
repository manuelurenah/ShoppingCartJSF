package com.cookiebutter.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by MEUrena on 9/18/16.
 * All rights reserved.
 */

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "User.findUserByUsername",
                query = "SELECT u FROM User u WHERE u.username = :username"
        ),
        @NamedQuery(
                name = "User.findUserByUsernameAndPassword",
                query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"
        ),
        @NamedQuery(
                name = "User.findAllUsers",
                query = "select u from User u where u.username <> 'admin' order by u.firstName"
        )
})
public class User implements Serializable {

    public static String QUERY_NAME_FIND_BY_USERNAME = "User.findUserByUsername";
    public static String QUERY_NAME_FIND_BY_USERNAME_AND_PASSWORD = "User.findUserByUsernameAndPassword";
    public static String QUERY_NAME_FIND_ALL_USERS = "User.findAllUsers";

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "first_name")
    private String firstName = "";
    @Column(name = "last_name")
    private String lastName = "";
    @Column(name = "email")
    private String email = "";
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "is_administrator")
    private Boolean isAdmin = false;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
