package com.ui.controller.main.leave;

import com.model.bean.EmployeeBean;
import com.model.bean.LeaveAbsenceBean;
import com.model.bean.OtherBean;
import com.model.entity.Employee;
import com.model.entity.LeaveAbsence;
import com.model.entity.LeaveType;
import com.model.entity.WorkMonths;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Arti on 6/28/2015.
 */
public class LeaveMainPanelController extends MainComponent {
    List<LeaveAbsence> leaveList;
    LeaveAbsence leaveAbsence;
    LeaveAbsenceBean leaveAbsenceBean;
    EmployeeBean employeeBean;
    OtherBean otherBean;

    @Wire
    Listbox leaveMainListBox;

    @Wire
    Cell employeeCell, leaveTypeCell, workCell;

    @Wire
    Checkbox chkActive;
    private CustomBandbox<Employee> employeeCustomBandbox;
    private CustomBandbox<LeaveType> leaveTypeCustomBandbox;
    private CustomBandbox<WorkMonths> workMonthsCustomBandbox;


    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        leaveAbsenceBean = EBeanUtils.getBean(LeaveAbsenceBean.class);
        otherBean = EBeanUtils.getBean(OtherBean.class);
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        leaveAbsence = new LeaveAbsence();
        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findAll", new String[]{"firstname"});
        employeeCustomBandbox.setWidth("100%");
        employeeCell.appendChild(employeeCustomBandbox);

        leaveTypeCustomBandbox = new CustomBandbox<LeaveType>(LeaveType.class, "LeaveType.findAll", new String[]{"leaveType"});
        leaveTypeCustomBandbox.setWidth("100%");
        leaveTypeCustomBandbox.setPlaceholder("Амралтын Төрөл");
        leaveTypeCell.appendChild(leaveTypeCustomBandbox);

        workMonthsCustomBandbox = new CustomBandbox<WorkMonths>(WorkMonths.class, "WorkMonths.findAll", new String[]{"yearAndMonth"});
        workMonthsCustomBandbox.setWidth("100%");
        workMonthsCustomBandbox.setPlaceholder("Ажлын Сар");
        workCell.appendChild(workMonthsCustomBandbox);

        refresh();
    }

    public void refresh() {
        leaveList = leaveAbsenceBean.findByWorkMonths(otherBean.findByYearAndMonth());
        getBinder().loadComponent(leaveMainListBox, true);
    }

    @Command
    @NotifyChange("leaveAbsence")
    public void editLeave(@BindingParam("leave") LeaveAbsence leaveAbsence) {
        this.leaveAbsence = leaveAbsence;
        employeeCustomBandbox.setSelectedT(leaveAbsence.getEmployeeCode());
        leaveTypeCustomBandbox.setSelectedT(leaveAbsence.getLeaveTypeId());
        workMonthsCustomBandbox.setSelectedT(leaveAbsence.getWorkMonthsid());
        chkActive.setChecked(leaveAbsence.getIsPaid());
    }

    @Command
    @NotifyChange("leaveAbsence")
    public void deleteLeave(@BindingParam("leave") LeaveAbsence leaveAbsence) {
        if (leaveAbsenceBean.delete(leaveAbsence.getId())) {
            NotificationUtils.showDeletion();
            refresh();
        } else {
            NotificationUtils.showFailure();
        }
    }

    @Command
    @NotifyChange("leaveAbsence")
    public void save() {
        leaveAbsence.setEmployeeCode(employeeCustomBandbox.getSelectedT());
        leaveAbsence.setLeaveTypeId(leaveTypeCustomBandbox.getSelectedT());
        leaveAbsence.setWorkMonthsid(workMonthsCustomBandbox.getSelectedT());
        leaveAbsence.setIsPaid(chkActive.isChecked());
        if (leaveAbsence.getId() != null) {
            if (leaveAbsenceBean.update(leaveAbsence) != null) {
                NotificationUtils.showSuccess();
                refresh();
                clearValues();
            } else
                NotificationUtils.showFailure();
        } else {
            if (leaveAbsenceBean.save(leaveAbsence) != null) {
                NotificationUtils.showSuccess();
                refresh();
                clearValues();
            } else
                NotificationUtils.showFailure();
        }
    }

    @Command
    @NotifyChange("leaveAbsence")
    public void clearValues() {
        leaveAbsence = new LeaveAbsence();
        employeeCustomBandbox.setSelectedT(null);
        leaveTypeCustomBandbox.setSelectedT(null);
        workMonthsCustomBandbox.setSelectedT(null);
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
