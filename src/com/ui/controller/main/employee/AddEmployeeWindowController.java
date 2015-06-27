package com.ui.controller.main.employee;

import com.model.bean.EmployeeBean;
import com.model.entity.Employee;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Articlaus on 6/24/15.
 */
public class AddEmployeeWindowController extends MainComponent {
    EmployeeBean employeeBean;
    Employee employee;
    List<String> maritalStatuses;
    String maritalStatus;
    EmployeeListPanelController employeeListPanelController;

    @Wire
    Combobox maritalBox;

    @Wire
    Cell imageCell;

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
        employee.setMaritalStatus(maritalStatus);
        if (employeeBean.save(employee) != null) {
            NotificationUtils.showSuccess();
            employeeListPanelController.refreshValues();
            getCurrentWindow().detach();
        } else
            NotificationUtils.showFailure();

    }

    @Command
    public void uploadPortrait() {
        //Make use of ZK Fileupload component to read the media...
        Media media = Fileupload.get();
        //If user selects a real file...
        System.out.println("media = " + Arrays.toString(media.getByteData()));
        if (media != null) {
            employee.setPortrait(media.getByteData());
        }
    }

    @Command
    public void uploadFile(BindContext ctx) {
        UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
        Media media = event.getMedia();
        employee.setPortrait(media.getByteData());
        Button imageBtn = new Button("Үзэх");
        imageBtn.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                getWindowMap().put("image", media.getByteData());
                Executions.createComponents("/main/other/PortraitWindow.zul", null, getWindowMap());
            }
        });
        imageBtn.setIconSclass("z-icon-image");
        imageCell.appendChild(imageBtn);
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
