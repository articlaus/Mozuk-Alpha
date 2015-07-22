package com.ui.controller.main.employee;

import com.model.bean.DocumentBean;
import com.model.bean.EmployeeBean;
import com.model.entity.Document;
import com.model.entity.Employee;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/8/2015.
 */
public class EmployeeFileListController extends MainComponent {
    EmployeeBean employeeBean;
    DocumentBean documentBean;
    List<Document> documentList;
    private Employee employee;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
        documentBean = EBeanUtils.getBean(DocumentBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        employee = (Employee) getMainInclude().getDynamicProperty("employee");
        documentList = new ArrayList<>();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    @Command
    public void refresh() {

    }

    @Command
    public void upload() {
        getWindowMap().put("employeeController", this);
        Executions.createComponents("main/employee/EmployeeFileUploadWindow.zul", null, getWindowMap());
    }
}
