package com.ui.controller.main.timesheet;

import com.model.bean.EmployeeBean;
import com.model.bean.OtherBean;
import com.model.entity.EmployeeWorkMonth;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Arti on 6/28/2015.
 */
public class TimesheetPanelController extends MainComponent {
    List<EmployeeWorkMonth> employeeWorkMonthList;
    EmployeeWorkMonth employeeWorkMonth;
    EmployeeBean employeeBean;
    OtherBean otherBean;


    @Wire
    Listbox employeeTimesheetListbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
        otherBean = EBeanUtils.getBean(OtherBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        refresh();
    }

    public void refresh() {
        employeeWorkMonthList = employeeBean.findWorkMonthByWorkMonth(otherBean.findByYearAndMonth());
        getBinder().loadComponent(employeeTimesheetListbox, true);
    }

    @Command
    public void save() {
        //todo
    }

    @Command
    public void dlExcel() {
        //todo
    }

    @Command
    public void ulExcel() {
        //todo
    }

    public List<EmployeeWorkMonth> getEmployeeWorkMonthList() {
        return employeeWorkMonthList;
    }

    public void setEmployeeWorkMonthList(List<EmployeeWorkMonth> employeeWorkMonthList) {
        this.employeeWorkMonthList = employeeWorkMonthList;
    }

    public EmployeeWorkMonth getEmployeeWorkMonth() {
        return employeeWorkMonth;
    }

    public void setEmployeeWorkMonth(EmployeeWorkMonth employeeWorkMonth) {
        this.employeeWorkMonth = employeeWorkMonth;
    }
}
