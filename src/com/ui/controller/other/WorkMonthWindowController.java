package com.ui.controller.other;

import com.model.bean.OtherBean;
import com.model.entity.WorkMonths;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Articlaus on 8/2/15.
 */
public class WorkMonthWindowController extends MainComponent {

    OtherBean otherBean;
    WorkMonths workMonth;
    WorkMonths curWorkMonth;

    @Wire
    Label workMonthLbl;
    private WorkMonthsPanelController workMonthsPanelController;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        otherBean = EBeanUtils.getBean(OtherBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        Calendar calendar = Calendar.getInstance();
        workMonthsPanelController = (WorkMonthsPanelController) getArgument("panel");
        if (otherBean.findByYearAndMonth() == null) {
            workMonth = new WorkMonths();
            workMonth.setYear(calendar.get(Calendar.YEAR));
            workMonth.setMonth(calendar.get(Calendar.MONTH) + 1);
            workMonth.setIsLocked(false);
        } else {
            curWorkMonth = otherBean.findByYearAndMonth();
            if (otherBean.findByNextYearAndMonth(curWorkMonth) == null) {
                workMonth = new WorkMonths();
                workMonth.setYear(calendar.get(Calendar.YEAR));
                workMonth.setMonth(calendar.get(Calendar.MONTH) + 2);
                workMonth.setIsLocked(false);
            } else {
                NotificationUtils.showMsgWarning("Ирэх сарын мэдээлэл бүртгэгдсэн байна.");
            }
        }
        if (workMonth.getMonth() > 12) {
            workMonth.setYear(workMonth.getYear() + 1);
            workMonth.setMonth(1);
        }
        workMonthLbl.setValue(workMonth.getYearAndMonth());
    }

    @Command
    public void save() {
        if (otherBean.saveByWorkMonths(workMonth) != null) {
            NotificationUtils.showSuccess();
            workMonthsPanelController.loadValues();
            getCurrentWindow().detach();
        } else {
            NotificationUtils.showFailure();
        }
    }

    public WorkMonths getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(WorkMonths workMonth) {
        this.workMonth = workMonth;
    }
}
