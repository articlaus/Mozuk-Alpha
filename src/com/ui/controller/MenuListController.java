package com.ui.controller;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;

/**
 * Created by Articlaus on 6/23/15.
 */
public class MenuListController extends MainComponent {

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }
    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
    }

    @Command
    public void employeeList(){

    }



}
