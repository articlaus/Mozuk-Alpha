package com.model.bean;



import com.model.util.BaseEJB;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tseegii on 7/1/14.
 */
@LocalBean
@Stateless
public class BandboxBean<T> extends BaseEJB {

    public List<T> findAll(String namedQuery, Class<T> clazz, Map<String, Object> parameterMap) {
        Query query = getEm().createNamedQuery(namedQuery, clazz);
        if (parameterMap != null) {
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query.getResultList();
    }


    public List<T> findFilter(String namedQuery, Class<T> clazz, Map<String, Object> parameterMap) {
        Query query = getEm().createNamedQuery(namedQuery, clazz);
        if (parameterMap != null) {
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query.getResultList();
    }
}
