package com.model.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tseegii on 7/28/15.
 */
@Entity
@Table(name = "RESOLUTION_TYPE", schema = "", catalog = "avocado")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name ="ResolutionType.findAll",query = "SELECT r FROM ResolutionType AS r "),
        @NamedQuery(name ="ResolutionType.findByIsActive",query = "SELECT r FROM ResolutionType AS r WHERE r.isActive=:isActive"),
})
public class ResolutionType implements Serializable{

    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic
    @Column(name = "resolution_type")
    private String resolutionType;
    @Basic
    @Column(name = "resolution_type_desc")
    private String resolutionTypeDesc;
    @Basic
    @Column(name = "isActive")

    private Boolean isActive;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }


    public String getResolutionTypeDesc() {
        return resolutionTypeDesc;
    }

    public void setResolutionTypeDesc(String resolutionTypeDesc) {
        this.resolutionTypeDesc = resolutionTypeDesc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResolutionType that = (ResolutionType) o;

        if (id != that.id) return false;
        if (resolutionType != null ? !resolutionType.equals(that.resolutionType) : that.resolutionType != null)
            return false;
        if (resolutionTypeDesc != null ? !resolutionTypeDesc.equals(that.resolutionTypeDesc) : that.resolutionTypeDesc != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (resolutionType != null ? resolutionType.hashCode() : 0);
        result = 31 * result + (resolutionTypeDesc != null ? resolutionTypeDesc.hashCode() : 0);
        return result;
    }
}
