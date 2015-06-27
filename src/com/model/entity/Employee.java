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
import javax.persistence.OneToOne;
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
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByCode", query = "SELECT e FROM Employee e WHERE e.code = :code"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employee.findBySurname", query = "SELECT e FROM Employee e WHERE e.surname = :surname"),
    @NamedQuery(name = "Employee.findByCellNumber", query = "SELECT e FROM Employee e WHERE e.cellNumber = :cellNumber"),
    @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender"),
    @NamedQuery(name = "Employee.findByDob", query = "SELECT e FROM Employee e WHERE e.dob = :dob"),
    @NamedQuery(name = "Employee.findByMaritalStatus", query = "SELECT e FROM Employee e WHERE e.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "Employee.findByFamilySize", query = "SELECT e FROM Employee e WHERE e.familySize = :familySize"),
    @NamedQuery(name = "Employee.findBySocialSecurityNumber", query = "SELECT e FROM Employee e WHERE e.socialSecurityNumber = :socialSecurityNumber"),
    @NamedQuery(name = "Employee.findByCreatedDate", query = "SELECT e FROM Employee e WHERE e.createdDate = :createdDate")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Lob
    @Column(name = "portrait")
    private byte[] portrait;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "cell_number")
    private String cellNumber;
    @Lob
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "family_size")
    private Integer familySize;
    @Basic(optional = false)
    @Column(name = "social_security_number")
    private String socialSecurityNumber;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic
    @Column(name = "ISACTIVE")
    private boolean isActive;
    @OneToOne( mappedBy = "employeeCode")
    private DepartmentHeads departmentHeads;
    @OneToMany( mappedBy = "employeeCode")
    private List<EmployeeWorkMonth> employeeWorkMonthList;
    @OneToMany( mappedBy = "employeeCode")
    private List<Emergency> emergencyList;
    @OneToMany( mappedBy = "employeeCode")
    private List<EmployeePosition> employeePositionList;
    @OneToMany(mappedBy = "employeeId")
    private List<Resolution> resolutionList;
    @OneToMany( mappedBy = "employeeId")
    private List<LeaveAbsence> leaveAbsenceList;
    @OneToMany( mappedBy = "employeeId")
    private List<Overtime> overtimeList;
    @OneToMany( mappedBy = "employeeCode")
    private List<Probation> probationList;
    @OneToMany( mappedBy = "employeeCode")
    private List<Users> usersList;

    public Employee() {
    }

    public Employee(String code) {
        this.code = code;
    }

    public Employee(String code, byte[] portrait, String firstname, String lastname, String surname, String cellNumber, Date dob, String maritalStatus, String socialSecurityNumber) {
        this.code = code;
        this.portrait = portrait;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.cellNumber = cellNumber;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getFamilySize() {
        return familySize;
    }

    public void setFamilySize(Integer familySize) {
        this.familySize = familySize;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public DepartmentHeads getDepartmentHeads() {
        return departmentHeads;
    }

    public void setDepartmentHeads(DepartmentHeads departmentHeads) {
        this.departmentHeads = departmentHeads;
    }

    @XmlTransient
    public List<EmployeeWorkMonth> getEmployeeWorkMonthList() {
        return employeeWorkMonthList;
    }

    public void setEmployeeWorkMonthList(List<EmployeeWorkMonth> employeeWorkMonthList) {
        this.employeeWorkMonthList = employeeWorkMonthList;
    }

    @XmlTransient
    public List<Emergency> getEmergencyList() {
        return emergencyList;
    }

    public void setEmergencyList(List<Emergency> emergencyList) {
        this.emergencyList = emergencyList;
    }

    @XmlTransient
    public List<EmployeePosition> getEmployeePositionList() {
        return employeePositionList;
    }

    public void setEmployeePositionList(List<EmployeePosition> employeePositionList) {
        this.employeePositionList = employeePositionList;
    }

    @XmlTransient
    public List<Resolution> getResolutionList() {
        return resolutionList;
    }

    public void setResolutionList(List<Resolution> resolutionList) {
        this.resolutionList = resolutionList;
    }

    @XmlTransient
    public List<LeaveAbsence> getLeaveAbsenceList() {
        return leaveAbsenceList;
    }

    public void setLeaveAbsenceList(List<LeaveAbsence> leaveAbsenceList) {
        this.leaveAbsenceList = leaveAbsenceList;
    }

    @XmlTransient
    public List<Overtime> getOvertimeList() {
        return overtimeList;
    }

    public void setOvertimeList(List<Overtime> overtimeList) {
        this.overtimeList = overtimeList;
    }

    @XmlTransient
    public List<Probation> getProbationList() {
        return probationList;
    }

    public void setProbationList(List<Probation> probationList) {
        this.probationList = probationList;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Employee[ code=" + code + " ]";
    }
    
}
