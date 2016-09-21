package com.cookiebutter.ManagedBeans;

import com.cookiebutter.Data.User;

import javax.ejb.Stateless;

/**
 * Created by MEUrena on 9/21/16.
 * All rights reserved.
 */
@Stateless
public class UserService {

    public void printUser(User u) {
        System.out.println(u.getFirstName());
        System.out.println(u.getLastName());
    }

}
