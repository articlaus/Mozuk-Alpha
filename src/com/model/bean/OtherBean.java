package com.model.bean;

import com.model.entity.Department;
import com.model.entity.DocumentType;
import com.model.entity.Emergency;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class OtherBean extends BaseEJB {
    /**
     * Ene bean jijeg heregtseet zvilvvdiig bichne
     */

    public Department findByDepartmentCode(String departmentCode) {
        return getEm().find(Department.class, departmentCode);
    }

    public List<Department> findAllDepartment() {
        return getEm().createNamedQuery("Department.findAll", Department.class).getResultList();
    }

    public Department saveByDepartment(Department department) {
        try {
            department.setCode(SequenceUtil.nextBigDecimal().toString());
            department.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(department);
            return department;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Department updateByDepartment(Department department) {
        try {
            department = getEm().merge(department);
            return department;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByDepartment(String departmentCode) {
        try {
            getEm().remove(getEm().getReference(Department.class, departmentCode));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//------------------------------------------------------------------------

    public DocumentType findByDocumentTypeId(Integer documentTypeId) {
        return getEm().find(DocumentType.class, documentTypeId);
    }

    public List<DocumentType> findAllDocumentType() {
        return getEm().createNamedQuery("DocumentType.findAll", DocumentType.class).getResultList();
    }

    public DocumentType saveByDocumentType(DocumentType documentType) {
        try {
            documentType.setId(SequenceUtil.nextBigDecimal().intValue());
            getEm().persist(documentType);
            return documentType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DocumentType updateByDocumentType(DocumentType documentType) {
        try {
            documentType = getEm().merge(documentType);
            return documentType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByDocumentType(String documentTypeId) {
        try {
            getEm().remove(getEm().getReference(DocumentType.class, documentTypeId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//------------------------------------------------------------------------

    public Emergency findByEmergencyId(Integer emergencyId) {
        return getEm().find(Emergency.class, emergencyId);
    }

    public List<Emergency> findAllEmergency() {
        return getEm().createNamedQuery("Emergency.findAll", Emergency.class).getResultList();
    }

    public Emergency saveByEmergency(Emergency emergency) {
        try {
            emergency.setId(SequenceUtil.nextBigDecimal().intValue());
            getEm().persist(emergency);
            return emergency;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Emergency updateByEmergency(Emergency emergency) {
        try {
            emergency = getEm().merge(emergency);
            return emergency;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByEmergency(String emergencyId) {
        try {
            getEm().remove(getEm().getReference(Emergency.class, emergencyId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
