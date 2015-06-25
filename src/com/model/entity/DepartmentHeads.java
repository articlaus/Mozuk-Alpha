package com.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "DEPARTMENT_HEADS", schema = "", catalog = "avocado")
@NamedQueries({
        @NamedQuery(name = "Department_Heads.findAll",query = "SELECT d FROM DepartmentHeads AS d"),
})
public class DepartmentHeads implements Serializable {
    private String departmentCode;
    private String employeeCode;
    private Integer id;

    @Basic
    @Column(name = "department_code")
    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Basic
    @Column(name = "employee_code")
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentHeads that = (DepartmentHeads) o;

        if (id != that.id) return false;
        if (departmentCode != null ? !departmentCode.equals(that.departmentCode) : that.departmentCode != null)
            return false;
        if (employeeCode != null ? !employeeCode.equals(that.employeeCode) : that.employeeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = departmentCode != null ? departmentCode.hashCode() : 0;
        result = 31 * result + (employeeCode != null ? employeeCode.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
