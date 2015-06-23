package com.model.util;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tseegii on 6/23/15.
 */
public class BaseEJB {


    @PersistenceContext(unitName = "avoUNIT")
    public EntityManager em;

    @Resource
    public SessionContext sessionContext;

    protected EntityManager getEm() {
        return em;
    }

    protected void rollbackTransaction() {
        sessionContext.setRollbackOnly();
    }
}
