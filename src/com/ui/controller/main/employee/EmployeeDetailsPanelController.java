package com.ui.controller.main.employee;

import com.model.entity.Emergency;
import com.model.entity.Employee;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Articlaus on 6/24/15.
 */
public class EmployeeDetailsPanelController extends MainComponent {
    List<String> maritalStatuses;
    String maritalStatus;
    @Wire
    Combobox maritalBox;

    Employee employee;
    Emergency emergency;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        calculateMaritalBox();
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Emergency getEmergency() {
        return emergency;
    }

    public void setEmergency(Emergency emergency) {
        this.emergency = emergency;
    }
}
