package com.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "EMPLOYEE_POSITION", schema = "", catalog = "avocado")
@NamedQueries(
        {
                @NamedQuery(name = "EmployeePosition.findAll", query = "SELECT e FROM EmployeePosition AS e "),
                @NamedQuery(name = "EmployeePosition.findByEmployeeAndIsActive", query = "SELECT e FROM EmployeePosition AS e WHERE e.employee=:employee AND e.isActive=:isActive"),
        }
)
public class EmployeePosition {
    private Integer id;
    private Integer salary;
    private String positionCode;
    private String employeeCode;
    private Boolean isActive;
    private Date createdDate;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "position_code")
    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    @Basic
    @Column(name = "employee_code")
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Basic
    @Column(name = "isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

        EmployeePosition that = (EmployeePosition) o;

        if (id != that.id) return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;
        if (positionCode != null ? !positionCode.equals(that.positionCode) : that.positionCode != null) return false;
        if (employeeCode != null ? !employeeCode.equals(that.employeeCode) : that.employeeCode != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (positionCode != null ? positionCode.hashCode() : 0);
        result = 31 * result + (employeeCode != null ? employeeCode.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    private Employee employee;
    private Position position;
    private Department department;

    @ManyToOne
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    @JoinColumn(name = "position_code", referencedColumnName = "code")
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
