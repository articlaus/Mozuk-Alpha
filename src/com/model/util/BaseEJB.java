package com.model.util;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

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

    public static BigDecimal DOC_TYPE_OVERTIME = BigDecimal.valueOf(2);
    public static BigDecimal DOC_TYPE_LEAVE = BigDecimal.valueOf(3);
    public static BigDecimal DOC_TYPE_RESOLUTION = BigDecimal.valueOf(4);
    public static BigDecimal DOC_TYPE_PROBATION = BigDecimal.valueOf(5);

//    public static final String filePath = "C:\\Users\\huslee\\IdeaProjects\\Mozuk-Alpha\\out\\production\\Mozuk-Alpha\\META-INF\\";
    public static final String filePath = "/home/tseegii/filePersist/";

    @PersistenceContext(unitName = "mozukUnit")
    private EntityManager em;

    @Resource
    private SessionContext sessionContext;

    protected EntityManager getEm() {
        return em;
    }

    protected void rollbackTransaction() {
        sessionContext.setRollbackOnly();
    }
}
