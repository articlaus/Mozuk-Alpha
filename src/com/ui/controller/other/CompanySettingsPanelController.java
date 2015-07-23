package com.ui.controller.other;

import com.model.bean.OtherBean;
import com.model.entity.Department;
import com.model.entity.Position;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Arti on 7/22/2015.
 */
public class CompanySettingsPanelController extends MainComponent {
    OtherBean otherBean;

    List<Position> positionList;
    List<Department> departmentList;

    @Wire
    Listbox positionListBox, departmentListBox;

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
        loadValues();
    }

    public void loadValues() {
        departmentList = otherBean.findAllDepartment();
        positionList = otherBean.findAllPosition();
        getBinder().loadComponent(departmentListBox, true);
        getBinder().loadComponent(positionListBox, true);
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    @Command
    public void refresh() {
        loadValues();
    }

    @Command
    public void deletePosition(@BindingParam("entity") Position position) {
        if (otherBean.deleteByPosition(position.getCode())) {
            NotificationUtils.showDeletion();
            loadValues();
        } else {
            NotificationUtils.showFailure();
        }
    }

    @Command
    public void deleteDepartment(@BindingParam("entity") Department department) {
        if (otherBean.deleteByDepartment(department.getCode())) {
            NotificationUtils.showDeletion();
            loadValues();
        } else {
            NotificationUtils.showFailure();
        }
    }

    @Command
    public void addPosition() {
        getWindowMap().put("listPanel", this);
        Executions.createComponents("main/settings/PositionWindow.zul", null, getWindowMap());
    }

    @Command
    public void addDepartment() {
        getWindowMap().put("listPanel", this);
        Executions.createComponents("main/settings/DepartmentWindow.zul", null, getWindowMap());
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}
