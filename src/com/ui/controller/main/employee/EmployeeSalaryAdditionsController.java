package com.ui.controller.main.employee;

import com.model.bean.EmployeeBean;
import com.model.entity.Employee;
import com.model.entity.SalaryAdditions;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

import java.util.List;

/**
 * Created by Arti on 8/9/2015.
 */
public class EmployeeSalaryAdditionsController extends MainComponent {

    List<SalaryAdditions> salaryAdditionses;

    Employee employee;

    EmployeeBean employeeBean;

    @Wire
    Listbox employeeSalaryListbox;

    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
    }

    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        employee = (Employee) getMainInclude().getDynamicProperty("employee");
        loadValues();
    }

    public void loadValues() {
        salaryAdditionses = employeeBean.findBySalaryAddition(employee);
        getBinder().loadComponent(employeeSalaryListbox, true);
    }

    @Command
    public void refresh() {
        loadValues();
        NotificationUtils.showRefresh();
    }


    @Command
    public void add() {
        SalaryAdditions salaryAdditions = new SalaryAdditions();
        salaryAdditionses.add(salaryAdditions);
        getBinder().loadComponent(employeeSalaryListbox, true);
    }

    @Command
    public void remove(@BindingParam("entity") SalaryAdditions salaryAdditions) {
        if (salaryAdditions.getId() != null) {
            if (employeeBean.deleteSalaryAdditions(salaryAdditions.getId())) {
                salaryAdditionses.remove(salaryAdditions);
                NotificationUtils.showDeletion();
            }
        } else {
            salaryAdditionses.remove(salaryAdditions);
            NotificationUtils.showDeletion();
        }
        getBinder().loadComponent(employeeSalaryListbox, true);
    }

    @Command
    public void save() {
        for (SalaryAdditions salaryAdditionse : salaryAdditionses) {
            if (salaryAdditionse.getId() != null)
                employeeBean.deleteSalaryAdditions(salaryAdditionse.getId());
        }

        if (employeeBean.save(salaryAdditionses)) {
            NotificationUtils.showSuccess();
        } else {
            NotificationUtils.showDeletion();
        }
    }

    public List<SalaryAdditions> getSalaryAdditionses() {
        return salaryAdditionses;
    }

    public void setSalaryAdditionses(List<SalaryAdditions> salaryAdditionses) {
        this.salaryAdditionses = salaryAdditionses;
    }
}
