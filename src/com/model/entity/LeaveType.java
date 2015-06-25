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
@Table(name = "LEAVE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveType.findAll", query = "SELECT l FROM LeaveType l"),
    @NamedQuery(name = "LeaveType.findById", query = "SELECT l FROM LeaveType l WHERE l.id = :id"),
    @NamedQuery(name = "LeaveType.findByLeaveType", query = "SELECT l FROM LeaveType l WHERE l.leaveType = :leaveType")})
public class LeaveType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "leave_type")
    private String leaveType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaveTypeId")
    private List<LeaveAbsence> leaveAbsenceList;

    public LeaveType() {
    }

    public LeaveType(BigDecimal id) {
        this.id = id;
    }

    public LeaveType(BigDecimal id, String leaveType) {
        this.id = id;
        this.leaveType = leaveType;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    @XmlTransient
    public List<LeaveAbsence> getLeaveAbsenceList() {
        return leaveAbsenceList;
    }

    public void setLeaveAbsenceList(List<LeaveAbsence> leaveAbsenceList) {
        this.leaveAbsenceList = leaveAbsenceList;
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
        if (!(object instanceof LeaveType)) {
            return false;
        }
        LeaveType other = (LeaveType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.LeaveType[ id=" + id + " ]";
    }
    
}
