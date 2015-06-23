package com.model.entity;

import javax.persistence.*;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "employee_to_document", schema = "", catalog = "avocado")
public class EmployeeToDocument {
    private int id;
    private int documentsid;
    private String employeeCode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Documentsid")
    public int getDocumentsid() {
        return documentsid;
    }

    public void setDocumentsid(int documentsid) {
        this.documentsid = documentsid;
    }

    @Basic
    @Column(name = "employee_code")
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeToDocument that = (EmployeeToDocument) o;

        if (id != that.id) return false;
        if (documentsid != that.documentsid) return false;
        if (employeeCode != null ? !employeeCode.equals(that.employeeCode) : that.employeeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + documentsid;
        result = 31 * result + (employeeCode != null ? employeeCode.hashCode() : 0);
        return result;
    }
}
