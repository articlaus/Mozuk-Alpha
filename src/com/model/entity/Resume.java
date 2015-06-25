package com.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "RESUME")
@NamedQueries({
        @NamedQuery(name = "Resume.findAll",query = "SELECT r FROM Resume AS r"),
})
public class Resume {
    private Integer id;
    private Date createdDate;
    private String positionCode;
    private String email;
    private String note;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "position_code")
    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (id != resume.id) return false;
        if (createdDate != null ? !createdDate.equals(resume.createdDate) : resume.createdDate != null) return false;
        if (positionCode != null ? !positionCode.equals(resume.positionCode) : resume.positionCode != null)
            return false;
        if (email != null ? !email.equals(resume.email) : resume.email != null) return false;
        if (note != null ? !note.equals(resume.note) : resume.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (positionCode != null ? positionCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
