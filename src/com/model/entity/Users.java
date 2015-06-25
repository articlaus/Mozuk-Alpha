/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByIsLocked", query = "SELECT u FROM Users u WHERE u.isLocked = :isLocked"),
    @NamedQuery(name = "Users.findByCreatedDate", query = "SELECT u FROM Users u WHERE u.createdDate = :createdDate")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)

    @Column(name = "isLocked")
    private boolean isLocked;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserRoles userRoleId;
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Employee employeeCode;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public Users(String username, String password, boolean isLocked) {
        this.username = username;
        this.password = password;
        this.isLocked = isLocked;
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

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserRoles getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(UserRoles userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Users[ username=" + username + " ]";
    }
    
}
