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
 *
 * @author tseegii
 */
@Entity
@Table(name = "OVERTIME_DATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OvertimeDates.findAll", query = "SELECT o FROM OvertimeDates o"),
    @NamedQuery(name = "OvertimeDates.findById", query = "SELECT o FROM OvertimeDates o WHERE o.id = :id"),
    @NamedQuery(name = "OvertimeDates.findByWorkDate", query = "SELECT o FROM OvertimeDates o WHERE o.workDate = :workDate"),
    @NamedQuery(name = "OvertimeDates.findByStartTime", query = "SELECT o FROM OvertimeDates o WHERE o.startTime = :startTime"),
    @NamedQuery(name = "OvertimeDates.findByEndTime", query = "SELECT o FROM OvertimeDates o WHERE o.endTime = :endTime"),
    @NamedQuery(name = "OvertimeDates.findByHours", query = "SELECT o FROM OvertimeDates o WHERE o.hours = :hours"),
    @NamedQuery(name = "OvertimeDates.findByIsHoliday", query = "SELECT o FROM OvertimeDates o WHERE o.isHoliday = :isHoliday"),
    @NamedQuery(name = "OvertimeDates.findByOvertime", query = "SELECT o FROM OvertimeDates o WHERE o.overtimeid = :overtimeid"),

})
public class OvertimeDates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)

    @Column(name = "work_date")
    @Temporal(TemporalType.DATE)
    private Date workDate;
    @Basic
    @Column(name = "start_time")
    private String startTime;
    @Basic
    @Column(name = "end_time")
    private String endTime;
    @Basic(optional = false)
    @Column(name = "hours")
    private double hours;
    @Basic(optional = false)

    @Column(name = "isHoliday")
    private boolean isHoliday;
    @JoinColumn(name = "Overtimeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Overtime overtimeid;

    public OvertimeDates() {
    }

    public OvertimeDates(BigDecimal id) {
        this.id = id;
    }

    public OvertimeDates(BigDecimal id, Date workDate, int hours, boolean isHoliday) {
        this.id = id;
        this.workDate = workDate;
        this.hours = hours;
        this.isHoliday = isHoliday;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public boolean getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public Overtime getOvertimeid() {
        return overtimeid;
    }

    public void setOvertimeid(Overtime overtimeid) {
        this.overtimeid = overtimeid;
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
        if (!(object instanceof OvertimeDates)) {
            return false;
        }
        OvertimeDates other = (OvertimeDates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.OvertimeDates[ id=" + id + " ]";
    }

}
