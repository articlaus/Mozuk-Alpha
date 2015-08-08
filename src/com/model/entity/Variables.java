package com.model.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created by tseegii on 7/25/15.
 */
@Entity
@Table(name = "VARIABLES")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VARIABLES.findAll", query = "SELECT v FROM Variables AS v "),
        @NamedQuery(name = "VARIABLES.findByIsActive", query = "SELECT v FROM Variables AS v WHERE v.isActive=:isActive"),
        @NamedQuery(name = "VARIABLES.findByVariableCode", query = "SELECT v FROM Variables AS v WHERE v.variableCode=:variableCode"),
        @NamedQuery(name = "VARIABLES.findByVariableCodeAndIsActive", query = "SELECT v FROM Variables AS v WHERE v.isActive=:isActive AND v.variableCode=:variableCode"),
        @NamedQuery(name = "VARIABLES.findByVariableName", query = "SELECT v FROM Variables AS v WHERE v.isActive=:isActive AND v.variableName=:variableName"),
})
public class Variables {
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic
    @Column(name = "variable_name")
    private String variableName;

    @Basic
    @Column(name = "variable_value")
    private String variableValue;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;
    @Basic
    @Column(name = "desc")
    private String desc;

    @Basic
    @Column(name = "isActive")
    private Boolean isActive;

    @Basic
    @Column(name = "variable_code")
    private String variableCode;


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getVariableCode() {
        return variableCode;
    }

    public void setVariableCode(String variableCode) {
        this.variableCode = variableCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variables variables = (Variables) o;

        if (!Objects.equals(id, variables.id)) return false;
        if (isActive != variables.isActive) return false;
        if (variableName != null ? !variableName.equals(variables.variableName) : variables.variableName != null)
            return false;
        if (variableValue != null ? !variableValue.equals(variables.variableValue) : variables.variableValue != null)
            return false;
        if (createdDate != null ? !createdDate.equals(variables.createdDate) : variables.createdDate != null)
            return false;
        if (desc != null ? !desc.equals(variables.desc) : variables.desc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
