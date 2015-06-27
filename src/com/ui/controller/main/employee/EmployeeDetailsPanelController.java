package com.ui.controller.main.employee;

import com.model.bean.EmployeeBean;
import com.model.entity.Emergency;
import com.model.entity.Employee;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
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
    EmployeeBean employeeBean;
    Emergency emergency;

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

        System.out.println("employee = " + employee);
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

    @Command
    public void uploadFile(BindContext ctx) {
        UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
        Media media = event.getMedia();
        employee.setPortrait(media.getByteData());
    }

    @Command
    public void viewImage() {
        getWindowMap().put("image", employee.getPortrait());
        Executions.createComponents("/main/other/PortraitWindow.zul", null, getWindowMap());
    }

    @Command
    public void save() {
        employee.setMaritalStatus(maritalStatus);
        if (employeeBean.update(employee) != null) {
            NotificationUtils.showSuccess();
        } else
            NotificationUtils.showFailure();
    }

    @Command
    public void back(){
        getMainInclude().setSrc("/main/employee/EmployeeList.zul");
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
