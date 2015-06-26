package com.model.bean;

import com.model.entity.Employee;
import com.model.entity.EmployeePosition;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tseegii on 6/23/15.
 */
@LocalBean
@Stateless
public class EmployeeBean extends BaseEJB {

    public Employee findByCode(String code) {
        return getEm().find(Employee.class, code);
    }

    public Employee save(Employee employee) {
        try {
            employee.setCode(SequenceUtil.nextBigDecimal().toString());
            employee.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(employee);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

//---------------------Employee Position--------------------------------

    public List<EmployeePosition> findAllEmployeePosition() {
        return getEm().createNamedQuery("EmployeePosition.findAll", EmployeePosition.class).getResultList();
    }

    public EmployeePosition findByEmployeePositionId(BigDecimal employeePositionId) {
        return getEm().find(EmployeePosition.class, employeePositionId);
    }

    public List<EmployeePosition> findByEmployee(Employee employee) {
        try {
            return getEm().createNamedQuery("EmployeePosition.findByEmployee", EmployeePosition.class)
                    .setParameter("employeeCode", employee)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmployeePosition findByEmployeeCodeAndIsActive(Employee employee,boolean isActive) {
        try {
            return getEm().createNamedQuery("EmployeePosition.findByEmployeeAndIsActive", EmployeePosition.class)
                    .setParameter("employeeCode", employee)
                    .setParameter("isActive", isActive)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<EmployeePosition> findByIsActive(boolean isActive) {
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


}
