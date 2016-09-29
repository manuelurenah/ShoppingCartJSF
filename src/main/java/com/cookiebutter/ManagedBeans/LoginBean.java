package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.User;
import com.cookiebutter.PersistenceHandlers.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by MEUrena on 9/20/16.
 * All rights reserved.
 */

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @NotNull
    private String username;
    @NotNull
    private String password;
    private User current;

    @EJB
    UserService userService;

    @PostConstruct
    public void init() {

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

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public String login() {
        User user = userService.getByUsername(username);

        if (("admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password)) || (user != null && user.getPassword().equals(password))) {
            userService.setCurrentUser(user);
            return "availableProducts?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Incorrect username and/or password");
            FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);

            return null;
        }
    }

    public String register() {
        return "register?faces-redirect=true";
    }

}
