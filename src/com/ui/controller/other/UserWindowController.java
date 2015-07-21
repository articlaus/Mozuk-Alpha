package com.ui.controller.other;

import com.model.bean.UserBean;
import com.model.entity.UserRoles;
import com.model.entity.Users;
import com.ui.component.CustomBandbox;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Textbox;

/**
 * Created by Arti on 7/21/2015.
 */
public class UserWindowController extends MainComponent {
    UserBean userBean;
    Boolean editing;
    Users user;

    @Wire
    Cell userTypeCell;

    @Wire
    Textbox passTxt1, passTxt2;
    private CustomBandbox<UserRoles> rolesCustomBandbox;
    private PersonalSettingPanelController personalSettingPanelController;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        userBean = EBeanUtils.getBean(UserBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        if (getArgument("user") != null) {
            user = (Users) getArgument("user");
            editing = true;
        } else {
            user = new Users();
            editing = false;
        }
        personalSettingPanelController = (PersonalSettingPanelController) getArgument("listCont");
        rolesCustomBandbox = new CustomBandbox<UserRoles>(UserRoles.class, "UserRoles.findAll", new String[]{"userRole"});
        rolesCustomBandbox.setWidth("100%");
        if (editing)
            rolesCustomBandbox.setSelectedT(user.getUserRoleId());
        userTypeCell.appendChild(rolesCustomBandbox);
    }

    @Command
    public void save() {
        if (editing) {
            if (userBean.update(user) != null) {
                NotificationUtils.showSuccess();
                personalSettingPanelController.loadValues();
                getCurrentWindow().detach();
            } else
                NotificationUtils.showFailure();
        } else {
            if (userBean.save(user) != null) {
                NotificationUtils.showSuccess();
                personalSettingPanelController.loadValues();
                getCurrentWindow().detach();
            } else
                NotificationUtils.showFailure();
        }
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
