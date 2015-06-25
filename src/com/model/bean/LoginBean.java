package com.model.bean;

import com.model.entity.Users;
import com.model.util.BaseEJB;
import com.model.util.PasswordUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class LoginBean extends BaseEJB {

    public Users checkLogin(String username, String password) {
        Users user=getEm().find(Users.class, username);
        if (user != null) {
            if (PasswordUtil.checkPassword(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
