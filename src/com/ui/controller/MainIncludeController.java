package com.ui.controller;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;

/**
 * Created by Articlaus on 6/23/15.
 */
public class MainIncludeController {
    @Wire
    Include mainInclude;


    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        System.out.println("mainInclude = " + mainInclude);
        Executions.getCurrent().getSession().setAttribute("mainInclude", mainInclude);
        System.out.println("Executions.getCurrent().getSession().getAttribute(\"mainInclude\") = " + Executions.getCurrent().getSession().getAttribute("mainInclude"));
    }
}
