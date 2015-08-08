package com.model.bean;

import com.model.entity.Employee;
import com.model.entity.LeaveAbsence;
import com.model.entity.LeaveType;
import com.model.entity.WorkMonths;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class LeaveAbsenceBean extends BaseEJB {

    @Inject
    private DocumentBean documentBean;

    public LeaveAbsence findByLeaveAbsenceId(BigDecimal leaveAbsenceId) {
        return getEm().find(LeaveAbsence.class, leaveAbsenceId);
    }

    public List<LeaveAbsence> findAll() {
        return getEm().createNamedQuery("LeaveAbsence.findAll", LeaveAbsence.class)
                .getResultList();
    }

    public List<LeaveAbsence> findByEmployee(Employee employee) {
        return getEm().createNamedQuery("LeaveAbsence.findByEmployee", LeaveAbsence.class)
                .setParameter("employeeId", employee)
                .getResultList();
    }

    public List<LeaveAbsence> findByLeaveType(LeaveType leaveType) {
        return getEm().createNamedQuery("LeaveAbsence.findByLeaveType", LeaveAbsence.class)
                .setParameter("leaveTypeId", leaveType)
                .getResultList();
    }

    public List<LeaveAbsence> findByWorkMonths(WorkMonths workMonths) {
        return getEm().createNamedQuery("LeaveAbsence.findByWorkMonths", LeaveAbsence.class)
                .setParameter("workMonthsid", workMonths)
                .getResultList();
    }

    public List<LeaveAbsence> findByWorkMonths(WorkMonths workMonths,boolean isActive) {
        return getEm().createNamedQuery("LeaveAbsence.findByWorkMonthsAndIsActive", LeaveAbsence.class)
                .setParameter("workMonthsid", workMonths)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public List<LeaveAbsence> findByIsPaid(boolean isPaid) {
        return getEm().createNamedQuery("LeaveAbsence.findByIsPaid", LeaveAbsence.class)
                .setParameter("isPaid", isPaid)
                .getResultList();
    }

    public List<LeaveAbsence> findByStartDateAndEndDate(Date startDate, Date endDate) {
        return getEm().createNamedQuery("LeaveAbsence.findByStartDateAndEndDate", LeaveAbsence.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }

    public List<LeaveAbsence> findByEmployeeAndLeaveType(Employee employee, LeaveType leaveType) {
        return getEm().createNamedQuery("LeaveAbsence.findByEmployeeAndLeaveType", LeaveAbsence.class)
                .setParameter("employeeId", employee)
                .setParameter("leaveTypeId", leaveType)
                .getResultList();
    }

    public List<LeaveAbsence> findByEmployeeAndWorkMonths(Employee employee, WorkMonths workMonths) {
        return getEm().createNamedQuery("LeaveAbsence.findByEmployeeAndWorkMonths", LeaveAbsence.class)
                .setParameter("employeeId", employee)
                .setParameter("workMonthsid", workMonths)
                .getResultList();
    }

    public LeaveAbsence save(LeaveAbsence leaveAbsence) {
        try {
            leaveAbsence.setId(SequenceUtil.nextBigDecimal());
            leaveAbsence.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(leaveAbsence);
            if (leaveAbsence.getDocuments().size() > 0)
                documentBean.saveAll(leaveAbsence.getId().toString(), leaveAbsence.getDocuments(), DOC_TYPE_LEAVE);
            return leaveAbsence;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LeaveAbsence update(LeaveAbsence leaveAbsence) {
        try {
            leaveAbsence = getEm().merge(leaveAbsence);
            if (leaveAbsence.getDocuments().size() > 0)
                documentBean.saveAll(leaveAbsence.getId().toString(), leaveAbsence.getDocuments(), DOC_TYPE_LEAVE);
            return leaveAbsence;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(BigDecimal leaveAbsenceId) {
        try {
            getEm().remove(getEm().getReference(LeaveAbsence.class, leaveAbsenceId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int findHoursByEmployeeAndWorkMonthAndIsPaid(Employee employee, WorkMonths workMonths, boolean isPaid) {
        List<LeaveAbsence> leaveAbsences=getEm().createNamedQuery("LeaveAbsence.findByEmployeeAndWorkMonthsAndIsPaid", LeaveAbsence.class)
                .setParameter("employeeId", employee)
                .setParameter("workMonthsid", workMonths)
                .setParameter("isPaid", isPaid)
                .getResultList();
        int hours = 0;
        if (leaveAbsences.size() == 0) {
            return hours;
        } else {
            for (LeaveAbsence leaveAbsence : leaveAbsences) {
                if (leaveAbsence.getLeaveTypeId().getId().equals(BigDecimal.valueOf(1))) {
                    hours += leaveAbsence.getNumberOfHours();
                }

                if (leaveAbsence.getLeaveTypeId().getId().equals(BigDecimal.valueOf(2))) {
                    hours += leaveAbsence.getNumberOfHours();
                }
            }
            return hours;
        }
    }

}
