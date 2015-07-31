package com.model.bean;

import com.model.entity.Document;
import com.model.entity.DocumentType;
import com.model.entity.Employee;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
            document.setFilePath(createFile(document));
            getEm().persist(document);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveAll(String foreignKey, List<Document> documents, BigDecimal docTypeId) {

        try {
            DocumentType docType = getEm().getReference(DocumentType.class, docTypeId);
            for (Document document : documents) {
                document.setId(SequenceUtil.nextBigDecimal());
                document.setForeignKey(foreignKey);
                document.setCreatedDate(Calendar.getInstance().getTime());
                document.setDocumentTypeId(docType);
                document.setFilePath(createFile(document));
                getEm().persist(document);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String createFile(Document document) {
        try {
            String filePath = BaseEJB.filePath + document.getFileName() + "." + document.getFileExtension();
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(document.getBytes());
            fos.flush();
            return filePath;
        } catch (java.io.IOException e) {
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
            Document doc=getEm().getReference(Document.class, document);
            getEm().remove(doc);
            File file = new File(doc.getFilePath());
            file.delete();
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

    public List<Document> findByForeignKey(String foreignKey) {
        return getEm().createNamedQuery("Document.findByForeignKey", Document.class)
                .setParameter("foreignKey", foreignKey)
                .getResultList();
    }

    public List<Document> findByFileType(String fileType) {
        return getEm().createNamedQuery("Document.findByFileType", Document.class)
                .setParameter("fileExtension", fileType)
                .getResultList();
    }

    public List<Document> findByEmployee(Employee employee) {
        return getEm().createNamedQuery("Document.findByEmployee", Document.class)
                .setParameter("employeeCode", employee)
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

    public boolean removeFile(BigDecimal documentId) {
        try {
            Document document=getEm().find(Document.class, documentId);
            File file = new File(document.getFilePath());
            getEm().remove(document);
            return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }



}
