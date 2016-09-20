package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.User;
import com.cookiebutter.PersistenceHandlers.UserHandler;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.prefs.Preferences;

/**
 * Created by MEUrena on 9/18/16.
 * All rights reserved.
 */
@ManagedBean(name = "index")
@SessionScoped
public class IndexView implements Serializable {

    @PostConstruct
    public void checkFirstRun() {
        Preferences userPrefs = Preferences.userRoot();
        // userPrefs.putBoolean("first_run", true);
        Boolean isFirstRun = userPrefs.getBoolean("first_run", true);

        if (isFirstRun) {
            System.out.println("running for the first time");
            User firstUser = new User("Administrator", "", "admin", "admin", true);
            UserHandler userHandler = UserHandler.getInstance();
            try {
                userHandler.insertIntoDatabase(firstUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userPrefs.putBoolean("first_run", false);
        }
    }

    public String toIndex() { return "index.xhtml"; }
    public String toAvailableProducts() {
        return "availableProducts.xhtml";
    }
    public String toAdminPage() { return "admin.xhtml"; }
}
