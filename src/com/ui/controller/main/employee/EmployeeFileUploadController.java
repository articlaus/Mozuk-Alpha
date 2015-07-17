package com.ui.controller.main.employee;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganbat.b on 7/8/2015.
 */
public class EmployeeFileUploadController extends MainComponent {
    List<String> attachments;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        attachments = new ArrayList<>();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
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
}
