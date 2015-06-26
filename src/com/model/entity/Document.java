/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "DOCUMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findById", query = "SELECT d FROM Document d WHERE d.id = :id"),
    @NamedQuery(name = "Document.findByForeignKey", query = "SELECT d FROM Document d WHERE d.foreignKey = :foreignKey"),
    @NamedQuery(name = "Document.findByDescription", query = "SELECT d FROM Document d WHERE d.description = :description"),
    @NamedQuery(name = "Document.findByFileName", query = "SELECT d FROM Document d WHERE d.fileName = :fileName"),
    @NamedQuery(name = "Document.findByFileType", query = "SELECT d FROM Document d WHERE d.fileType = :fileType"),
    @NamedQuery(name = "Document.findByCreatedDate", query = "SELECT d FROM Document d WHERE d.createdDate = :createdDate"),
    @NamedQuery(name = "Document.findByDocumentType", query = "SELECT d FROM Document d WHERE d.documentTypeId = :documentTypeId"),
})
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "foreign_key")
    private BigDecimal foreignKey;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DocumentType documentTypeId;

    public Document() {
    }

    public Document(BigDecimal id) {
        this.id = id;
    }

    public Document(BigDecimal id, BigDecimal foreignKey, String fileName) {
        this.id = id;
        this.foreignKey = foreignKey;
        this.fileName = fileName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(BigDecimal foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DocumentType getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(DocumentType documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Document[ id=" + id + " ]";
    }
    
}
