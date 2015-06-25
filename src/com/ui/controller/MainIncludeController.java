package com.ui.controller;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;

/**
 * Created by Articlaus on 6/23/15.
 */
public class MainIncludeController extends MainComponent {
    @Wire
    Include mainInclude;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        System.out.println("mainInclude = " + mainInclude);
        Executions.getCurrent().getSession().setAttribute("mainInclude", mainInclude);
        System.out.println("Executions.getCurrent().getSession().getAttribute(\"mainInclude\") = " + Executions.getCurrent().getSession().getAttribute("mainInclude"));
    }
}
