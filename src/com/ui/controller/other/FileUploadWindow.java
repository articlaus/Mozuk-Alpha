package com.ui.controller.other;

import com.model.bean.DocumentBean;
import com.model.bean.FileUploadBean;
import com.model.entity.Document;
import com.model.entity.DocumentType;
import com.model.entity.Employee;
import com.model.entity.WorkMonths;
import com.model.util.BaseEJB;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.controller.main.employee.EmployeeFileListController;
import com.ui.controller.main.leave.LeaveMainPanelController;
import com.ui.controller.main.overtime.OvertimeWindowController;
import com.ui.controller.main.probation.ProbationWindowController;
import com.ui.controller.main.resolution.ResolutionWindowController;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huslee on 7/17/2015.
 */
public class FileUploadWindow extends MainComponent {

    private Document document;

    private Media file;

    private DocumentBean documentBean;
    //    private EmployeeFileListController controller;
//    private CustomBandbox<DocumentType> bandbox;
    private Employee employee;
    private BigDecimal type;
    private ResolutionWindowController resolutionWindowController;
    private OvertimeWindowController overtimeWindowController;
    private ProbationWindowController probationWindowController;
    private LeaveMainPanelController leaveMainPanelController;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        documentBean = EBeanUtils.getBean(DocumentBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);

        type = (BigDecimal) getArgument("type");

        if (type.equals(BaseEJB.DOC_TYPE_RESOLUTION)) {
            resolutionWindowController = (ResolutionWindowController) getArgument("controller");
        } else if (type.equals(BaseEJB.DOC_TYPE_OVERTIME)) {
            overtimeWindowController = (OvertimeWindowController) getArgument("controller");
        } else if (type.equals(BaseEJB.DOC_TYPE_PROBATION)) {
            probationWindowController = (ProbationWindowController) getArgument("controller");
        } else if (type.equals(BaseEJB.DOC_TYPE_LEAVE)) {
            leaveMainPanelController = (LeaveMainPanelController) getArgument("controller");
        }

        Object emp = getArgument("employeeObject");
        if (emp != null) {
            employee = (Employee) emp;
        }
        document = new Document();
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
        if (file != null && isValid()) {
            document.setBytes(file.getByteData());
            document.setEmployeeCode(employee);
            document.setDocumentTypeId(documentBean.findTypeById(type));
            Executions.getCurrent().getSession().setAttribute("document", document);
            addDocumentlist();
            getCurrentWindow().detach();
        }
    }

    public void addDocumentlist() {
        if (type.equals(BaseEJB.DOC_TYPE_RESOLUTION)) {
            resolutionWindowController.addDocuments(document);
        } else if (type.equals(BaseEJB.DOC_TYPE_OVERTIME)) {
            overtimeWindowController.addDocument(document);
        } else if (type.equals(BaseEJB.DOC_TYPE_PROBATION)) {
            probationWindowController.addDocument(document);
        } else if (type.equals(BaseEJB.DOC_TYPE_LEAVE)) {
            leaveMainPanelController.addDocuments(document);
        }
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
