package com.ui.controller.main.leave;

import com.model.bean.LeaveAbsenceBean;
import com.model.entity.LeaveAbsence;
import com.model.entity.WorkMonths;
import com.ui.component.CustomBandbox;
import com.ui.component.SearchBox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Arti on 6/28/2015.
 */
public class LeaveHistoryPanelController extends MainComponent {
    List<LeaveAbsence> leaveList;
    LeaveAbsence leaveAbsence;
    LeaveAbsenceBean leaveAbsenceBean;
    private CustomBandbox<WorkMonths> workMonthsCustomBandbox;
    @Wire
    Cell workCell, searchCell;

    @Wire
    Listbox leaveHistoryListBox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        leaveAbsenceBean = EBeanUtils.getBean(LeaveAbsenceBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        leaveList = leaveAbsenceBean.findAll();
        workMonthsCustomBandbox = new CustomBandbox<>(WorkMonths.class, "WorkMonths.findAll", new String[]{"yearAndMonth"});
        workMonthsCustomBandbox.getListbox().addEventListener(Events.ON_SELECT, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                loadByMonths(workMonthsCustomBandbox.getSelectedT());
            }
        });
        workCell.appendChild(workMonthsCustomBandbox);
        SearchBox<LeaveAbsence> searchBox = new SearchBox<>(leaveList, new String[]{"startDate", "endDate", "employeeCode.fullName", "workMonthsId.month"}, leaveHistoryListBox, getBinder());
        searchCell.appendChild(searchBox);
    }

    public void loadByMonths(WorkMonths workMonths) {
        leaveList = leaveAbsenceBean.findByWorkMonths(workMonths);
        getBinder().loadComponent(leaveHistoryListBox, true);
    }

    @Command
    public void editLeave(@BindingParam("leave") LeaveAbsence leaveAbsence) {
        //todo Jasper report oor haruulah
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
