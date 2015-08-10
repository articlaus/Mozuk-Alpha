package com.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tseegii on 8/4/15.
 */
@Entity
@Table(name = "SALARY_ADDITIONS")
@NamedQueries({
        @NamedQuery(name = "SalaryAdditions.findAll", query = "SELECT s FROM SalaryAdditions AS s "),
        @NamedQuery(name = "SalaryAdditions.findByEmployeeCode", query = "SELECT s FROM SalaryAdditions AS s WHERE s.employeeCode.code=:employeeCode"),
        @NamedQuery(name = "SalaryAdditions.findDoubleByEmployeeCode", query = "SELECT SUM(s.additionAmount) FROM SalaryAdditions AS s WHERE s.employeeCode.code=:employeeCode"),
})
public class SalaryAdditions implements Serializable {


    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic
    @Column(name = "addition_name")
    private String additionName;
    @Basic
    @Column(name = "addition_amount")
    private Double additionAmount;
    @ManyToOne
    @JoinColumn(name = "employee_code",referencedColumnName = "code")
    private Employee employeeCode;


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    public String getAdditionName() {
        return additionName;
    }

    public void setAdditionName(String additionName) {
        this.additionName = additionName;
    }


    public Double getAdditionAmount() {
        return additionAmount;
    }

    public void setAdditionAmount(Double additionAmount) {
        this.additionAmount = additionAmount;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryAdditions that = (SalaryAdditions) o;

        if (id != that.id) return false;
        if (additionName != null ? !additionName.equals(that.additionName) : that.additionName != null) return false;
        if (additionAmount != null ? !additionAmount.equals(that.additionAmount) : that.additionAmount != null)
            return false;
        if (employeeCode != null ? !employeeCode.equals(that.employeeCode) : that.employeeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + additionName.hashCode();
        result = 31 * result + additionAmount.hashCode();
        result = 31 * result + employeeCode.hashCode();
        return result;
    }
}
