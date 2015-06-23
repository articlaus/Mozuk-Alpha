package com.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
public class Overtime {
    private long id;
    private String employeeId;
    private boolean isHoliday;
    private String reason;
    private int workMonthsid;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "isHoliday")
    public boolean isHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "Work_Monthsid")
    public int getWorkMonthsid() {
        return workMonthsid;
    }

    public void setWorkMonthsid(int workMonthsid) {
        this.workMonthsid = workMonthsid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Overtime overtime = (Overtime) o;

        if (id != overtime.id) return false;
        if (isHoliday != overtime.isHoliday) return false;
        if (workMonthsid != overtime.workMonthsid) return false;
        if (employeeId != null ? !employeeId.equals(overtime.employeeId) : overtime.employeeId != null) return false;
        if (reason != null ? !reason.equals(overtime.reason) : overtime.reason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (isHoliday ? 1 : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + workMonthsid;
        return result;
    }
}
