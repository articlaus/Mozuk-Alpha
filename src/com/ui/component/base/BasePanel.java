package com.ui.component.base;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
//import org.springframework.util.Assert;
//import org.springframework.util.ClassUtils;
//import org.springframework.web.context.ContextLoader;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Panel;
import org.zkoss.zul.impl.InputElement;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: tseegii
 * Date: 6/17/13
 * Time: 8:19 PM
 */
public class BasePanel extends Panel implements BaseComponent ,Serializable {

    public BasePanel() {
//        processInjectionBasedOnCurrentContext(this);
    }

    /**
     * ZUL file-дээр хэрэв DataBinder ашиглаж байгаа бол
     * үүнийг ашиглаж мэдээллийг zul файл-руу илгээнэ.
     */
    private AnnotateDataBinder binder = null;
    private Collection<Component> components;

    @Override
    public AnnotateDataBinder getBinder() {
        return binder;
    }

    @Override
    public void afterCompose() {
        BaseConvention.processRecursive(this, this, this);
        binder = BaseConvention.getDataBinder(this, true);
    }

    public boolean isValid() {
        if (components == null)
            components = getFellows();

        for (Component component : components) {
            if (component instanceof InputElement) {
                if (!((InputElement) component).isValid()) {
                    ((InputElement) component).focus();
                    return false;
                }
            }
        }
        return true;

    }


    public boolean isValid(Component comp) {
        components = comp.getFellows();
        return isValid();
    }

//    private static final Log logger = LogFactory.getLog(SpringBeanAutowiringSupport.class);
    /**
     * Process <code>@Autowired</code> injection for the given target object,
     * based on the current web application context.
     * <p>Intended for use as a delegate.
     * @param target the target object to process
//     * @see org.springframework.web.context.ContextLoader#getCurrentWebApplicationContext()
     */
//    public static void processInjectionBasedOnCurrentContext(Object target) {
//        Assert.notNull(target, "Target object must not be null");
//        WebApplicationContext cc = ContextLoader.getCurrentWebApplicationContext();
//        if (cc != null) {
//            AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
//            bpp.setBeanFactory(cc.getAutowireCapableBeanFactory());
//            bpp.processInjection(target);
//        }
//        else {
//            if (logger.isDebugEnabled()) {
//                logger.debug("Current WebApplicationContext is not available for processing of " +
//                        ClassUtils.getShortName(target.getClass()) + ": " +
//                        "Make sure this class gets constructed in a Spring web application. Proceeding without injection.");
//            }
//        }
//    }
}