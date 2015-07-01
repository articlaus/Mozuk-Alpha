package com.ui.controller.main.timesheet;

import com.model.entity.EmployeeWorkMonth;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;

import java.util.List;

/**
 * Created by Arti on 6/28/2015.
 */
public class TimesheetPanelController extends MainComponent {
    List<EmployeeWorkMonth> employeeWorkMonthList;

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
}
