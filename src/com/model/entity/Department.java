package com.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
public class Department {
    private String code;
    private String departmentTitle;
    private String departmentDescription;
    private Date createdDate;

    @Id
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "department_title")
    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    @Basic
    @Column(name = "department_description")
    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    @Basic
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (departmentTitle != null ? !departmentTitle.equals(that.departmentTitle) : that.departmentTitle != null)
            return false;
        if (departmentDescription != null ? !departmentDescription.equals(that.departmentDescription) : that.departmentDescription != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (departmentTitle != null ? departmentTitle.hashCode() : 0);
        result = 31 * result + (departmentDescription != null ? departmentDescription.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
