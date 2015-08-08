package com.model.bean;

import com.model.entity.*;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;
import org.joda.time.DateTime;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tseegii on 6/23/15.
 */
@LocalBean
@Stateless
public class EmployeeBean extends BaseEJB {

    @Inject
    private OtherBean otherBean;

    public Employee findByCode(String code) {
        return getEm().find(Employee.class, code);
    }

    public Employee save(Employee employee) {
        try {
            employee.setIsActive(true);
            employee.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(employee);
            createWorkMonth(employee);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createWorkMonth(Employee employee) {
        EmployeeWorkMonth employeeWorkMonth = new EmployeeWorkMonth();
        WorkMonths workMonths = otherBean.findByYearAndMonth();
        saveByEmployeeWorkMonth(employee, workMonths);
    }

    public Employee update(Employee employee) {
        try {
            employee = getEm().merge(employee);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(String code) {
        try {
            getEm().remove(getEm().getReference(Employee.class, code));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> findAll() {
        return getEm().createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public List<Employee> findByIsActive(boolean isActive) {
        List<Employee> employees = getEm().createNamedQuery("Employee.findByIsActive", Employee.class)
                .setParameter("isActive", isActive)
                .getResultList();
        for (Employee employee : employees) {
            employee.setEmployeePosition(findByEmployeeCodeAndIsActive(employee, true));
        }
        return employees;
    }

//---------------------Employee Position--------------------------------

    public List<EmployeePosition> findAllEmployeePosition() {
        return getEm().createNamedQuery("EmployeePosition.findAll", EmployeePosition.class).getResultList();
    }

    public EmployeePosition findByEmployeePositionId(BigDecimal employeePositionId) {
        return getEm().find(EmployeePosition.class, employeePositionId);
    }

    public List<EmployeePosition> findPositionByEmployee(Employee employee) {
        try {
            return getEm().createNamedQuery("EmployeePosition.findByEmployee", EmployeePosition.class)
                    .setParameter("employeeCode", employee)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmployeePosition findByEmployeeCodeAndIsActive(Employee employee, boolean isActive) {
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        List<EmployeePosition> employeePositions=getEm().createNamedQuery("EmployeePosition.findByEmployeeAndIsActive", EmployeePosition.class)
                .setParameter("employeeCode", employee)
                .setParameter("isActive", isActive)
                .getResultList();
        if (employeePositions.size() == 0) {
            return null;
        } else {
            for (EmployeePosition employeePosition : employeePositions) {
                if (employeePosition.getIsActive()) {
                    return employeePosition;
                }
            }
            return null;
        }
    }

    public List<EmployeePosition> findEmployeePositionByIsActive(boolean isActive) {
        return getEm().createNamedQuery("EmployeePosition.findByIsActive", EmployeePosition.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public EmployeePosition saveByEmployeePosition(EmployeePosition employeePosition) {
        try {
            employeePosition.setId(SequenceUtil.nextBigDecimal());
            employeePosition.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(employeePosition);
            return employeePosition;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmployeePosition updateByEmployeePosition(EmployeePosition employeePosition) {
        try {
            employeePosition = getEm().merge(employeePosition);
            return employeePosition;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByEmployeePosition(BigDecimal employeePositionId) {
        try {
            getEm().remove(getEm().getReference(EmployeePosition.class, employeePositionId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//--------------------------Employee Work Month--------------------------------------

    public List<EmployeeWorkMonth> findAllEmployeeWorkMonth() {
        return getEm().createNamedQuery("EmployeeWorkMonth.findAll", EmployeeWorkMonth.class).getResultList();
    }

    public List<EmployeeWorkMonth> findEmployeeWorkMonthByIsActive(boolean isActive) {
        return getEm().createNamedQuery("EmployeeWorkMonth.findByIsActive", EmployeeWorkMonth.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public List<EmployeeWorkMonth> findWorkMonthByEmployee(Employee employee) {
        return getEm().createNamedQuery("EmployeeWorkMonth.findByEmployee", EmployeeWorkMonth.class)
                .setParameter("employeeCode", employee)
                .getResultList();
    }

    public List<EmployeeWorkMonth> findWorkMonthByWorkMonth(WorkMonths workMonths) {
        return getEm().createNamedQuery("EmployeeWorkMonth.findByWorkMonth", EmployeeWorkMonth.class)
                .setParameter("workMonthsid", workMonths)
                .getResultList();
    }

    public List<EmployeeWorkMonth> findWorkMonthByEmployeeAndWorkMonth(Employee employee, WorkMonths workMonths) {
        return getEm().createNamedQuery("EmployeeWorkMonth.findByEmployeeAndWorkMonth", EmployeeWorkMonth.class)
                .setParameter("employeeCode", employee)
                .setParameter("workMonthsid", workMonths)
                .getResultList();
    }

    public EmployeeWorkMonth findByEmployeeWorkMonthId(BigDecimal employeeWorkMonthId) {
        return getEm().find(EmployeeWorkMonth.class, employeeWorkMonthId);
    }

    public EmployeeWorkMonth saveByEmployeeWorkMonth(EmployeeWorkMonth employeeWorkMonth) {
        try {
            employeeWorkMonth.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(employeeWorkMonth);
            return employeeWorkMonth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmployeeWorkMonth saveByEmployeeWorkMonth(Employee employee, WorkMonths workMonths) {
        try {
            EmployeeWorkMonth ewm = new EmployeeWorkMonth();
            ewm.setId(SequenceUtil.nextBigDecimal());
            ewm.setWorkedHours(0);
            ewm.setFinalSalary(0);
            ewm.setEmployeeCode(employee);
            ewm.setWorkMonthsid(workMonths);
            getEm().persist(ewm);
            return ewm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmployeeWorkMonth updateByEmployeeWorkMonth(EmployeeWorkMonth employeeWorkMonth) {
        try {
            employeeWorkMonth = getEm().merge(employeeWorkMonth);
            return employeeWorkMonth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateByEmployeeWorkMonths(List<EmployeeWorkMonth> employeeWorkMonths) {
        try {
            for (EmployeeWorkMonth employeeWorkMonth : employeeWorkMonths) {
                getEm().merge(employeeWorkMonth);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean deleteByEmployeeWorkMonth(BigDecimal employeeWorkMonthId) {
        try {
            getEm().remove(getEm().getReference(EmployeeWorkMonth.class, employeeWorkMonthId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//----------------------Salary Additions --------------------

    public SalaryAdditions save(SalaryAdditions salaryAdditions) {
        try {
            salaryAdditions.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(salaryAdditions);
            return salaryAdditions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(List<SalaryAdditions> salaryAdditionses) {
        try {
            for (SalaryAdditions salaryAdditionse : salaryAdditionses) {
                save(salaryAdditionse);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SalaryAdditions update(SalaryAdditions salaryAdditions) {
        try {
            getEm().merge(salaryAdditions);
            return salaryAdditions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(List<SalaryAdditions> salaryAdditionses) {
        try {
            for (SalaryAdditions salaryAdditionse : salaryAdditionses) {
                update(salaryAdditionse);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SalaryAdditions> findBySalaryAddition(Employee employee) {
        return getEm().createNamedQuery("SalaryAdditions.findByEmployeeCode",SalaryAdditions.class)
                .setParameter("employeeCode", employee.getCode()).getResultList();
    }

    public SalaryAdditions findBySalaryAdditionId(BigDecimal salaryAdditionId) {
        return getEm().find(SalaryAdditions.class, salaryAdditionId);
    }

    public double findAdditionalSalaryByEmployeeCode(Employee employee) {
        Number number=getEm().createNamedQuery("SalaryAdditions.findDoubleByEmployeeCode", Number.class)
        .setParameter("employeeCode",employee.getCode()).getSingleResult();
        if (number != null) {
            return number.doubleValue();
        } else {
            return 0d;
        }
    }



}
