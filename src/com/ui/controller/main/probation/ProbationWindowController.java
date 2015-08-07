package com.ui.controller.main.probation;

import com.model.bean.ProbationBean;
import com.model.entity.Department;
import com.model.entity.Document;
import com.model.entity.Employee;
import com.model.entity.Probation;
import com.model.util.BaseEJB;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/27/2015.
 */
public class ProbationWindowController extends MainComponent {

    ProbationBean probationBean;
    Boolean isEditing;
    Probation probation;
    List<Document> documents;

    @Wire
    Cell employeeCell, departmentCell;
    private CustomBandbox<Employee> employeeCustomBandbox;
    private CustomBandbox<Department> departmentCustomBandbox;


    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        probationBean = EBeanUtils.getBean(ProbationBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        if (getArgument("probation") != null) {
            probation = (Probation) getArgument("probation");
            documents = probation.getDocuments();
            isEditing = true;
        } else {
            probation = new Probation();
            documents = new ArrayList<>();
            isEditing = false;
        }

        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findAll", new String[]{"fullName"});
        employeeCustomBandbox.setWidth("100%");
        employeeCell.appendChild(employeeCustomBandbox);

        departmentCustomBandbox = new CustomBandbox<Department>(Department.class, "Department.findAll", new String[]{"departmentTitle"});
        departmentCustomBandbox.setWidth("100%");
        departmentCell.appendChild(departmentCustomBandbox);
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    @Command
    public void fileUpload() {
        getWindowMap().put("type", BaseEJB.DOC_TYPE_PROBATION);
        getWindowMap().put("controller", this);
        Executions.createComponents("main/other/FileUploadWindow.zul", null, getWindowMap());
    }

    @Command
    public void fileList() {
        getWindowMap().put("documentList", documents);
            Executions.createComponents("main/other/FileListWindow.zul", null, getWindowMap());
    }


    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Probation getProbation() {
        return probation;
    }

    public void setProbation(Probation probation) {
        this.probation = probation;
    }

    @Command
    public void save() {
        if (probationBean.save(probation) != null) {
            NotificationUtils.showSuccess();
        }
    }

}
