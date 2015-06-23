package com.model.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "Leave_Absence", schema = "", catalog = "avocado")
public class LeaveAbsence {
    private int id;
    private String employeeId;
    private int leaveTypeId;
    private Date startDate;
    private Date endDate;
    private int numberOfDays;
    private boolean isPaid;
    private int workMonthsid;
    private Date createdDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "employee_id")
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "leave_type_id")
    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "number_of_days")
    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Basic
    @Column(name = "isPaid")
    public boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Basic
    @Column(name = "Work_Monthsid")
    public int getWorkMonthsid() {
        return workMonthsid;
    }

    public void setWorkMonthsid(int workMonthsid) {
        this.workMonthsid = workMonthsid;
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

        LeaveAbsence that = (LeaveAbsence) o;

        if (id != that.id) return false;
        if (leaveTypeId != that.leaveTypeId) return false;
        if (numberOfDays != that.numberOfDays) return false;
        if (isPaid != that.isPaid) return false;
        if (workMonthsid != that.workMonthsid) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + leaveTypeId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + numberOfDays;
        result = 31 * result + (isPaid ? 1 : 0);
        result = 31 * result + workMonthsid;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
