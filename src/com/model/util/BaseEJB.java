package com.model.util;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tseegii on 6/23/15.
 */
public class BaseEJB {
    /**
     * Албан тушаалийн хамрах хүрээ
     */
    public static int RES_PERSON = 1;
    public static int RES_DEPARTMENT = 2;
    public static int RES_COMPANY = 3;

    public static final String filePath = "C:\\Users\\huslee\\IdeaProjects\\Mozuk-Alpha\\out\\production\\Mozuk-Alpha\\META-INF\\";

    @PersistenceContext(unitName = "mozukUnit")
    private EntityManager em;Бү

    @Resource
    private SessionContext sessionContext;

    protected EntityManager getEm() {
        return em;
    }

    protected void rollbackTransaction() {
        sessionContext.setRollbackOnly();
    }
}
