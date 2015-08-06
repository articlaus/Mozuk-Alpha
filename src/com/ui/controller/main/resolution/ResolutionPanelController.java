package com.ui.controller.main.resolution;

import com.model.bean.ResolutionBean;
import com.model.entity.Resolution;
import com.ui.component.SearchBox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Articlaus on 7/27/15.
 */
public class ResolutionPanelController extends MainComponent {

    ResolutionBean resolutionBean;

    List<Resolution> resolutionList;

    @Wire
    Listbox resolutionListBox;
    @Wire
    Cell searchCellResolution;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        resolutionBean = EBeanUtils.getBean(ResolutionBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        loadValues();
        SearchBox<Resolution> searchBox = new SearchBox<>(resolutionList, new String[]{"resolutionType.resolutionType", "employeeCode.fullName", "departmentCode.departmentTitle", "createdDate", "resolutionDescription"}, resolutionListBox, getBinder());
        searchCellResolution.appendChild(searchBox);
    }


    @Command
    public void addResolution() {
        getWindowMap().put("controller", this);
        Executions.createComponents("/main/resolution/ResolutionWindow.zul", null, getWindowMap());
    }

    @Command
    public void edit(@BindingParam("entity") Resolution resolution){
        getWindowMap().put("controller", this);
        getWindowMap().put("resolution",resolution);
        Executions.createComponents("/main/resolution/ResolutionWindow.zul", null, getWindowMap());
    }

    public void loadValues() {
        resolutionList = resolutionBean.findAll();
        getBinder().loadComponent(resolutionListBox, true);
    }

    public List<Resolution> getResolutionList() {
        return resolutionList;
    }

    public void setResolutionList(List<Resolution> resolutionList) {
        this.resolutionList = resolutionList;
    }
}
