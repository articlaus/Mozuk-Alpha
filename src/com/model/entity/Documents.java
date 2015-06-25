package com.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "DOCUMENT")
@NamedQueries({
        @NamedQuery(name = "Documents.findAll",query = "SELECT d FROM Documents AS d"),
})
public class Documents implements Serializable {
    private Integer id;
    private Integer foreignKey;
    private String description;
    private String fileName;
    private String fileType;
    private Integer documentTypeId;
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
    @Column(name = "foreign_key")
    public Integer getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(Integer foreignKey) {
        this.foreignKey = foreignKey;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "document_type_id")
    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
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

        Documents documents = (Documents) o;

        if (id != documents.id) return false;
        if (foreignKey != documents.foreignKey) return false;
        if (documentTypeId != documents.documentTypeId) return false;
        if (description != null ? !description.equals(documents.description) : documents.description != null)
            return false;
        if (fileName != null ? !fileName.equals(documents.fileName) : documents.fileName != null) return false;
        if (fileType != null ? !fileType.equals(documents.fileType) : documents.fileType != null) return false;
        if (createdDate != null ? !createdDate.equals(documents.createdDate) : documents.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + foreignKey;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + documentTypeId;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
