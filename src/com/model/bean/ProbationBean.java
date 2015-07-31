package com.model.bean;

import com.model.entity.Department;
import com.model.entity.Employee;
import com.model.entity.Probation;
import com.model.util.BaseEJB;
import com.model.util.SequenceUtil;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class ProbationBean extends BaseEJB {

    @Inject
    private DocumentBean documentBean;

    public List<Probation> findAll() {
        return getEm().createNamedQuery("Probation.findAll", Probation.class)
                .getResultList();
    }

    public List<Probation> findByIsActive(boolean isActive) {
        return getEm().createNamedQuery("Probation.findByIsActive", Probation.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public List<Probation> findByEmployeeCode(Employee employee) {
        return getEm().createNamedQuery("Probation.findByEmployee", Probation.class)
                .setParameter("employeeCode", employee)
                .getResultList();
    }

    public List<Probation> findByDepartmentCode(Department department) {
        return getEm().createNamedQuery("Probation.findByDepartment", Probation.class)
                .setParameter("departmentCode", department)
                .getResultList();
    }

    public Probation findById(BigDecimal probationId) {
        return getEm().find(Probation.class, probationId);
    }


    public Probation save(Probation probation) {
        try {
            probation.setId(SequenceUtil.nextBigDecimal());
            probation.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(probation);
            if (probation.getDocuments().size() > 0)
                documentBean.saveAll(probation.getId().toString(), probation.getDocuments(), DOC_TYPE_PROBATION);
            return probation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Probation update(Probation probation) {
        try {
            probation=getEm().merge(probation);
            if (probation.getDocuments().size() > 0)
                documentBean.saveAll(probation.getId().toString(), probation.getDocuments(), DOC_TYPE_PROBATION);
            return probation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(BigDecimal probationId) {
        try {
            Probation probation=getEm().getReference(Probation.class, probationId);
            getEm().remove(probation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
