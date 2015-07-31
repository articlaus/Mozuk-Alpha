package com.ui.controller.main.employee;

import com.model.bean.DocumentBean;
import com.model.entity.Document;
import com.model.entity.DocumentType;
import com.model.entity.Employee;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/8/2015.
 */
public class EmployeeFileUploadController extends MainComponent {
    List<String> attachments;

    private Document document;

    private Media file;

    @Wire
    private Cell fileTypeCell;
    private DocumentBean documentBean;
    private EmployeeFileListController controller;
    private CustomBandbox<DocumentType> bandbox;
    private Employee employee;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        documentBean = EBeanUtils.getBean(DocumentBean.class);
        attachments = new ArrayList<>();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);

        Object o = getArgument("employeeController");
        if (o !=null) {
            controller = (EmployeeFileListController) o;
        }

        Object emp = getArgument("employeeObject");
        if (emp !=null) {
            employee = (Employee) emp;
        }
        document =new Document();
        bandbox = new CustomBandbox<>(DocumentType.class, "DocumentType.findAll", new String[]{"name"});
        bandbox.setWidth("100%");
        fileTypeCell.appendChild(bandbox);
    }

    @Command
    @NotifyChange({"document", "file"})
    public void uploadFile(BindContext ctx) {
        UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
        file = event.getMedia();
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length());
        document.setFileName(fileName);
        document.setFileExtension(file.getFormat());
        document.setContentType(file.getContentType());


    }

    @Command
    public void btnSave() {
        if (file !=null && isValid() && bandbox.getSelectedT() !=null) {
            document.setBytes(file.getByteData());
            document.setEmployeeCode(employee);
            document.setDocumentTypeId(bandbox.getSelectedT());
            documentBean.save(document);
            controller.refresh();
            getCurrentWindow().detach();
            NotificationUtils.showSuccess();
        }
    }

    @Command
    @NotifyChange("attachments")
    public void doUpload(@BindingParam("files") Media[] files) {
        for (Media file : files) {
            attachments.add(file.getName());
        }
        System.out.println("attachments = " + attachments);
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
