package com.ui.controller.main.timesheet;

import com.model.bean.EmployeeBean;
import com.model.entity.EmployeeWorkMonth;
import com.model.entity.WorkMonths;
import com.ui.component.CustomBandbox;
import com.ui.component.SearchBox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Articlaus on 7/2/15.
 */
public class TimesheetHistoryPanelController extends MainComponent {

    List<EmployeeWorkMonth> employeeWorkMonthList;
    EmployeeBean employeeBean;
    private CustomBandbox<WorkMonths> workMonthsCustomBandbox;

    @Wire
    Cell workCell, searchCell;

    @Wire
    Listbox employeeTimesheetHistoryListbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        employeeWorkMonthList = employeeBean.findAllEmployeeWorkMonth();
        workMonthsCustomBandbox = new CustomBandbox<WorkMonths>(WorkMonths.class, "WorkMonths.findAll", new String[]{"yearAndMonth"});
//        workMonthsCustomBandbox.addEventListener(Events.ON_CHANGING, event -> {
//            employeeWorkMonthList = employeeBean.findWorkMonthByWorkMonth(workMonthsCustomBandbox.getSelectedT());
//            getBinder().loadComponent(employeeTimesheetHistoryListbox, true);
//        });
        workMonthsCustomBandbox.getListbox().addEventListener(Events.ON_CHANGING, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                employeeWorkMonthList = employeeBean.findWorkMonthByWorkMonth(workMonthsCustomBandbox.getSelectedT());
                getBinder().loadComponent(employeeTimesheetHistoryListbox, true);
            }
        });
        workCell.appendChild(workMonthsCustomBandbox);
        SearchBox<EmployeeWorkMonth> searchBox = new SearchBox<>(employeeWorkMonthList, new String[]{"employeeCode.firstname", "workMonthsid.yearAndMonth", "workMonth.totalWorkHours", "workedHours"}, employeeTimesheetHistoryListbox, getBinder());
        searchCell.appendChild(searchBox);
    }

    public List<EmployeeWorkMonth> getEmployeeWorkMonthList() {
        return employeeWorkMonthList;
    }

    public void setEmployeeWorkMonthList(List<EmployeeWorkMonth> employeeWorkMonthList) {
        this.employeeWorkMonthList = employeeWorkMonthList;
    }
}
