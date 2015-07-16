package com.ui.controller.main.position;

import com.model.bean.EmployeeBean;
import com.model.entity.Department;
import com.model.entity.Employee;
import com.model.entity.EmployeePosition;
import com.model.entity.Position;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;

/**
 * Created by Arti on 6/26/2015.
 */
public class EmployeePositionWindowController extends MainComponent {

    EmployeeBean employeeBean;
    Employee employee;
    EmployeePosition employeePosition;
    EmployeePositionListPanelController listPanelController;
    int type = 1;

    @Wire
    Cell positionCell, departmentCell;
    @Wire
    Checkbox chkActive;
    private CustomBandbox<Position> positionCustomBandbox;
    private CustomBandbox<Department> departmentCustomBandbox;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        employeeBean = EBeanUtils.getBean(EmployeeBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        type = (int) getArgument("type");

        employee = (Employee) getArgument("employee");
        listPanelController = (EmployeePositionListPanelController) getArgument("list");

        if (type == 2) {
            employeePosition = (EmployeePosition) getArgument("employeePosition");
            chkActive.setChecked(employeePosition.getIsActive());
        } else if (type == 1) {
            employeePosition = new EmployeePosition();
        } else if (type == 3) {
            employeePosition = (EmployeePosition) getArgument("employeePosition");
            chkActive.setChecked(employeePosition.getIsActive());
        }

        positionCustomBandbox = new CustomBandbox<Position>(Position.class, "Position.findAll", new String[]{"positionTitle"});
        positionCustomBandbox.setStyle("width=100%");
        positionCell.appendChild(positionCustomBandbox);
        if (type == 3) {
            positionCustomBandbox.setDisabled(true);
            positionCustomBandbox.setSelectedT(employeePosition.getPositionCode());
        } else if (type == 2)
            positionCustomBandbox.setSelectedT(employeePosition.getPositionCode());

        departmentCustomBandbox = new CustomBandbox<Department>(Department.class, "Department.findAll", new String[]{"departmentTitle"});
        departmentCustomBandbox.setStyle("width=100%");
        departmentCell.appendChild(departmentCustomBandbox);
        if (type == 3) {
            departmentCustomBandbox.setDisabled(true);
            departmentCustomBandbox.setSelectedT(employeePosition.getDepartmentCode());
        } else if (type == 2)
            departmentCustomBandbox.setSelectedT(employeePosition.getDepartmentCode());
    }

    @Command
    public void check(@BindingParam("checked") Boolean checked) {
        employeePosition.setIsActive(checked);
    }

    @Command
    public void save() {
        if (type == 1) {
            employeePosition.setEmployeeCode(employee);
            employeePosition.setPositionCode(positionCustomBandbox.getSelectedT());
            employeePosition.setDepartmentCode(departmentCustomBandbox.getSelectedT());
            if (employeeBean.saveByEmployeePosition(employeePosition) != null) {
                NotificationUtils.showSuccess();
            } else
                NotificationUtils.showFailure();
        } else if (type == 2) {
            employeePosition.setEmployeeCode(employee);
            employeePosition.setPositionCode(positionCustomBandbox.getSelectedT());
            employeePosition.setDepartmentCode(departmentCustomBandbox.getSelectedT());
            if (employeeBean.updateByEmployeePosition(employeePosition) != null)
                NotificationUtils.showSuccess();
            else
                NotificationUtils.showFailure();
        } else if (type == 3) {
            if (employeeBean.updateByEmployeePosition(employeePosition) != null)
                NotificationUtils.showSuccess();
            else
                NotificationUtils.showFailure();
        }
        listPanelController.refresh();
        getCurrentWindow().detach();

    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
    }
}
