package com.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "OVERTIME_DATES", schema = "", catalog = "avocado")
@NamedQueries({
        @NamedQuery(name = "Overtime_dates.findAll",query = "SELECT o FROM OvertimeDates AS o"),
})
public class OvertimeDates implements Serializable {
    private Integer id;
    private Date workDate;
    private Date startTime;
    private Date endTime;
    private Integer hours;
    private boolean isHoliday;
    private long overtimeid;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "work_date")
    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "hours")
    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Basic
    @Column(name = "isHoliday")
    public boolean getIsHoliday() {
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
