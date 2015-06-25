package com.model.bean;

import com.model.entity.Users;
import com.model.util.BaseEJB;
import com.model.util.PasswordUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class UserBean extends BaseEJB {


    public Users findByUsername(String username) {
        return getEm().find(Users.class, username);
    }

    public Users save(Users users) {
        try {
            String encPass = PasswordUtil.passwordEncryptor(users.getPassword());
            users.setPassword(encPass);
            users.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(users);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Users update(Users users) {
        try {
            users = getEm().merge(users);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(String username) {
        try {
            getEm().remove(getEm().getReference(Users.class, username));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Users user = getEm().find(Users.class, username);
        if (user != null) {
            if (PasswordUtil.checkPassword(oldPassword, user.getPassword())) {
                user.setPassword(PasswordUtil.passwordEncryptor(newPassword));
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public List<Users> findAll() {
        List<Users> users = getEm().createNamedQuery("Users.findAll", Users.class).getResultList();
        return users;
    }


}
