package com.model.bean;

import com.model.entity.*;
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

    public DocumentType findByDocumentTypeId(BigDecimal documentTypeId) {
        return getEm().find(DocumentType.class, documentTypeId);
    }

    public List<DocumentType> findAllDocumentType() {
        return getEm().createNamedQuery("DocumentType.findAll", DocumentType.class).getResultList();
    }

    public DocumentType saveByDocumentType(DocumentType documentType) {
        try {
            documentType.setId(SequenceUtil.nextBigDecimal());
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

    public Emergency findByEmergencyId(BigDecimal emergencyId) {
        return getEm().find(Emergency.class, emergencyId);
    }

    public List<Emergency> findAllEmergency() {
        return getEm().createNamedQuery("Emergency.findAll", Emergency.class).getResultList();
    }

    public Emergency saveByEmergency(Emergency emergency) {
        try {
            emergency.setId(SequenceUtil.nextBigDecimal());
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

//------------------------------------------------------------------------

    public LeaveType findByLeaveTypeId(BigDecimal emergencyId) {
        return getEm().find(LeaveType.class, emergencyId);
    }

    public List<LeaveType> findAllLeaveType() {
        return getEm().createNamedQuery("LeaveType.findAll", LeaveType.class).getResultList();
    }

    public LeaveType saveByLeaveType(LeaveType leaveType) {
        try {
            leaveType.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(leaveType);
            return leaveType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LeaveType updateByLeaveType(LeaveType leaveType) {
        try {
            leaveType = getEm().merge(leaveType);
            return leaveType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByLeaveType(BigDecimal leaveTypeId) {
        try {
            getEm().remove(getEm().getReference(LeaveType.class, leaveTypeId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//------------------------------------------------------------------------

    public WorkMonths findByWorkMonthsId(BigDecimal workMonthsId) {
        return getEm().find(WorkMonths.class, workMonthsId);
    }

    public List<WorkMonths> findAllWorkMonths() {
        return getEm().createNamedQuery("WorkMonths.findAll", WorkMonths.class).getResultList();
    }

    public WorkMonths saveByWorkMonths(WorkMonths workMonths) {
        try {
            workMonths.setId(SequenceUtil.nextBigDecimal());
            workMonths.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(workMonths);
            return workMonths;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WorkMonths updateByWorkMonths(WorkMonths workMonths) {
        try {
            workMonths = getEm().merge(workMonths);
            return workMonths;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByWorkMonths(BigDecimal workMonthsId) {
        try {
            getEm().remove(getEm().getReference(WorkMonths.class, workMonthsId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<WorkMonths> findByIsLocked(boolean isLocked) {
        return getEm().createNamedQuery("WorkMonths.findByIsLocked", WorkMonths.class)
                .setParameter("isLocked", isLocked)
                .getResultList();
    }

    public WorkMonths findByYearAndMonth(int year, int month) {
        try {
            return getEm().createNamedQuery("WorkMonths.findByYearAndMonth", WorkMonths.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WorkMonths findByYearAndMonthAndIsLocked(int year, int month, boolean isLocked) {
        try {
            return getEm().createNamedQuery("WorkMonths.findByYearAndMonthAndIsLocked", WorkMonths.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .setParameter("isLocked", isLocked)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
