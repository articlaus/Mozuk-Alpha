package com.ui.controller.main.leave;

import com.model.entity.Employee;
import com.model.entity.LeaveAbsence;
import com.model.entity.LeaveType;
import com.ui.component.CustomBandbox;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;

import java.util.List;

/**
 * Created by Arti on 6/28/2015.
 */
public class LeaveMainPanelController extends MainComponent {
    List<LeaveAbsence> leaveList;
    LeaveAbsence leaveAbsence;

    @Wire
    Cell employeeCell, leaveTypeCell;
    private CustomBandbox<Employee> employeeCustomBandbox;
    private CustomBandbox<LeaveType> leaveTypeCustomBandbox;


    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findAll", new String[]{"firstname"});
        employeeCustomBandbox.setWidth("100%");
        employeeCell.appendChild(employeeCustomBandbox);

        leaveTypeCustomBandbox = new CustomBandbox<LeaveType>(LeaveType.class, "LeaveType.findAll", new String[]{"leaveType"});
        leaveTypeCustomBandbox.setWidth("100%");
        leaveTypeCell.appendChild(leaveTypeCustomBandbox);
    }

    public List<LeaveAbsence> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<LeaveAbsence> leaveList) {
        this.leaveList = leaveList;
    }

    public LeaveAbsence getLeaveAbsence() {
        return leaveAbsence;
    }

    public void setLeaveAbsence(LeaveAbsence leaveAbsence) {
        this.leaveAbsence = leaveAbsence;
    }
}