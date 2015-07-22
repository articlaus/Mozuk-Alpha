/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "EMPLOYEE_WORK_MONTH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeWorkMonth.findAll", query = "SELECT e FROM EmployeeWorkMonth e"),
    @NamedQuery(name = "EmployeeWorkMonth.findById", query = "SELECT e FROM EmployeeWorkMonth e WHERE e.id = :id"),
    @NamedQuery(name = "EmployeeWorkMonth.findByWorkedHours", query = "SELECT e FROM EmployeeWorkMonth e WHERE e.workedHours = :workedHours"),
    @NamedQuery(name = "EmployeeWorkMonth.findByFinalSalary", query = "SELECT e FROM EmployeeWorkMonth e WHERE e.finalSalary = :finalSalary"),
    @NamedQuery(name = "EmployeeWorkMonth.findByEmployee", query = "SELECT e FROM EmployeeWorkMonth e WHERE e.employeeCode = :employeeCode"),
    @NamedQuery(name = "EmployeeWorkMonth.findByWorkMonth", query = "SELECT e FROM EmployeeWorkMonth e WHERE e.workMonthsid = :workMonthsid"),
    @NamedQuery(name = "EmployeeWorkMonth.findByIsActive", query = "SELECT e FROM EmployeeWorkMonth e WHERE e.employeeCode.isActive = :isActive"),
    @NamedQuery(name = "EmployeeWorkMonth.findByEmployeeAndWorkMonth", query = "SELECT e FROM EmployeeWorkMonth e WHERE e.employeeCode=:employeeCode AND e.workMonthsid = :workMonthsid"),
})
public class EmployeeWorkMonth implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "worked_hours")
    private Integer workedHours;
    @Basic(optional = false)
    @Column(name = "final_salary")
    private double finalSalary;
    @JoinColumn(name = "Work_Monthsid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WorkMonths workMonthsid;
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Employee employeeCode;

    public EmployeeWorkMonth() {
    }

    public EmployeeWorkMonth(BigDecimal id) {
        this.id = id;
    }

    public EmployeeWorkMonth(BigDecimal id, double finalSalary) {
        this.id = id;
        this.finalSalary = finalSalary;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    public WorkMonths getWorkMonthsid() {
        return workMonthsid;
    }

    public void setWorkMonthsid(WorkMonths workMonthsid) {
        this.workMonthsid = workMonthsid;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeWorkMonth)) {
            return false;
        }
        EmployeeWorkMonth other = (EmployeeWorkMonth) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.EmployeeWorkMonth[ id=" + id + " ]";
    }
    
}
