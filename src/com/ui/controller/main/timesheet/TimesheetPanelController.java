package com.ui.controller.main.timesheet;

import com.model.bean.CalculationBean;
import com.model.bean.EmployeeBean;
import com.model.bean.FileUploadBean;
import com.model.bean.OtherBean;
import com.model.entity.EmployeeWorkMonth;
import com.model.entity.WorkMonths;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Arti on 6/28/2015.
 */
public class TimesheetPanelController extends MainComponent {
    List<EmployeeWorkMonth> employeeWorkMonthList;
    EmployeeWorkMonth employeeWorkMonth;
    EmployeeBean employeeBean;
    OtherBean otherBean;
    CalculationBean calculationBean;


    @Wire
    Listbox employeeTimesheetListbox;

    @Wire
    Button calculateBtn;

    private FileUploadBean fileUploadBean;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
        otherBean = EBeanUtils.getBean(OtherBean.class);
        fileUploadBean = EBeanUtils.getBean(FileUploadBean.class);
        calculationBean = EBeanUtils.getBean(CalculationBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        refresh();
//        calculateBtn.setDisabled(otherBean.findByYearAndMonth().getIsLocked());
    }

    public void refresh() {
        employeeWorkMonthList = employeeBean.findWorkMonthByWorkMonth(otherBean.findByYearAndMonth());
        System.out.println("employeeWorkMonthList = " + employeeWorkMonthList.size());
        getBinder().loadComponent(employeeTimesheetListbox, true);
    }

    @Command
    public void save() {
        if (employeeBean.updateByEmployeeWorkMonths(employeeWorkMonthList)) {
            NotificationUtils.showSuccess();
            refresh();
        } else {
            NotificationUtils.showFailure();
        }
    }

    @Command
    public void calculate() {
        if (calculationBean.calculateTimeSheet(otherBean.findByYearAndMonth()) != null) {
            NotificationUtils.showMsg("Цалин амжилттай бодоглоо.");
        } else {
            NotificationUtils.showMsgWarning("Цалин бодоход алдаа гарлаа");
        }
    }

    @Command
    public void dlExcel() {
        try {
            File file = fileUploadBean.downloadXlsTemplate();
            if (file != null) {
                Filedownload.save(file, "xlsx");
//                fileUploadBean.deleteXlsTemplate();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Command
    public void ulExcel() {
        Executions.createComponents("/main/other/FileUploadWindow.zul", null, null);
    }

    public List<EmployeeWorkMonth> getEmployeeWorkMonthList() {
        return employeeWorkMonthList;
    }

    public void setEmployeeWorkMonthList(List<EmployeeWorkMonth> employeeWorkMonthList) {
        this.employeeWorkMonthList = employeeWorkMonthList;
    }

    public EmployeeWorkMonth getEmployeeWorkMonth() {
        return employeeWorkMonth;
    }

    public void setEmployeeWorkMonth(EmployeeWorkMonth employeeWorkMonth) {
        this.employeeWorkMonth = employeeWorkMonth;
    }
}
