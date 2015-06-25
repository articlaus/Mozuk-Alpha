package com.ui.controller.main.employee;

import com.model.entity.Employee;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.util.List;

/**
 * Created by Articlaus on 6/24/15.
 */
public class EmployeeListPanelController extends MainComponent {

    List<Employee> employeeList;

    Employee selectedEmployee;


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

    @Command
    public void addEmployee() {
        getWindowMap().put("employeeController",this);
        Executions.createComponents("main/employee/AddEmployeeWindow.zul", null, getWindowMap());
    }

    @Command
    public void editEmployee() {
        //todo
    }

    public void refreshValues(){
        //todo
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}
