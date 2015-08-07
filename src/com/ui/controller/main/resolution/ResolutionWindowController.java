package com.ui.controller.main.resolution;

import com.model.bean.DocumentBean;
import com.model.bean.ResolutionBean;
import com.model.entity.*;
import com.model.util.BaseEJB;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Textbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/27/2015.
 */
public class ResolutionWindowController extends MainComponent {
    Resolution resolution;
    Boolean isEditing;
    private CustomBandbox<Employee> employeeCustomBandbox;
    ResolutionBean resolutionBean;
    DocumentBean documentBean;
    Boolean fileUploaded = false;

    List<Document> documentList;

    @Wire
    Cell employeeCell, departmentCell, typeCell;
    @Wire
    Textbox codeTxt;

    private CustomBandbox<Department> departmentCustomBandbox;
    private ResolutionPanelController controller;
    private CustomBandbox<ResolutionType> resolutionTypeCustomBandbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        resolutionBean = EBeanUtils.getBean(ResolutionBean.class);
        documentBean = EBeanUtils.getBean(DocumentBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        if (getArgument("resolution") != null) {
            isEditing = true;
            resolution = (Resolution) getArgument("resolution");
            documentList = documentBean.findByForeignKey(resolution.getCode());
            codeTxt.setDisabled(true);
        } else {
            isEditing = false;
            resolution = new Resolution();
            documentList = new ArrayList<>();
        }

        controller = (ResolutionPanelController) getArgument("controller");

        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findAll", new String[]{"fullName"});
        employeeCustomBandbox.setWidth("100%");
        if (isEditing)
            employeeCustomBandbox.setSelectedT(resolution.getEmployeeCode());
        employeeCell.appendChild(employeeCustomBandbox);

        departmentCustomBandbox = new CustomBandbox<Department>(Department.class, "Department.findAll", new String[]{"departmentTitle"});
        departmentCustomBandbox.setWidth("100%");
        if (isEditing)
            departmentCustomBandbox.setSelectedT(resolution.getDepartmentCode());
        departmentCell.appendChild(departmentCustomBandbox);

        resolutionTypeCustomBandbox = new CustomBandbox<ResolutionType>(ResolutionType.class, "ResolutionType.findAll", new String[]{"resolutionType"});
        resolutionTypeCustomBandbox.setWidth("100%");
        if (isEditing)
            resolutionTypeCustomBandbox.setSelectedT(resolution.getResolutionType());
        typeCell.appendChild(resolutionTypeCustomBandbox);

    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    @Command
    public void save() {

        resolution.setDocuments(documentList);
        resolution.setEmployeeCode(employeeCustomBandbox.getSelectedT());
        resolution.setDepartmentCode(departmentCustomBandbox.getSelectedT());
        resolution.setResolutionType(resolutionTypeCustomBandbox.getSelectedT());

        if (isEditing) {
            if (resolutionBean.update(resolution) != null) {
                NotificationUtils.showSuccess();
                controller.loadValues();
                getCurrentWindow().detach();
            } else {
                NotificationUtils.showFailure();
            }
        } else {
            if (resolutionBean.save(resolution) != null) {
                NotificationUtils.showSuccess();
                controller.loadValues();
                getCurrentWindow().detach();
            } else {
                NotificationUtils.showFailure();
            }
        }
    }

    public void addDocuments(Document document) {
        documentList.add(document);
    }


    @Command
    public void fileList() {
        getWindowMap().put("documentList", documentList);
        Executions.createComponents("main/other/FileListWindow.zul", null, getWindowMap());
    }

    public void setDocuments(List<Document> documents) {
        this.documentList = documents;
    }

    @Command
    public void fileUpload() {
        fileUploaded = true;
        getWindowMap().put("type", BaseEJB.DOC_TYPE_RESOLUTION);
        getWindowMap().put("controller", this);
        Executions.createComponents("main/other/FileUploadWindow.zul", null, getWindowMap());
    }

}
