package com.ui.controller.other;

import com.model.entity.EmployeeTimesheet;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;

import java.util.List;

/**
 * Created by Arti on 7/23/2015.
 */
public class TimesheetFinalizePanelController extends MainComponent {
    List<EmployeeTimesheet> employeeTimesheetList;

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

    public List<EmployeeTimesheet> getEmployeeTimesheetList() {
        return employeeTimesheetList;
    }

    public void setEmployeeTimesheetList(List<EmployeeTimesheet> employeeTimesheetList) {
        this.employeeTimesheetList = employeeTimesheetList;
    }


    @Command
    public void getPrevious() {
        //todo
    }

    @Command
    public void getNext() {
        //todo
    }

    @Command
    public void finalize() {
        //todo
    }
}
