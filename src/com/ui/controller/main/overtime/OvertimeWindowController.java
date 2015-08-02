package com.ui.controller.main.overtime;

import com.model.bean.DocumentBean;
import com.model.bean.EmployeeBean;
import com.model.bean.OvertimeBean;
import com.model.entity.*;
import com.model.util.BaseEJB;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Listbox;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/6/2015.
 */
public class OvertimeWindowController extends MainComponent {

    List<OvertimeDates> overtimeDateList;
    List<OvertimeDates> removeList;
    DocumentBean documentBean;
    Boolean isEditing = false;
    EmployeeBean employeeBean;
    OvertimeBean overtimeBean;
    Overtime overtime;
    OvertimeListPanelController listPanelController;
    List<Document> documentList;

    @Wire
    Listbox overtimeTimeListBox;

    @Wire
    Cell employeeCell, workMonthCell;
    private CustomBandbox<Employee> employeeCustomBandbox;
    private CustomBandbox<WorkMonths> workMonthsCustomBandbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
        overtimeBean = EBeanUtils.getBean(OvertimeBean.class);
        documentBean = EBeanUtils.getBean(DocumentBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        documentList = new ArrayList<>();

        if (getArgument("overtime") != null) {
            overtime = (Overtime) getArgument("overtime");
            overtimeDateList = overtime.getOvertimeDatesList();
            isEditing = true;
        } else {
            overtime = new Overtime();
            overtimeDateList = new ArrayList<>();
            isEditing = false;
        }
        removeList = new ArrayList<>();

        listPanelController = (OvertimeListPanelController) getArgument("overtimeList");

        employeeCustomBandbox = new CustomBandbox<Employee>(Employee.class, "Employee.findByActive", new String[]{"fullName"});
        employeeCustomBandbox.setWidth("100%");
        employeeCustomBandbox.setPlaceholder("Ажилтаныг сонгоно уу!");
        if (overtime.getId() != null) {
            employeeCustomBandbox.setSelectedT(overtime.getEmployeeCode());
        }
        employeeCell.appendChild(employeeCustomBandbox);

        workMonthsCustomBandbox = new CustomBandbox<WorkMonths>(WorkMonths.class, "WorkMonths.findAll", new String[]{"yearAndMonth"});
        workMonthsCustomBandbox.setWidth("100%");
        workMonthsCustomBandbox.setPlaceholder("Хамаарагдах ажлын сарыг сонгон уу!");
        if (overtime.getId() != null) {
            workMonthsCustomBandbox.setSelectedT(overtime.getWorkMonthsid());
        }
        workMonthCell.appendChild(workMonthsCustomBandbox);

    }

    @Command
    public void save() {
        overtime.setEmployeeCode(employeeCustomBandbox.getSelectedT());
        overtime.setOvertimeDatesList(overtimeDateList);
        overtime.setWorkMonthsid(workMonthsCustomBandbox.getSelectedT());
        if (isEditing) {
            if (overtimeBean.update(overtime) != null) {
                NotificationUtils.showSuccess();
            } else {
                NotificationUtils.showFailure();
            }
        } else {
            overtime = overtimeBean.saveOvertimeAndOvertimeDates(overtime);
            if (overtime != null) {
                documentBean.saveAll(overtime.getId().toString(), documentList, BaseEJB.DOC_TYPE_OVERTIME);
                NotificationUtils.showSuccess();
            } else {
                NotificationUtils.showFailure();
            }
        }

        for (OvertimeDates overtimeDates : removeList) {
            if (overtimeDates.getId() != null)
                overtimeBean.deleteByOvertimeDate(overtimeDates.getId());
        }
        listPanelController.refresh();
        getCurrentWindow().detach();
    }


    @Command
    @NotifyChange("documentList")
    public void doUpload(@BindingParam("files") Media[] files) {
        Document doc = new Document();
        for (Media file : files) {
            doc = new Document();
            doc.setBytes(file.getByteData());
            doc.setEmployeeCode(employeeCustomBandbox.getSelectedT());
            doc.setFileName(file.getName());
            documentList.add(doc);
        }
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    @Command
    public void addTime() {
        overtimeDateList.add(new OvertimeDates());
        getBinder().loadComponent(overtimeTimeListBox, true);
    }

    @Command
    public void removeDates(@BindingParam("dates") OvertimeDates overtimeDates) {
        overtimeDateList.remove(overtimeDates);
        removeList.add(overtimeDates);
        getBinder().loadComponent(overtimeTimeListBox, true);
    }

    public Overtime getOvertime() {
        return overtime;
    }

    public void setOvertime(Overtime overtime) {
        this.overtime = overtime;
    }

    public List<OvertimeDates> getOvertimeDateList() {
        return overtimeDateList;
    }

    public void setOvertimeDateList(List<OvertimeDates> overtimeDateList) {
        this.overtimeDateList = overtimeDateList;
    }
}
