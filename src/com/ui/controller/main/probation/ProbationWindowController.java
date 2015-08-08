package com.ui.controller.main.probation;

import com.model.bean.DocumentBean;
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
import org.zkoss.zul.Checkbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/27/2015.
 */
public class ProbationWindowController extends MainComponent {

    ProbationBean probationBean;
    DocumentBean documentBean;
    Boolean isEditing;
    Probation probation;
    List<Document> documents;

    @Wire
    Cell employeeCell, departmentCell;
    @Wire
    Checkbox chkActive;

    private CustomBandbox<Employee> employeeCustomBandbox;
    private CustomBandbox<Department> departmentCustomBandbox;


    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        probationBean = EBeanUtils.getBean(ProbationBean.class);
        documentBean = EBeanUtils.getBean(DocumentBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        if (getArgument("probation") != null) {
            probation = (Probation) getArgument("probation");
            documents = documentBean.findByForeignKey(probation.getId().toPlainString());
            if (documents == null || documents.size() == 0) {
                documents = new ArrayList<>();
            }
            isEditing = true;
            chkActive.setChecked(probation.getIsActive());
        } else {
            probation = new Probation();
            documents = new ArrayList<>();
            isEditing = false;
        }

        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findAll", new String[]{"fullName"});
        employeeCustomBandbox.setWidth("100%");
        if (probation.getEmployeeCode() != null)
            employeeCustomBandbox.setSelectedT(probation.getEmployeeCode());
        employeeCell.appendChild(employeeCustomBandbox);

        departmentCustomBandbox = new CustomBandbox<Department>(Department.class, "Department.findAll", new String[]{"departmentTitle"});
        departmentCustomBandbox.setWidth("100%");
        if (probation.getDepartmentCode() != null)
            departmentCustomBandbox.setSelectedT(probation.getDepartmentCode());
        departmentCell.appendChild(departmentCustomBandbox);
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    @Command
    public void uploadWindow() {
        getWindowMap().put("type", BaseEJB.DOC_TYPE_PROBATION);
        getWindowMap().put("controller", this);
        Executions.createComponents("main/other/FileUploadWindow.zul", null, getWindowMap());
    }

    @Command
    public void fileList() {
        getWindowMap().put("type", BaseEJB.DOC_TYPE_PROBATION);
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
        probation.setEmployeeCode(employeeCustomBandbox.getSelectedT());
        probation.setDepartmentCode(departmentCustomBandbox.getSelectedT());
        probation.setIsActive(chkActive.isChecked());
        for (Document document : documents) {
            document.setEmployeeCode(employeeCustomBandbox.getSelectedT());
        }
        probation.setDocuments(documents);
        if (isEditing) {
            if (probationBean.update(probation) != null) {
                NotificationUtils.showSuccess();
                getCurrentWindow().detach();
            } else {
                NotificationUtils.showFailure();
            }
        } else {
            if (probationBean.save(probation) != null) {
                NotificationUtils.showSuccess();
                getCurrentWindow().detach();
            } else {
                NotificationUtils.showFailure();
            }
        }
    }

}
