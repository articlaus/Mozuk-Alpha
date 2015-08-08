package com.ui.controller.main.probation;

import com.model.bean.ProbationBean;
import com.model.entity.Probation;
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
 * Created by User on 8/7/2015.
 */
public class ProbationHistoryListPanelController extends MainComponent {

    List<Probation> probations;
    ProbationBean probationBean;

    @Wire
    Cell searchCell;
    @Wire
    Listbox probationActiveList;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        probationBean = EBeanUtils.getBean(ProbationBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        loadValues();
        SearchBox<Probation> searchBox = new SearchBox<>(probations, new String[]{"employeeCode.fullName", "departmentCode.departmentTitle", "employeeCode.fullName", "startDate", "endDate", "probationReason"}, probationActiveList, getBinder());
        searchCell.appendChild(searchBox);
    }

    public void loadValues() {
        probations = probationBean.findAll();
        getBinder().loadComponent(probationActiveList, true);
    }


    @Command
    public void edit(@BindingParam("entity") Probation probation) {
        getWindowMap().put("probation", probation);
        getWindowMap().put("controller", this);
        Executions.createComponents("/main/probation/ProbationWindow.zul", null, getWindowMap());
    }


    @Command
    public void refresh() {
        loadValues();
    }

    public List<Probation> getProbations() {
        return probations;
    }

    public void setProbations(List<Probation> probations) {
        this.probations = probations;
    }
}
