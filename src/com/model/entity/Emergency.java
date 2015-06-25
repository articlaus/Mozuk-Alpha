/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "EMERGENCY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emergency.findAll", query = "SELECT e FROM Emergency e"),
    @NamedQuery(name = "Emergency.findById", query = "SELECT e FROM Emergency e WHERE e.id = :id"),
    @NamedQuery(name = "Emergency.findByCellNumber", query = "SELECT e FROM Emergency e WHERE e.cellNumber = :cellNumber"),
    @NamedQuery(name = "Emergency.findByName", query = "SELECT e FROM Emergency e WHERE e.name = :name"),
    @NamedQuery(name = "Emergency.findByRelationship", query = "SELECT e FROM Emergency e WHERE e.relationship = :relationship")})
public class Emergency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "cell_number")
    private String cellNumber;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "relationship")
    private String relationship;
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Employee employeeCode;

    public Emergency() {
    }

    public Emergency(BigDecimal id) {
        this.id = id;
    }

    public Emergency(BigDecimal id, String cellNumber, String name, String relationship) {
        this.id = id;
        this.cellNumber = cellNumber;
        this.name = name;
        this.relationship = relationship;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
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
        if (!(object instanceof Emergency)) {
            return false;
        }
        Emergency other = (Emergency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Emergency[ id=" + id + " ]";
    }
    
}
