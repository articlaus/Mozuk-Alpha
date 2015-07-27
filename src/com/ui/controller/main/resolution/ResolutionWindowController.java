package com.ui.controller.main.resolution;

import com.model.entity.Department;
import com.model.entity.Document;
import com.model.entity.Employee;
import com.model.entity.Resolution;
import com.ui.component.CustomBandbox;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/27/2015.
 */
public class ResolutionWindowController extends MainComponent {
    Resolution resolution;
    Boolean isEditing;
    private CustomBandbox<Employee> employeeCustomBandbox;

    List<Document> documentList;

    @Wire
    Cell employeeCell, departmentCell, typeCell;
    private CustomBandbox<Department> departmentCustomBandbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        if (getArgument("resolution") != null) {
            isEditing = true;
            resolution = (Resolution) getArgument("resolution");
        } else {
            isEditing = false;
            resolution = new Resolution();
            documentList = new ArrayList<>();
        }

        //todo resolution list panel iig tohirgoo hiih

        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findAll", new String[]{"fullName"});
        employeeCustomBandbox.setWidth("100%");
        if (isEditing)
            employeeCustomBandbox.setSelectedT(resolution.getEmployeeCode());
        employeeCell.appendChild(employeeCustomBandbox);

        departmentCustomBandbox = new CustomBandbox<Department>(Department.class, "Department.findAll", new String[]{"name"});
        departmentCustomBandbox.setWidth("100%");
        if (isEditing)
            departmentCustomBandbox.setSelectedT(resolution.getDepartmentCode());
        departmentCell.appendChild(departmentCustomBandbox);

        //todo resolution type custom band box

    }

    @Command
    @NotifyChange("attachments")
    public void doUpload(@BindingParam("files") Media[] files) {
        if (isEditing) {
            //todo read
        } else {
            Document document;
            for (Media file : files) {
                document = new Document();
                document.setFileName(file.getName());
                document.setFileExtension(file.getContentType());

            }
        }
    }

    @Command
    public void save() {

        //todo save function
        if (isEditing) {

        }
    }


}
