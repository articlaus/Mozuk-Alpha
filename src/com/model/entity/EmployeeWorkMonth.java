package com.model.entity;

import javax.persistence.*;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "EMPLOYEE_WORK_MONTH", schema = "", catalog = "avocado")
@NamedQueries({
        @NamedQuery(name = "EmployeeWorkMonth.findAll",query = "SELECT e FROM EmployeeWorkMonth AS e"),
})
public class EmployeeWorkMonth {
    private Integer id;
    private Integer workMonthsid;
    private Integer workedHours;
    private Integer finalSalary;
    private String employeeCode;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Work_Monthsid")
    public Integer getWorkMonthsid() {
        return workMonthsid;
    }

    public void setWorkMonthsid(Integer workMonthsid) {
        this.workMonthsid = workMonthsid;
    }

    @Basic
    @Column(name = "worked_hours")
    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    @Basic
    @Column(name = "final_salary")
    public Integer getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(Integer finalSalary) {
        this.finalSalary = finalSalary;
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

        EmployeeWorkMonth that = (EmployeeWorkMonth) o;

        if (id != that.id) return false;
        if (workMonthsid != that.workMonthsid) return false;
        if (finalSalary != that.finalSalary) return false;
        if (workedHours != null ? !workedHours.equals(that.workedHours) : that.workedHours != null) return false;
        if (employeeCode != null ? !employeeCode.equals(that.employeeCode) : that.employeeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + workMonthsid;
        result = 31 * result + (workedHours != null ? workedHours.hashCode() : 0);
        result = 31 * result + finalSalary;
        result = 31 * result + (employeeCode != null ? employeeCode.hashCode() : 0);
        return result;
    }
}
