package com.model.bean;

import com.model.entity.Employee;
import com.model.entity.EmployeePosition;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
        List<Employee> employees=getEm().createNamedQuery("Employee.findAll",Employee.class).getResultList();
        for (Employee employee : employees) {
            employee.setEmployeePosition(
                    getEm().createNamedQuery("EmployeePosition.findByEmployeeAndIsActive", EmployeePosition.class)
                            .setParameter("employee", employee)
                            .setParameter("isActive", true).getSingleResult()
            );
        }
        return employees;
    }


}
