package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.User;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MEUrena on 9/21/16.
 * All rights reserved.
 */
@Singleton
public class UserService {

    public static int IdCounter = 0;

    private List<User> users;
    private User currentUser;

    public UserService() {  }

    //    @Override
    public Boolean add(User item) {
        Boolean added = false;
        if (!users.contains(item)) {
            added = users.add(item);
            setUsers(users);
        }

        return added;
    }

    //    @Override
    public User update(User item) {
        deleteById(item.getId());
        add(item);
        return null;
    }

    //    @Override
    public User getById(int id) {
        for (User u: users) {
            if(u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public User getByUsername(String username) {
        for (User u: users) {
            if (u.getUsername() == username) {
                return u;
            }
        }

        return null;
    }

    //    @Override
    public Boolean deleteById(int id) {
        for (User u: users) {
            if(u.getId() == id) {
                users.remove(u);
                setUsers(users);
                return true;
            }
        }
        return false;
    }

    //    @Override
    public Boolean delete(User item) {
        Boolean deleted =  users.remove(item);
        if(deleted) {
            setUsers(users);
        }

        return deleted;
    }
    public List<User> getUsers() {
        users = (ArrayList<User>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("users");
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("users", users);
    }

    public User getCurrentUser() {
        currentUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
        if (currentUser == null) {
            currentUser = new User();
        }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
    }
}
