package com.ui.controller.main.overtime;

import com.model.bean.EmployeeBean;
import com.model.bean.OvertimeBean;
import com.model.entity.Employee;
import com.model.entity.Overtime;
import com.model.entity.OvertimeDates;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Listbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/6/2015.
 */
public class OvertimeWindowController extends MainComponent {

    List<OvertimeDates> overtimeDateList;
    Boolean isEditing = false;
    EmployeeBean employeeBean;
    OvertimeBean overtimeBean;
    Overtime overtime;

    @Wire
    Listbox overtimeTimeListBox;

    @Wire
    Cell employeeCell;
    private CustomBandbox<Employee> employeeCustomBandbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
        overtimeBean = EBeanUtils.getBean(OvertimeBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        if (getArgument("overtime") != null) {
            overtime = (Overtime) getArgument("overtime");
            overtimeDateList = overtime.getOvertimeDatesList();
            isEditing = true;
        } else {
            overtime = new Overtime();
            overtimeDateList = new ArrayList<>();
            isEditing = false;
        }

        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findByIsActive", new String[]{"fullName"});
        employeeCustomBandbox.setWidth("100%");
        employeeCell.appendChild(employeeCustomBandbox);
    }

    @Command
    public void save() {
        overtime.setEmployeeCode(employeeCustomBandbox.getSelectedT());
        overtime.setOvertimeDatesList(overtimeDateList);
        if (isEditing) {
            //todo
        } else {
            //todo
        }
        getCurrentWindow().detach();
    }

    @Command
    public void addTime() {
        overtimeDateList.add(new OvertimeDates());
        getBinder().loadComponent(overtimeTimeListBox, true);
    }

    public List<OvertimeDates> getOvertimeDateList() {
        return overtimeDateList;
    }

    public void setOvertimeDateList(List<OvertimeDates> overtimeDateList) {
        this.overtimeDateList = overtimeDateList;
    }
}
