package com.model.bean;

import com.model.entity.*;
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
public class ResolutionBean extends BaseEJB {

    @Inject
    private DocumentBean documentBean;

    public List<ResolutionType> findAllResolutionType() {
        return getEm().createNamedQuery("ResolutionType.findAll", ResolutionType.class)
                .getResultList();
    }

    public List<ResolutionType> findByIsActive(boolean isActive) {
        return getEm().createNamedQuery("ResolutionType.findByIsActive", ResolutionType.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public ResolutionType findById(BigDecimal resolutionTypeId) {
        return getEm().find(ResolutionType.class, resolutionTypeId);
    }

    public ResolutionType saveByResolutionType(ResolutionType resolutionType) {
        try {
            resolutionType.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(resolutionType);
            return resolutionType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResolutionType updateByResolutionType(ResolutionType resolutionType) {
        try {
            resolutionType.setId(SequenceUtil.nextBigDecimal());
            resolutionType = getEm().merge(resolutionType);
            return resolutionType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByResolutionType(BigDecimal resolutionTypeId) {
        try {
            ResolutionType resolutionType = getEm().getReference(ResolutionType.class, resolutionTypeId);
            getEm().remove(resolutionType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//-------------------------Resolution-------------------------------

    public List<Resolution> findAll() {
        return getEm().createNamedQuery("Resolution.findAll", Resolution.class)
                .getResultList();
    }

    public List<Resolution> findByEmployee(Employee employee) {
        return getEm().createNamedQuery("Resolution.findByEmployee", Resolution.class)
                .setParameter("employeeCode", employee)
                .getResultList();
    }

    public List<Resolution> findByDepartment(Department department) {
        return getEm().createNamedQuery("Resolution.findByDepartment", Resolution.class)
                .setParameter("departmentCode", department)
                .getResultList();
    }

    public List<Resolution> findByResolutionRange(int resolutionRange) {
        return getEm().createNamedQuery("Resolution.findByResolutionRange", Resolution.class)
                .setParameter("resolutionRange", resolutionRange)
                .getResultList();
    }

    public List<Resolution> findByResolutionType(ResolutionType resolutionType) {
        return getEm().createNamedQuery("Resolution.findByResolutionType", Resolution.class)
                .setParameter("resolutionType", resolutionType)
                .getResultList();
    }

    public Resolution findByCode(String resolutionCode) {
        try {
            Resolution resolution = getEm().find(Resolution.class, resolutionCode);
            List<Document> documents = documentBean.findByForeignKey(resolution.getCode());
            resolution.setDocuments(documents);
            return resolution;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Resolution save(Resolution resolution, int RESOLUTION_RANGE) {
        try {
            Date date = Calendar.getInstance().getTime();
            resolution.setResolutionRange(RESOLUTION_RANGE);
            resolution.setCreatedDate(date);
            getEm().persist(resolution);
            if (resolution.getDocuments().size() > 0)
                documentBean.saveAll(resolution.getCode(), resolution.getDocuments(), DOC_TYPE_RESOLUTION);
            return resolution;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Resolution save(Resolution resolution) {
        try {
            Date date = Calendar.getInstance().getTime();
            resolution.setCreatedDate(date);
            getEm().persist(resolution);
            if (resolution.getDocuments().size() > 0)
                documentBean.saveAll(resolution.getCode(), resolution.getDocuments(), DOC_TYPE_RESOLUTION);
            return resolution;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Resolution update(Resolution resolution) {
        try {
            resolution = getEm().merge(resolution);
            if (resolution.getDocuments().size() > 0)
                documentBean.saveAll(resolution.getCode(), resolution.getDocuments(), DOC_TYPE_RESOLUTION);
            return resolution;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(String resolutionCode) {
        try {
            Resolution resolution = getEm().getReference(Resolution.class, resolutionCode);
            getEm().remove(resolution);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
