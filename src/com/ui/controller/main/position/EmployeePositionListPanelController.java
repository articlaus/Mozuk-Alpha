package com.ui.controller.main.position;

import com.model.bean.EmployeeBean;
import com.model.entity.Employee;
import com.model.entity.EmployeePosition;
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
 * Created by Arti on 6/26/2015.
 */
public class EmployeePositionListPanelController extends MainComponent {
    Employee employee;
    EmployeeBean employeeBean;
    EmployeePosition employeePosition;
    List<EmployeePosition> employeePositions;

    @Wire
    Listbox employeePositionListBox;

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
        employee = (Employee) getMainInclude().getDynamicProperty("employee");
        refresh();
    }

    @Command
    public void addPosition() {
        getWindowMap().put("type", 1);
        getWindowMap().put("employee", employee);
        getWindowMap().put("list", this);
        Executions.createComponents("/main/position/EmployeePositionWindow.zul", null, getWindowMap());
    }

    @Command
    public void editPosition() {
        if (employeePosition != null) {
            getWindowMap().put("type", 2);
            getWindowMap().put("employee", employee);
            getWindowMap().put("employeePosition", employeePosition);
            getWindowMap().put("list", this);
            Executions.createComponents("/main/position/EmployeePositionWindow.zul", null, getWindowMap());
        } else {
            NotificationUtils.showSelectValueMsg();
        }
    }

    @Command
    public void editSalary() {
        if (employeePosition != null) {
            getWindowMap().put("type", 3);
            getWindowMap().put("employee", employee);
            getWindowMap().put("employeePosition", employeePosition);
            getWindowMap().put("list", this);
            Executions.createComponents("/main/position/EmployeePositionWindow.zul", null, getWindowMap());
        } else {
            NotificationUtils.showSelectValueMsg();
        }
    }

    public void refresh() {
        employeePositions = employeeBean.findPositionByEmployee(employee);
        getBinder().loadComponent(employeePositionListBox, true);
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
    }

    public List<EmployeePosition> getEmployeePositions() {
        return employeePositions;
    }

    public void setEmployeePositions(List<EmployeePosition> employeePositions) {
        this.employeePositions = employeePositions;
    }
}
