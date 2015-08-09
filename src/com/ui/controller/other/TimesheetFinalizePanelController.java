package com.ui.controller.other;

import com.model.bean.CalculationBean;
import com.model.bean.EmployeeBean;
import com.model.bean.OtherBean;
import com.model.entity.EmployeeTimesheet;
import com.model.entity.WorkMonths;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Arti on 7/23/2015.
 */
public class TimesheetFinalizePanelController extends MainComponent {
    List<EmployeeTimesheet> employeeTimesheetList;
    CalculationBean calculationBean;
    OtherBean otherBean;
    EmployeeBean employeeBean;
    WorkMonths currentMonth;
    WorkMonths prevMonth;
    WorkMonths nextMonth;

    @Wire
    Listbox employeeTimeListbox;

    @Wire
    Button btnPrev, btnNext, lockBtn;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        calculationBean = EBeanUtils.getBean(CalculationBean.class);
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
        otherBean = EBeanUtils.getBean(OtherBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        currentMonth = otherBean.findByYearAndMonth();
        if (otherBean.findByPreviousYearAndMonth(currentMonth) != null)
            prevMonth = otherBean.findByPreviousYearAndMonth(currentMonth);
        else {
            prevMonth=new WorkMonths();
            btnPrev.setDisabled(true);
        }


        if (otherBean.findByNextYearAndMonth(currentMonth) != null)
            nextMonth = otherBean.findByNextYearAndMonth(currentMonth);
        else {
            nextMonth=new WorkMonths();
            btnNext.setDisabled(true);
        }

        loadValues();
    }

    public void loadValues() {
        employeeTimesheetList = calculationBean.findByWorkMonth(currentMonth);
        loadEmployeeAndPosition();
        getBinder().loadComponent(employeeTimeListbox, true);
    }


    private void loadEmployeeAndPosition() {
        for (EmployeeTimesheet employeeTimesheet : employeeTimesheetList) {
            employeeTimesheet.setEmployee(employeeBean.findByRegister(employeeTimesheet.getEmployeeRegister()));
            employeeTimesheet.setPosition(employeeTimesheet.getEmployee().getEmployeePosition().getPositionCode());
        }
    }

    public List<EmployeeTimesheet> getEmployeeTimesheetList() {
        return employeeTimesheetList;
    }

    public void setEmployeeTimesheetList(List<EmployeeTimesheet> employeeTimesheetList) {
        this.employeeTimesheetList = employeeTimesheetList;
    }


    @Command
    public void getPrevious() {
        currentMonth = prevMonth;
        if (otherBean.findByPreviousYearAndMonth(currentMonth) != null)
            prevMonth = otherBean.findByPreviousYearAndMonth(currentMonth);
        else {
            btnPrev.setDisabled(true);
        }

        btnNext.setDisabled(false);

        lockBtn.setDisabled(currentMonth.getIsLocked());
        getBinder().loadComponent(lockBtn, true);
        nextMonth = otherBean.findByNextYearAndMonth(currentMonth);
        loadValues();
    }

    @Command
    public void getNext() {
        currentMonth = nextMonth;
        prevMonth = otherBean.findByPreviousYearAndMonth(currentMonth);
        if (otherBean.findByNextYearAndMonth(currentMonth) != null)
            nextMonth = otherBean.findByNextYearAndMonth(currentMonth);
        else {
            btnNext.setDisabled(true);
        }
        lockBtn.setDisabled(currentMonth.getIsLocked());
        getBinder().loadComponent(lockBtn, true);
        btnPrev.setDisabled(false);
        loadValues();
    }

    @Command
    public void finalize() {
        currentMonth.setIsLocked(true);
        otherBean.updateByWorkMonths(currentMonth);
    }
}
