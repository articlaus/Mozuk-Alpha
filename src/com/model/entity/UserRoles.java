package com.model.entity;

import javax.persistence.*;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "USER_ROLES", schema = "", catalog = "avocado")
@NamedQueries({
        @NamedQuery(name = "User_Roles.findAll",query = "SELECT u FROM UserRoles AS u"),
})
public class UserRoles {
    private Integer id;
    private String userRole;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_role")
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (id != userRoles.id) return false;
        if (userRole != null ? !userRole.equals(userRoles.userRole) : userRoles.userRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }
}
