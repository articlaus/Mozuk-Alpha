package com.ui.controller.other;

import com.model.bean.DocumentBean;
import com.model.entity.Document;
import com.model.util.BaseEJB;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.controller.main.leave.LeaveMainPanelController;
import com.ui.controller.main.overtime.OvertimeWindowController;
import com.ui.controller.main.probation.ProbationWindowController;
import com.ui.controller.main.resolution.ResolutionWindowController;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Filedownload;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User on 8/7/2015.
 */
public class FileListWindowController extends MainComponent {

    List<Document> documentList;
    DocumentBean documentBean;
    BigDecimal type;
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
        documentList = (List<Document>) getArgument("documentList");

        if (type.equals(BaseEJB.DOC_TYPE_RESOLUTION)) {
            resolutionWindowController = (ResolutionWindowController) getArgument("controller");
        } else if (type.equals(BaseEJB.DOC_TYPE_OVERTIME)) {
            overtimeWindowController = (OvertimeWindowController) getArgument("controller");
        } else if (type.equals(BaseEJB.DOC_TYPE_PROBATION)) {
            probationWindowController = (ProbationWindowController) getArgument("controller");
        } else if (type.equals(BaseEJB.DOC_TYPE_LEAVE)) {
            leaveMainPanelController = (LeaveMainPanelController) getArgument("controller");
        }

    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }


    @Command
    public void fileDownload(@BindingParam("documentId") Document document) {
        try {
            File file = new File(document.getFilePath());
            Filedownload.save(file, document.getContentType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Command
    public void fileRemove(@BindingParam("documentId") BigDecimal documentId) {
        for (Document document : documentList) {
            if (document.getId().equals(documentId)) {
                documentList.remove(document);
            }
        }

        if (documentBean.removeFile(documentId)) {
            if (type.equals(BaseEJB.DOC_TYPE_RESOLUTION)) {
                resolutionWindowController.setDocuments(documentList);
            } else if (type.equals(BaseEJB.DOC_TYPE_OVERTIME)) {
                overtimeWindowController.setDocumentList(documentList);
            } else if (type.equals(BaseEJB.DOC_TYPE_PROBATION)) {
                probationWindowController.setDocuments(documentList);
            } else if (type.equals(BaseEJB.DOC_TYPE_LEAVE)) {
                leaveMainPanelController.setDocuments(documentList);
            }
            NotificationUtils.showDeletion();
        } else {
            NotificationUtils.showFailure();
        }

    }

}
