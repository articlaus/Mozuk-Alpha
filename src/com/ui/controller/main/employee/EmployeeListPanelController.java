package com.ui.controller.main.employee;

import com.model.bean.EmployeeBean;
import com.model.entity.Employee;
import com.model.entity.EmployeePosition;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Articlaus on 6/24/15.
 */
public class EmployeeListPanelController extends MainComponent {


    EmployeeBean employeeBean;
    List<EmployeePosition> employeeList;

    Employee selectedEmployee;
    @Wire
    Listbox employeeListBox;


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
        refreshValues();
    }

    @Command
    public void addEmployee() {
        getWindowMap().put("employeeController", this);
        Executions.createComponents("main/employee/AddEmployeeWindow.zul", null, getWindowMap());
    }

    @Command
    public void editEmployee(@BindingParam("btnEdit") Employee employee) {
        getMainInclude().setDynamicProperty("employee", selectedEmployee);
        getMainInclude().setSrc("/main/employee/EmployeeDetails.zul");
        //todo
    }

    public void refreshValues() {
        employeeList = employeeBean.findByIsActive(true);
        getBinder().loadComponent(employeeListBox, true);
    }

    public List<EmployeePosition> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeePosition> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}
