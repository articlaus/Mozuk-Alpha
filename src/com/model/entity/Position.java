/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "POSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
    @NamedQuery(name = "Position.findByCode", query = "SELECT p FROM Position p WHERE p.code = :code"),
    @NamedQuery(name = "Position.findByPositionTitle", query = "SELECT p FROM Position p WHERE p.positionTitle = :positionTitle"),
    @NamedQuery(name = "Position.findByCreatedDate", query = "SELECT p FROM Position p WHERE p.createdDate = :createdDate")})
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "code")
    private String code;
    @Basic(optional = false)

    @Column(name = "position_title")
    private String positionTitle;
    @Basic(optional = false)

    @Lob
    @Column(name = "position_description")
    private String positionDescription;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @OneToMany( mappedBy = "positionCode")
    private List<Resume> resumeList;
    @OneToMany( mappedBy = "positionCode")
    private List<EmployeePosition> employeePositionList;

    public Position() {
    }

    public Position(String code) {
        this.code = code;
    }

    public Position(String code, String positionTitle, String positionDescription) {
        this.code = code;
        this.positionTitle = positionTitle;
        this.positionDescription = positionDescription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public List<Resume> getResumeList() {
        return resumeList;
    }

    public void setResumeList(List<Resume> resumeList) {
        this.resumeList = resumeList;
    }

    @XmlTransient
    public List<EmployeePosition> getEmployeePositionList() {
        return employeePositionList;
    }

    public void setEmployeePositionList(List<EmployeePosition> employeePositionList) {
        this.employeePositionList = employeePositionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Position[ code=" + code + " ]";
    }
    
}
