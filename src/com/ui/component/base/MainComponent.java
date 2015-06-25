package com.ui.component.base;

import com.ui.component.CustomBandbox;
import org.zkoss.bind.AnnotateBinder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.*;
import org.zkoss.zul.impl.InputElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tseegii on 2/24/14.
 */
public abstract class MainComponent {

    private Include mainInclude;
    private AnnotateBinder binder;

    private Window currentWindow;
    private Panel currentPanel;
    private Html html;
    private HashMap<String, String> backURL = new HashMap<>();

    public void init() {
        mainInclude = (Include) Executions.getCurrent().getSession().getAttribute("mainInclude");
    }


    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (view instanceof Window) {
            currentWindow = (Window) view;
        } else if (view instanceof Panel) {
            currentPanel = (Panel) view;
        }
        binder = (AnnotateBinder) view.getAttribute("binder");
        mainInclude = (Include) Executions.getCurrent().getSession().getAttribute("mainInclude");
    }


    @GlobalCommand
    public void windowCancelBtn(@BindingParam("xWindow") Window x) {
        if (x != null)
            x.detach();
        else if (currentWindow != null)
            currentWindow.detach();
        clearArguments();
    }

    public boolean isValid(Component comp) {
        Collection<Component> components = null;
        if (comp != null)
            components = comp.getFellows();

        assert components != null;
        for (Component component : components) {
            if (component instanceof InputElement) {
                if (!((InputElement) component).isValid()) {
                    ((InputElement) component).setFocus(true);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid() {
        if (currentWindow != null) {
            return isValid(currentWindow);
        } else if (currentPanel != null) {
            return isValid(currentPanel);
        }
        return false;
    }

    public static final int XX_CENTURY = 1900;
    public static final int XXI_CENTURY = 2000;

    public int getAge(String register, int currentYear) {
        Integer bornYear = Integer.valueOf(register.substring(2, 4));
        Integer bornMonth = Integer.valueOf(register.substring(4, 6));

        if (bornMonth > 12) {
            bornMonth -= 20;
            bornYear += XXI_CENTURY;
        } else {
            bornYear += XX_CENTURY;
        }

        return currentYear - bornYear;
    }


    public Include getMainInclude() {
//        return (Include) Executions.getCurrent().getSession().getAttribute("mainInclude");
        return mainInclude;
    }

    public AnnotateBinder getBinder() {
        return binder;
    }

    public void setBinder(AnnotateBinder binder) {
        this.binder = binder;
    }

    private HashMap<String, Object> windowMap = new HashMap<>();

    public HashMap<String, Object> getWindowMap() {
        return windowMap;
    }

    public void setWindowMap(HashMap<String, Object> windowMap) {
        this.windowMap = windowMap;
    }

    public HashMap<String, Object> put(String key, Object value) {
        windowMap.put(key, value);
        return windowMap;
    }

    public HashMap<String, Object> putSingle(String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }


    public Object getArgument(String key) {
        return Executions.getCurrent().getArg().get(key);
    }

    public void clearArguments() {
        Executions.getCurrent().getArg().clear();
    }

    public Window getCurrentWindow() {
        return currentWindow;
    }

    public void setCurrentWindow(Window currentWindow) {
        this.currentWindow = currentWindow;
    }

    public Panel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(Panel currentPanel) {
        this.currentPanel = currentPanel;
    }


    public Treecell linkTreeCell(String label, final String src, final String register) {

        Treecell treecell = new Treecell();
        A a = new A(label);
        a.setStyle("text-decoration: underline;");
        a.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                if (src.contains("CompanyTab")) {
                    Executions.getCurrent().getSession().setAttribute("companyRegister", register);
                } else {
                    Executions.getCurrent().getSession().setAttribute("personRegister", register);
                }
                getMainInclude().setSrc(src);

            }
        });
        treecell.appendChild(a);
        return treecell;
    }

    public HashMap<String, String> getBackURL() {
        return backURL;
    }

    public void setBackURL(HashMap<String, String> backURL) {
        this.backURL = backURL;
    }


}