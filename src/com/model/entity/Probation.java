/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "PROBATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Probation.findAll", query = "SELECT p FROM Probation p"),
    @NamedQuery(name = "Probation.findById", query = "SELECT p FROM Probation p WHERE p.id = :id"),
    @NamedQuery(name = "Probation.findByProbationReason", query = "SELECT p FROM Probation p WHERE p.probationReason = :probationReason"),
    @NamedQuery(name = "Probation.findByStartDate", query = "SELECT p FROM Probation p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Probation.findByEndDate", query = "SELECT p FROM Probation p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Probation.findByIsActive", query = "SELECT p FROM Probation p WHERE p.isActive = :isActive"),
    @NamedQuery(name = "Probation.findByCreatedDate", query = "SELECT p FROM Probation p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Probation.findByEmployee", query = "SELECT p FROM Probation p WHERE p.employeeCode = :employeeCode"),
    @NamedQuery(name = "Probation.findByDepartment", query = "SELECT p FROM Probation p WHERE p.departmentCode = :departmentCode"),
})
public class Probation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "probation_reason")
    private String probationReason;
    @Basic(optional = false)

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "isActive")
    private boolean isActive;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Employee employeeCode;
    @JoinColumn(name = "department_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Department departmentCode;

    @Column(name = "deduction_percent")
    private Double deductionPercent;

    public Probation() {
    }

    public Probation(BigDecimal id) {
        this.id = id;
    }

    public Probation(BigDecimal id, String probationReason, Date startDate, Date endDate, boolean isActive, Date createdDate) {
        this.id = id;
        this.probationReason = probationReason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getProbationReason() {
        return probationReason;
    }

    public void setProbationReason(String probationReason) {
        this.probationReason = probationReason;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Department getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Department departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Double getDeductionPercent() {
        return deductionPercent;
    }

    public void setDeductionPercent(Double deductionPercent) {
        this.deductionPercent = deductionPercent;
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
        if (!(object instanceof Probation)) {
            return false;
        }
        Probation other = (Probation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Probation[ id=" + id + " ]";
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
