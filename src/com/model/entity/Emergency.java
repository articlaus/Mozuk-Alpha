package com.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "EMERGENCY")
@NamedQueries({
        @NamedQuery(name = "Emergency.findAll",query = "SELECT e FROM Emergency AS e"),
})
public class Emergency implements Serializable {
    private Integer id;
    private String cellNumber;
    private String name;
    private String relationship;
    private String employeeCode;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cell_number")
    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "relationship")
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Basic
    @Column(name = "employee_code")
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emergency emergency = (Emergency) o;

        if (id != emergency.id) return false;
        if (cellNumber != null ? !cellNumber.equals(emergency.cellNumber) : emergency.cellNumber != null) return false;
        if (name != null ? !name.equals(emergency.name) : emergency.name != null) return false;
        if (relationship != null ? !relationship.equals(emergency.relationship) : emergency.relationship != null)
            return false;
        if (employeeCode != null ? !employeeCode.equals(emergency.employeeCode) : emergency.employeeCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cellNumber != null ? cellNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (relationship != null ? relationship.hashCode() : 0);
        result = 31 * result + (employeeCode != null ? employeeCode.hashCode() : 0);
        return result;
    }
}
