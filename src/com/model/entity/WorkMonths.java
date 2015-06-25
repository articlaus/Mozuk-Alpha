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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "WORK_MONTHS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkMonths.findAll", query = "SELECT w FROM WorkMonths w"),
    @NamedQuery(name = "WorkMonths.findById", query = "SELECT w FROM WorkMonths w WHERE w.id = :id"),
    @NamedQuery(name = "WorkMonths.findByYear", query = "SELECT w FROM WorkMonths w WHERE w.year = :year"),
    @NamedQuery(name = "WorkMonths.findByMonth", query = "SELECT w FROM WorkMonths w WHERE w.month = :month"),
    @NamedQuery(name = "WorkMonths.findByTotalWorkHours", query = "SELECT w FROM WorkMonths w WHERE w.totalWorkHours = :totalWorkHours"),
    @NamedQuery(name = "WorkMonths.findByIsLocked", query = "SELECT w FROM WorkMonths w WHERE w.isLocked = :isLocked"),
    @NamedQuery(name = "WorkMonths.findByCreatedDate", query = "SELECT w FROM WorkMonths w WHERE w.createdDate = :createdDate")})
public class WorkMonths implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "year")
    private Integer year;
    @Column(name = "month")
    private Integer month;
    @Column(name = "total_work_hours")
    private Integer totalWorkHours;
    @Column(name = "isLocked")
    private Boolean isLocked;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workMonthsid")
    private List<EmployeeWorkMonth> employeeWorkMonthList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workMonthsid")
    private List<LeaveAbsence> leaveAbsenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workMonthsid")
    private List<Overtime> overtimeList;

    public WorkMonths() {
    }

    public WorkMonths(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTotalWorkHours() {
        return totalWorkHours;
    }

    public void setTotalWorkHours(Integer totalWorkHours) {
        this.totalWorkHours = totalWorkHours;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public List<EmployeeWorkMonth> getEmployeeWorkMonthList() {
        return employeeWorkMonthList;
    }

    public void setEmployeeWorkMonthList(List<EmployeeWorkMonth> employeeWorkMonthList) {
        this.employeeWorkMonthList = employeeWorkMonthList;
    }

    @XmlTransient
    public List<LeaveAbsence> getLeaveAbsenceList() {
        return leaveAbsenceList;
    }

    public void setLeaveAbsenceList(List<LeaveAbsence> leaveAbsenceList) {
        this.leaveAbsenceList = leaveAbsenceList;
    }

    @XmlTransient
    public List<Overtime> getOvertimeList() {
        return overtimeList;
    }

    public void setOvertimeList(List<Overtime> overtimeList) {
        this.overtimeList = overtimeList;
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
        if (!(object instanceof WorkMonths)) {
            return false;
        }
        WorkMonths other = (WorkMonths) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.WorkMonths[ id=" + id + " ]";
    }
    
}
