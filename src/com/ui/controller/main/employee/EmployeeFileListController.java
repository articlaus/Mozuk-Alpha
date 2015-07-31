package com.ui.controller.main.employee;

import com.model.bean.DocumentBean;
import com.model.bean.EmployeeBean;
import com.model.entity.Document;
import com.model.entity.Employee;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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

    @Wire
    private Listbox employeeFilesListBox;

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
        refresh();
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
        documentList = documentBean.findByEmployee(employee);
        getBinder().loadComponent(employeeFilesListBox, true);
    }

    @Command
    public void upload() {
        getWindowMap().put("employeeController", this);
        getWindowMap().put("employeeObject", employee);
        Executions.createComponents("main/employee/EmployeeFileUploadWindow.zul", null, getWindowMap());
    }

    @Command
    public void fileDownload(@BindingParam("documentId") Document document) {
        try {
            File file = new File(document.getFilePath());
            Filedownload.save(file,document.getContentType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Command
    public void fileRemove(@BindingParam("documentId") BigDecimal documentId) {
        if (documentBean.removeFile(documentId)) {
            refresh();
            NotificationUtils.showDeletion();
        } else {
            NotificationUtils.showFailure();
        }

    }
}
