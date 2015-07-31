/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


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
        @NamedQuery(name = "Resolution.findByEmployee", query = "SELECT r FROM Resolution r WHERE r.employeeCode = :employeeCode"),
        @NamedQuery(name = "Resolution.findByDepartment", query = "SELECT r FROM Resolution r WHERE r.departmentCode = :departmentCode"),
        @NamedQuery(name = "Resolution.findByResolutionRange", query = "SELECT r FROM Resolution r WHERE r.resolutionRange = :resolutionRange"),


})
public class Resolution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @JoinColumn(name = "resolution_type_id", referencedColumnName = "id")
    @ManyToOne
    private ResolutionType resolutionType;
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

    public Resolution(String code, Date createdDate, ResolutionType resolutionType) {
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


    public ResolutionType getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(ResolutionType resolutionType) {
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

    @Transient
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }


}
