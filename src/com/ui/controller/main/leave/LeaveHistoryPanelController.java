package com.ui.controller.main.leave;

import com.model.entity.LeaveAbsence;
import com.model.entity.WorkMonths;
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
public class LeaveHistoryPanelController extends MainComponent {
    List<LeaveAbsence> leaveList;
    LeaveAbsence leaveAbsence;
    private CustomBandbox<WorkMonths> workMonthsCustomBandbox;
    @Wire
    Cell workCell;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        workMonthsCustomBandbox = new CustomBandbox<WorkMonths>(WorkMonths.class, "WorkMonths.findAll", new String[]{"month"});
        workCell.appendChild(workMonthsCustomBandbox);

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
