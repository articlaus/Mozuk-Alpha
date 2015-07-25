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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author tseegii
 */
@Entity
@Table(name = "DEPARTMENT_HEADS")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DepartmentHeads.findAll", query = "SELECT d FROM DepartmentHeads d"),
        @NamedQuery(name = "DepartmentHeads.findById", query = "SELECT d FROM DepartmentHeads d WHERE d.id = :id"),
        @NamedQuery(name = "DepartmentHeads.findByDepartmentCode", query = "SELECT d FROM DepartmentHeads d WHERE d.id = :id"),
        @NamedQuery(name = "DepartmentHeads.findByIsActive", query = "SELECT d FROM DepartmentHeads d WHERE d.isActive = :isActive"),
        @NamedQuery(name = "DepartmentHeads.findByDepartmentCodeAndIsActive", query = "SELECT d FROM DepartmentHeads d WHERE d.departmentCode=:departmentCode AND d.isActive = :isActive"),
        @NamedQuery(name = "DepartmentHeads.findByDepartmentCodeAndEmployeeCodeAndIsActive", query = "SELECT d FROM DepartmentHeads d WHERE d.departmentCode=:departmentCode AND d.employeeCode=:employeeCode AND d.isActive = :isActive"),

})
public class DepartmentHeads implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic
    @Column(name = "employee_code")
    private String employeeCode;
    @Basic
    @Column(name = "department_code")
    private String departmentCode;

    @Basic
    @Column(name = "isActive")
    private boolean isActive;

    public DepartmentHeads() {
    }

    public DepartmentHeads(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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
        if (!(object instanceof DepartmentHeads)) {
            return false;
        }
        DepartmentHeads other = (DepartmentHeads) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.DepartmentHeads[ id=" + id + " ]";
    }

}
