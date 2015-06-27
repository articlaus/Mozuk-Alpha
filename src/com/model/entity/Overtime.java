/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "OVERTIME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Overtime.findAll", query = "SELECT o FROM Overtime o"),
    @NamedQuery(name = "Overtime.findById", query = "SELECT o FROM Overtime o WHERE o.id = :id"),
    @NamedQuery(name = "Overtime.findByIsHoliday", query = "SELECT o FROM Overtime o WHERE o.isHoliday = :isHoliday"),
    @NamedQuery(name = "Overtime.findByEmployee", query = "SELECT o FROM Overtime o WHERE o.employeeId = :employeeId"),
    @NamedQuery(name = "Overtime.findByWorkMonthsId", query = "SELECT o FROM Overtime o WHERE o.workMonthsid = :workMonthsid"),
    @NamedQuery(name = "Overtime.findByEmployeeAndWorkMonthsId", query = "SELECT o FROM Overtime o WHERE o.employeeId=:employeeId AND o.workMonthsid = :workMonthsid"),
})
public class Overtime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "isHoliday")
    private boolean isHoliday;
    @Lob
    @Column(name = "reason")
    private String reason;
    @OneToMany( mappedBy = "overtimeid")
    private List<OvertimeDates> overtimeDatesList;
    @JoinColumn(name = "Work_Monthsid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WorkMonths workMonthsid;
    @JoinColumn(name = "employee_id", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Employee employeeId;

    public Overtime() {
    }

    public Overtime(BigDecimal id) {
        this.id = id;
    }

    public Overtime(BigDecimal id, boolean isHoliday) {
        this.id = id;
        this.isHoliday = isHoliday;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public boolean getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @XmlTransient
    public List<OvertimeDates> getOvertimeDatesList() {
        return overtimeDatesList;
    }

    public void setOvertimeDatesList(List<OvertimeDates> overtimeDatesList) {
        this.overtimeDatesList = overtimeDatesList;
    }

    public WorkMonths getWorkMonthsid() {
        return workMonthsid;
    }

    public void setWorkMonthsid(WorkMonths workMonthsid) {
        this.workMonthsid = workMonthsid;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof Overtime)) {
            return false;
        }
        Overtime other = (Overtime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Overtime[ id=" + id + " ]";
    }
    
}
