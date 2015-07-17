package com.ui.controller.other;

import com.model.bean.FileUploadBean;
import com.model.entity.WorkMonths;
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

import java.util.HashMap;

/**
 * Created by huslee on 7/17/2015.
 */
public class FileUploadWindow extends MainComponent {

    private FileUploadBean fileUploadBean;
    private Media file;
    private String fileName;

    @Wire
    private Cell activeMonthCell;
    private CustomBandbox<WorkMonths> monthsCustomBandbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        fileUploadBean = EBeanUtils.getBean(FileUploadBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        HashMap<String, Object> map = new HashMap<>();
        map.put("isLocked", false);
        monthsCustomBandbox = new CustomBandbox<>(WorkMonths.class, "WorkMonths.findByIsLocked", new String[]{"yearAndMonth"}, 0, map);
        monthsCustomBandbox.setWidth("100%");
        monthsCustomBandbox.getListbox().setWidth("300px");
        activeMonthCell.appendChild(monthsCustomBandbox);
    }

    @Command
    @NotifyChange({"file", "fileName"})
    public void uploadFile(BindContext ctx) {
        UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
        file = event.getMedia();
        fileName = file.getName();
    }


    @Command
    public void btnSave() {
        if (isValid() && monthsCustomBandbox.getSelectedT() !=null) {
            fileUploadBean.uploadXlsx(file.getStreamData(), file.getFormat(), monthsCustomBandbox.getSelectedT());
//            refresh();
            getCurrentWindow().detach();
            NotificationUtils.showMsg("Амжилттай хадгалагдлаа.");
        }
    }

    public Media getFile() {
        return file;
    }

    public void setFile(Media file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
