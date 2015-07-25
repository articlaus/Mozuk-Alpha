package com.ui.controller.other;

import com.model.bean.OtherBean;
import com.model.entity.Department;
import com.model.entity.Employee;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;

/**
 * Created by Arti on 6/26/2015.
 */
public class DepartmentWindowController extends MainComponent {
    Department department;
    OtherBean otherBean;

    @Wire
    Cell headCell;

    Boolean isEditing;
    private CompanySettingsPanelController panelController;
    private CustomBandbox<Employee> customBandbox;

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
        customBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findAll", new String[]{"fullName"});
        customBandbox.setWidth("100%");
        headCell.appendChild(customBandbox);

        if (getArgument("department") != null) {
            department = (Department) getArgument("department");
            if (department.getDepartmentHeads() != null)
                customBandbox.setSelectedT(department.getDepartmentHeads().getEmployeeCode());
            isEditing = true;
        } else {
            department = new Department();
            isEditing = false;
        }
        panelController = (CompanySettingsPanelController) getArgument("listPanel");


    }

    @Command
    public void save() {
        if (isEditing) {
            if (otherBean.updateByDepartment(department) != null) {
                NotificationUtils.showSuccess();
                panelController.loadValues();
                getCurrentWindow().detach();
            } else {
                NotificationUtils.showFailure();
            }
        } else {
            if (otherBean.saveByDepartment(department) != null) {
                NotificationUtils.showSuccess();
                panelController.loadValues();
                getCurrentWindow().detach();
            } else {
                NotificationUtils.showFailure();
            }
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
