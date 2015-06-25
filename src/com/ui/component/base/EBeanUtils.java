package com.ui.component.base;

import javax.naming.InitialContext;
import java.util.logging.Logger;

/**
 * Created by tseegii on 2/21/14.
 */
public class EBeanUtils {
    private static final Logger LOG = Logger.getLogger(String.valueOf(EBeanUtils.class));

    private static String JNDI_PREFIX = "java:module";

    /**
     * <p/>
     * Get a bean. (Always a new bean.)
     *
     * @param clazz Bean class.
     * @return Bean, every time a new JNDI lookup, (i.e. a new bean is returned). In case of troubles return {@code null}.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        T bean = null;
        try {
            String clazzName = clazz.getSimpleName();
//            System.out.println("JNDI_PREFIX + clazzName + \"!\" + clazz.getName() = " + JNDI_PREFIX + Executions.getCurrent().getContextPath() + "/" + clazzName + "!" + clazz.getName());
//            System.out.println("JNDI_PREFIX + clazzName + \"!\" + clazz.getName() = " + JNDI_PREFIX+"/" + clazzName + "!" + clazz.getName());
            bean = InitialContext.doLookup(JNDI_PREFIX +"/"+ clazzName + "!" + clazz.getName()); // beanLocations.get(clazz)
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
