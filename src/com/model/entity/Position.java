package com.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "POSITION")
@NamedQueries({
        @NamedQuery(name = "Position.findAll",query = "SELECT p FROM Position AS p"),
})
public class Position {
    private String code;
    private String positionTitle;
    private String positionDescription;
    private Date createdDate;

    @Id
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "position_title")
    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    @Basic
    @Column(name = "position_description")
    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    @Basic
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

        Position position = (Position) o;

        if (code != null ? !code.equals(position.code) : position.code != null) return false;
        if (positionTitle != null ? !positionTitle.equals(position.positionTitle) : position.positionTitle != null)
            return false;
        if (positionDescription != null ? !positionDescription.equals(position.positionDescription) : position.positionDescription != null)
            return false;
        if (createdDate != null ? !createdDate.equals(position.createdDate) : position.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (positionTitle != null ? positionTitle.hashCode() : 0);
        result = 31 * result + (positionDescription != null ? positionDescription.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
