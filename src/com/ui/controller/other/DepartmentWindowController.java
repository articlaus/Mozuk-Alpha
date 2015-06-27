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
        department = new Department();
    }

    @Command
    public void save() {
        if (otherBean.saveByDepartment(department) != null) {
            NotificationUtils.showSuccess();
        } else {
            NotificationUtils.showFailure();
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
