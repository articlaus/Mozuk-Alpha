package com.model.bean;

import com.model.entity.Employee;
import com.model.entity.Overtime;
import com.model.entity.OvertimeDates;
import com.model.entity.WorkMonths;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class OvertimeBean extends BaseEJB {

    public Overtime findByOvertimeId(BigDecimal overtimeId) {
        return getEm().find(Overtime.class, overtimeId);
    }

    public List<Overtime> findAll() {
        return getEm().createNamedQuery("Overtime.findAll", Overtime.class)
                .getResultList();
    }

    public List<Overtime> findByEmployee(Employee employee) {
        return getEm().createNamedQuery("Overtime.findByEmployee", Overtime.class)
                .setParameter("employeeCode", employee)
                .getResultList();
    }

    public List<Overtime> findByWorkMonthsId(WorkMonths workMonths) {
        return getEm().createNamedQuery("Overtime.findByWorkMonthsId", Overtime.class)
                .setParameter("workMonthsid", workMonths)
                .getResultList();
    }

    public List<Overtime> findByEmployeeAndWorkMonths(Employee employee, WorkMonths workMonths) {
        return getEm().createNamedQuery("Overtime.findByEmployeeAndWorkMonthsId", Overtime.class)
                .setParameter("employeeCode", employee)
                .setParameter("workMonthsid", workMonths)
                .getResultList();
    }

    public Overtime save(Overtime overtime) {
        try {
            overtime.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(overtime);
            return overtime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Overtime saveOvertimeAndOvertimeDates(Overtime overtime) {
        try {
            overtime = save(overtime);
            saveByOvertimeDate(overtime.getOvertimeDatesList(), overtime);
            return overtime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Overtime update(Overtime overtime) {
        try {
            overtime = getEm().merge(overtime);
            updateByOvertimeDate(overtime.getOvertimeDatesList(),overtime);
            return overtime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(BigDecimal overtimeId) {
        try {
            getEm().remove(getEm().getReference(Overtime.class, overtimeId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteForChildren(BigDecimal overtimeId) {
        try {
            Overtime overtime = getEm().getReference(Overtime.class, overtimeId);
            return deleteByOvertimeDate(findByOvertimeId(overtime));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//---------------------Overtime Dates----------------------

    public OvertimeDates findByOvertimeDateId(BigDecimal overtimeDateId) {
        return getEm().find(OvertimeDates.class, overtimeDateId);
    }

    public List<OvertimeDates> findAllOvertimeDate() {
        return getEm().createNamedQuery("OvertimeDates.findAll", OvertimeDates.class)
                .getResultList();
    }

    public List<OvertimeDates> findByOvertimeId(Overtime overtime) {
        return getEm().createNamedQuery("OvertimeDates.findByOvertime", OvertimeDates.class)
                .setParameter("overtimeid", overtime)
                .getResultList();
    }

    public List<OvertimeDates> findByIsHoliday(boolean isHoliday) {
        return getEm().createNamedQuery("OvertimeDates.findByIsHoliday", OvertimeDates.class)
                .setParameter("isHoliday", isHoliday)
                .getResultList();
    }

    public OvertimeDates saveByOvertimeDate(OvertimeDates overtimeDates) {
        try {
            overtimeDates.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(overtimeDates);
            return overtimeDates;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OvertimeDates> saveByOvertimeDate(List<OvertimeDates> overtimeDates) {
        try {
            for (OvertimeDates overtimeDate : overtimeDates) {
                overtimeDate.setId(SequenceUtil.nextBigDecimal());
                getEm().persist(overtimeDate);
            }
            return overtimeDates;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OvertimeDates> saveByOvertimeDate(List<OvertimeDates> overtimeDates, Overtime overtime) {
        try {
            for (OvertimeDates overtimeDate : overtimeDates) {
                overtimeDate.setId(SequenceUtil.nextBigDecimal());
                overtimeDate.setOvertimeid(overtime);
//                hours calculate start_time(string) and end_time(string)
                getEm().persist(overtimeDate);
            }
            return overtimeDates;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OvertimeDates updateByOvertimeDate(OvertimeDates overtimeDates) {
        try {
            overtimeDates = getEm().merge(overtimeDates);
            return overtimeDates;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OvertimeDates> updateByOvertimeDate(List<OvertimeDates> overtimeDates,Overtime overtime) {
        try {
            List<OvertimeDates> returnList = new ArrayList<>(overtimeDates.size());
            for (OvertimeDates overtimeDate : overtimeDates) {
                if (overtimeDate.getId() != null) {

                    overtimeDate = getEm().merge(overtimeDate);
                } else {
                    overtimeDate.setId(SequenceUtil.nextBigDecimal());
                    overtimeDate.setOvertimeid(overtime);
                    getEm().persist(overtime);
                }
                returnList.add(overtimeDate);
            }
            return returnList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByOvertimeDate(BigDecimal overtimeDateId) {
        try {
            getEm().remove(getEm().getReference(OvertimeDates.class, overtimeDateId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByOvertimeDate(List<OvertimeDates> overtimeDates) {
        try {
            for (OvertimeDates overtimeDate : overtimeDates) {
                getEm().remove(getEm().getReference(OvertimeDates.class, overtimeDate.getId()));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
