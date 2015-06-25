package com.ui.component.base;

//import nous.data.exceptions.DataBinderNotCreatedException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.ConventionWires;
import org.zkoss.zkplus.databind.AnnotateDataBinder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tseegii
 * Date: 6/17/13
 * Time: 8:10 PM
 */
public class BaseConvention {

    private BaseConvention() {
    }

    /**
     * ZUL файлын field болон Controller class-ын property
     * хоёрыг хооронд нь холбоно.
     *
     * @param main
     * @param child
     * @param core
     * @return
     */
    static public void processRecursive(Component main, Component child, Component core) {
        ConventionWires.wireVariables(main, child);
        ConventionWires.addForwards(main, core);
        List<Component> winList = (List<Component>) child.getChildren();
        for (Component window : winList) {
            if (window instanceof BaseComponent) {
                processRecursive(main, window, core);
            }
        }
    }


    /*
   * Тухайн component-од зориулан AnnotateDataBinder үүсгэж өгнө. Хэрэв isNew утга нь
   * үнэн байвал шинээр үүсгэж өгөх бөгөөд худал бол эцэг component-ийн AnnotateDataBinder
   * буцаана.
   * */
    public static AnnotateDataBinder getDataBinder(Component component, boolean isNew) {
        if (isNew) {
            return new AnnotateDataBinder(component);
        } else {
            Component parent = component.getParent();
            if (parent instanceof BaseComponent) {
                BaseComponent baseParent = (BaseComponent) parent;
                return baseParent.getBinder();
            }
        }

//        throw new DataBinderNotCreatedException(component);
        try {
            throw new Exception("sdfsadfa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw null;
    }

    public static AnnotateDataBinder getDataBinder(Component component) {
        return getDataBinder(component, false);
    }
}
