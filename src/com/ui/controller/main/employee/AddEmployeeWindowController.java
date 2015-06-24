package com.ui.controller.main.employee;

import com.model.entity.Employee;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Articlaus on 6/24/15.
 */
public class AddEmployeeWindowController extends MainComponent {
    Employee employee;
    List<String> maritalStatuses;
    String maritalStatus;
    EmployeeListPanelController employeeListPanelController;

    @Wire
    Combobox maritalBox;

    @Init(superclass = true)
    @Override
    public void init() {

    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        employee = new Employee();

        calculateMaritalBox();
        employeeListPanelController = (EmployeeListPanelController) getArgument("employeeController");
        getBinder().loadComponent(maritalBox, true);
    }

    public void calculateMaritalBox() {
        maritalStatuses = new ArrayList<>();
        maritalStatuses.add("Гэрлээгүй");
        maritalStatuses.add("Гэрлэсэн");
        maritalStatuses.add("Бэлэвсэн");
        maritalStatuses.add("Салсан");
        maritalStatus = "Гэрлээгүй";
    }

    @Command
    public void save() {
        //todo
        employeeListPanelController.refreshValues();
    }

    public List<String> getMaritalStatuses() {
        return maritalStatuses;
    }

    public void setMaritalStatuses(List<String> maritalStatuses) {
        this.maritalStatuses = maritalStatuses;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
