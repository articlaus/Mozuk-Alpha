package com.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "Overtime_dates", schema = "", catalog = "avocado")
public class OvertimeDates {
    private int id;
    private Date workDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private int hours;
    private boolean isHoliday;
    private long overtimeid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "work_date")
    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "hours")
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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
    @Column(name = "Overtimeid")
    public long getOvertimeid() {
        return overtimeid;
    }

    public void setOvertimeid(long overtimeid) {
        this.overtimeid = overtimeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OvertimeDates that = (OvertimeDates) o;

        if (id != that.id) return false;
        if (hours != that.hours) return false;
        if (isHoliday != that.isHoliday) return false;
        if (overtimeid != that.overtimeid) return false;
        if (workDate != null ? !workDate.equals(that.workDate) : that.workDate != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (workDate != null ? workDate.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + hours;
        result = 31 * result + (isHoliday ? 1 : 0);
        result = 31 * result + (int) (overtimeid ^ (overtimeid >>> 32));
        return result;
    }
}
