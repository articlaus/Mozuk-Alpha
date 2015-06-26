package com.model.bean;

import com.model.entity.Document;
import com.model.entity.DocumentType;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class DocumentBean extends BaseEJB {

    public Document findByDocumentId(BigDecimal documentId) {
        return getEm().find(Document.class, documentId);
    }

    public List<Document> findAllDocument() {
        return getEm().createNamedQuery("Document.findAll", Document.class).getResultList();
    }

    public Document save(Document document) {
        try {
            document.setId(SequenceUtil.nextBigDecimal());
            document.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Document update(Document document) {
        try {
            document = getEm().merge(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(String document) {
        try {
            getEm().remove(getEm().getReference(Document.class, document));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Document> findByDocumentType(DocumentType documentType) {
        return getEm().createNamedQuery("Document.findByDocumentType", Document.class)
                .setParameter("documentTypeId", documentType)
                .getResultList();
    }

    public List<Document> findByForeignKey(BigDecimal foreignKey) {
        return getEm().createNamedQuery("Document.findByForeignKey", Document.class)
                .setParameter("foreignKey", foreignKey)
                .getResultList();
    }

    public List<Document> findByFileType(String fileType) {
        return getEm().createNamedQuery("Document.findByFileType", Document.class)
                .setParameter("fileType", fileType)
                .getResultList();
    }

    public Document findByFileName(String fileName) {
        try {
            return getEm().createNamedQuery("Document.findByFileName", Document.class)
                    .setParameter("fileName", fileName)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
