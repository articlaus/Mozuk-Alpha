/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "EMPLOYEE_POSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeePosition.findAll", query = "SELECT e FROM EmployeePosition e"),
    @NamedQuery(name = "EmployeePosition.findById", query = "SELECT e FROM EmployeePosition e WHERE e.id = :id"),
    @NamedQuery(name = "EmployeePosition.findBySalary", query = "SELECT e FROM EmployeePosition e WHERE e.salary = :salary"),
    @NamedQuery(name = "EmployeePosition.findByCreatedDate", query = "SELECT e FROM EmployeePosition e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EmployeePosition.findByStartDate", query = "SELECT e FROM EmployeePosition e WHERE e.startDate = :startDate"),
    @NamedQuery(name = "EmployeePosition.findByEndDate", query = "SELECT e FROM EmployeePosition e WHERE e.endDate = :endDate"),
    @NamedQuery(name = "EmployeePosition.findByIsActive", query = "SELECT e FROM EmployeePosition e WHERE e.isActive = :isActive")})
public class EmployeePosition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "isActive")
    private boolean isActive;
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Department departmentCode;
    @JoinColumn(name = "position_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Position positionCode;
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Employee employeeCode;

    public EmployeePosition() {
    }

    public EmployeePosition(BigDecimal id) {
        this.id = id;
    }

    public EmployeePosition(BigDecimal id, Date startDate, boolean isActive) {
        this.id = id;
        this.startDate = startDate;
        this.isActive = isActive;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Department getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Department departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Position getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Position positionCode) {
        this.positionCode = positionCode;
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
        if (!(object instanceof EmployeePosition)) {
            return false;
        }
        EmployeePosition other = (EmployeePosition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.EmployeePosition[ id=" + id + " ]";
    }
    
}
