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
 * @author tseegii
 */
@Entity
@Table(name = "LEAVE_ABSENCE")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LeaveAbsence.findAll", query = "SELECT l FROM LeaveAbsence l"),
        @NamedQuery(name = "LeaveAbsence.findById", query = "SELECT l FROM LeaveAbsence l WHERE l.id = :id"),
        @NamedQuery(name = "LeaveAbsence.findByStartDate", query = "SELECT l FROM LeaveAbsence l WHERE l.startDate = :startDate"),
        @NamedQuery(name = "LeaveAbsence.findByEndDate", query = "SELECT l FROM LeaveAbsence l WHERE l.endDate = :endDate"),
        @NamedQuery(name = "LeaveAbsence.findByNumberOfDays", query = "SELECT l FROM LeaveAbsence l WHERE l.numberOfDays = :numberOfDays"),
        @NamedQuery(name = "LeaveAbsence.findByIsPaid", query = "SELECT l FROM LeaveAbsence l WHERE l.isPaid = :isPaid"),
        @NamedQuery(name = "LeaveAbsence.findByCreatedDate", query = "SELECT l FROM LeaveAbsence l WHERE l.createdDate = :createdDate"),
        @NamedQuery(name = "LeaveAbsence.findByEmployee", query = "SELECT l FROM LeaveAbsence l WHERE l.employeeCode = :employeeId"),
        @NamedQuery(name = "LeaveAbsence.findByLeaveType", query = "SELECT l FROM LeaveAbsence l WHERE l.leaveTypeId = :leaveTypeId"),
        @NamedQuery(name = "LeaveAbsence.findByWorkMonths", query = "SELECT l FROM LeaveAbsence l WHERE l.workMonthsid = :workMonthsid"),
        @NamedQuery(name = "LeaveAbsence.findByWorkMonthsAndIsActive", query = "SELECT l FROM LeaveAbsence l WHERE l.workMonthsid = :workMonthsid AND l.employeeCode.isActive=:isActive"),
        @NamedQuery(name = "LeaveAbsence.findByEmployeeAndLeaveType", query = "SELECT l FROM LeaveAbsence l WHERE l.employeeCode=:employeeId AND l.leaveTypeId = :leaveTypeId"),
        @NamedQuery(name = "LeaveAbsence.findByEmployeeAndWorkMonths", query = "SELECT l FROM LeaveAbsence l WHERE l.employeeCode=:employeeId AND l.workMonthsid = :workMonthsid"),
        @NamedQuery(name = "LeaveAbsence.findByStartDateAndEndDate", query = "SELECT l FROM LeaveAbsence l WHERE l.startDate=:startDate AND l.endDate = :endDate"),

})
public class LeaveAbsence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "number_of_days")
    private int numberOfDays;
    @Basic(optional = false)
    @Column(name = "isPaid")
    private boolean isPaid;
    @Basic(optional = true)
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "leave_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LeaveType leaveTypeId;
    @JoinColumn(name = "Work_Monthsid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WorkMonths workMonthsid;
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Employee employeeCode;
    @Column(name = "salary_received")
    private Double salaryReceived;

    public LeaveAbsence() {
    }

    public LeaveAbsence(BigDecimal id) {
        this.id = id;
    }

    public LeaveAbsence(BigDecimal id, Date startDate, Date endDate, int numberOfDays, boolean isPaid, Date createdDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfDays = numberOfDays;
        this.isPaid = isPaid;
        this.createdDate = createdDate;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public LeaveType getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(LeaveType leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalaryReceived() {
        return salaryReceived;
    }

    public void setSalaryReceived(Double salaryReceived) {
        this.salaryReceived = salaryReceived;
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
        if (!(object instanceof LeaveAbsence)) {
            return false;
        }
        LeaveAbsence other = (LeaveAbsence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.LeaveAbsence[ id=" + id + " ]";
    }

}
