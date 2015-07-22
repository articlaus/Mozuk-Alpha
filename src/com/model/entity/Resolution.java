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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author tseegii
 */
@Entity
@Table(name = "RESOLUTION")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Resolution.findAll", query = "SELECT r FROM Resolution r"),
        @NamedQuery(name = "Resolution.findByCode", query = "SELECT r FROM Resolution r WHERE r.code = :code"),
        @NamedQuery(name = "Resolution.findByCreatedDate", query = "SELECT r FROM Resolution r WHERE r.createdDate = :createdDate"),
        @NamedQuery(name = "Resolution.findByResolutionType", query = "SELECT r FROM Resolution r WHERE r.resolutionType = :resolutionType"),
})
public class Resolution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "resolution_type")
    private String resolutionType;
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Department departmentCode;
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    @ManyToOne
    private Employee employeeCode;
    @Column(name = "resolution_range")
    private int resolutionRange;


    public Resolution() {
    }

    public Resolution(String code) {
        this.code = code;
    }

    public Resolution(String code, Date createdDate, String resolutionType) {
        this.code = code;
        this.createdDate = createdDate;
        this.resolutionType = resolutionType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }

    public Department getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Department departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public int getResolutionRange() {
        return resolutionRange;
    }

    public void setResolutionRange(int resolutionRange) {
        this.resolutionRange = resolutionRange;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resolution)) {
            return false;
        }
        Resolution other = (Resolution) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Resolution[ code=" + code + " ]";
    }

}
