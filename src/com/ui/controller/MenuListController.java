package com.ui.controller;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

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
    public void home() {
        getMainInclude().setSrc("calendar.zul");
    }

    @Command
    public void employeeList() {
        getMainInclude().setSrc("/main/employee/employeeList.zul");
    }

    @Command
    public void timesheet() {
        getMainInclude().setSrc("/main/timesheet/TimesheetTab.zul");
    }

    @Command
    public void settings() {
        getMainInclude().setSrc("/main/settings/SettingTab.zul");
    }

    @Command
    public void finalTime() {
        getMainInclude().setSrc("/main/other/TimesheetFinalizePanel.zul");
    }

    @Command
    public void overtime() {
        getMainInclude().setSrc("/main/overtime/OvertimeTab.zul");
    }

    @Command
    public void leaveList() {
        getMainInclude().setSrc("main/leave/LeaveTabPanel.zul");
    }


    @Command
    public void probation() {
        getMainInclude().setSrc("main/probation/ProbationTab.zul");
    }

    @Command
    public void resolution() {
        getMainInclude().setSrc("main/resolution/ResolutionPanel.zul");
    }


}
