package com.ui.controller.other;

import com.model.bean.OtherBean;
import com.model.entity.Department;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;

/**
 * Created by Arti on 6/26/2015.
 */
public class DepartmentWindowController extends MainComponent {
    Department department;
    OtherBean otherBean;

    Boolean isEditing;
    private CompanySettingsPanelController panelController;

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

        if (getArgument("department") != null) {
            department = (Department) getArgument("department");
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
