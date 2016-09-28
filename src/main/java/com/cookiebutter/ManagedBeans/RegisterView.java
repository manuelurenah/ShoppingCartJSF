package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.User;
import com.cookiebutter.PersistenceHandlers.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by MEUrena on 9/21/16.
 * All rights reserved.
 */
@ManagedBean
@SessionScoped
public class RegisterView implements Serializable {

    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @Size(min = 4, max = 15)
    private String username;
    @Size(min = 4, max = 15)
    private String password;
    private boolean isAdmin;

    @EJB
    UserService userService;

    @PostConstruct
    public void init() {
        name = "";
        lastname = "";
        username = "";
        password = "";
        isAdmin = false;
    }

    public String registerNewUser() {
        User user = new User(name, lastname, username, password, isAdmin);
//        userService.add(user);
        init();
        return "index?faces-redirect=true";
    }

    public String cancel() {
        init();
        return "index?faces-redirect=true";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
