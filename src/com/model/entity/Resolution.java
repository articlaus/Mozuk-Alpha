package com.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Arrays;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "RESOLUTION")
@NamedQueries({
        @NamedQuery(name = "Resolution.findAll",query = "SELECT r FROM Resolution AS r"),
})
public class Resolution {
    private String code;
    private String employeeId;
    private byte[] resolutionFile;
    private String departmentId;
    private Date createdDate;
    private boolean isDepartment;
    private String resolutionType;

    @Id
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "resolution_file")
    public byte[] getResolutionFile() {
        return resolutionFile;
    }

    public void setResolutionFile(byte[] resolutionFile) {
        this.resolutionFile = resolutionFile;
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

    @Basic
    @Column(name = "isDepartment")
    public boolean getIsDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(boolean isDepartment) {
        this.isDepartment = isDepartment;
    }

    @Basic
    @Column(name = "resolution_type")
    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resolution that = (Resolution) o;

        if (isDepartment != that.isDepartment) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (!Arrays.equals(resolutionFile, that.resolutionFile)) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (resolutionType != null ? !resolutionType.equals(that.resolutionType) : that.resolutionType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (resolutionFile != null ? Arrays.hashCode(resolutionFile) : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (isDepartment ? 1 : 0);
        result = 31 * result + (resolutionType != null ? resolutionType.hashCode() : 0);
        return result;
    }
}
