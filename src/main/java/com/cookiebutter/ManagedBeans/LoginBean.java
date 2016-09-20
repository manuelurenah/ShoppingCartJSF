package com.cookiebutter.ManagedBeans;

import com.cookiebutter.PersistenceHandlers.UserHandler;
import org.primefaces.context.RequestContext;

import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;
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

    public String login(ActionEvent event) {
        FacesMessage message = null;
        boolean loggedIn = false;

        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);

        return "index?faces-redirect=true";
    }

    public String register() {
        return "/user/add/register?faces-redirect=true";
    }

}
