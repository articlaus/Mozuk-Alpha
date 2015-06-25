package com.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "PROBATION")
@NamedQueries({
        @NamedQuery(name = "Probation.findAll",query = "SELECT p FROM Probation AS p"),
})
public class Probation implements Serializable {
    private Integer id;
    private String employeeCode;
    private String probationReason;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    private String departmentId;
    private Date createdDate;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "employee_code")
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Basic
    @Column(name = "probation_reason")
    public String getProbationReason() {
        return probationReason;
    }

    public void setProbationReason(String probationReason) {
        this.probationReason = probationReason;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "isActive")
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "department_id")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Temporal(TemporalType.DATE)
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

        Probation probation = (Probation) o;

        if (id != probation.id) return false;
        if (isActive != probation.isActive) return false;
        if (employeeCode != null ? !employeeCode.equals(probation.employeeCode) : probation.employeeCode != null)
            return false;
        if (probationReason != null ? !probationReason.equals(probation.probationReason) : probation.probationReason != null)
            return false;
        if (startDate != null ? !startDate.equals(probation.startDate) : probation.startDate != null) return false;
        if (endDate != null ? !endDate.equals(probation.endDate) : probation.endDate != null) return false;
        if (departmentId != null ? !departmentId.equals(probation.departmentId) : probation.departmentId != null)
            return false;
        if (createdDate != null ? !createdDate.equals(probation.createdDate) : probation.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (employeeCode != null ? employeeCode.hashCode() : 0);
        result = 31 * result + (probationReason != null ? probationReason.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
