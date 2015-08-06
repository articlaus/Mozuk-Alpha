package com.ui.controller.other;

import com.model.bean.OtherBean;
import com.model.entity.WorkMonths;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Articlaus on 8/2/15.
 */
public class WorkMonthsPanelController extends MainComponent {

    @Wire
    Listbox workMonthsListBox;

    OtherBean otherBean;
    List<WorkMonths> workMonthsList;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        otherBean = EBeanUtils.getBean(OtherBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        loadValues();
    }


    public void loadValues() {
        workMonthsList = otherBean.findAllWorkMonths();
        getBinder().loadComponent(workMonthsListBox, true);
    }

    @Command
    public void add() {
        getWindowMap().put("panel", this);
        Executions.createComponents("main/settings/WorkMonthWindow.zul", null, getWindowMap());
    }

    @Command
    public void edit(@BindingParam("entity") WorkMonths workMonths) {
        getWindowMap().put("workMonth", workMonths);
        getWindowMap().put("panel", this);
        Executions.createComponents("main/settings/WorkMonthWindow.zul", null, getWindowMap());
    }

    @Command
    public void refresh() {
        loadValues();

    }

    public List<WorkMonths> getWorkMonthsList() {
        return workMonthsList;
    }

    public void setWorkMonthsList(List<WorkMonths> workMonthsList) {
        this.workMonthsList = workMonthsList;
    }
}
