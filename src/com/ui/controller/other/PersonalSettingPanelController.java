package com.ui.controller.other;

import com.model.bean.UserBean;
import com.model.entity.Users;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import java.util.List;

/**
 * Created by Articlaus on 7/17/15.
 */
public class PersonalSettingPanelController extends MainComponent {
    UserBean userBean;
    private Users currentUser;
    private List<Users> usersList;
    @Wire
    Textbox psp_oldPassTxt, psp_newPassTxt, psp_reTypeTxt;

    @Wire
    Listbox usersListBox;

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
        currentUser = (Users) Executions.getCurrent().getSession().getAttribute("currentUser");
        loadValues();
    }

    public void loadValues() {
        usersList = userBean.findAll();
        getBinder().loadComponent(usersListBox, true);
    }

    @Command
    public void savePass() {
        if (psp_newPassTxt.getValue().equals(psp_reTypeTxt.getValue()))
            userBean.changePassword(currentUser.getUsername(), psp_oldPassTxt.getValue(), psp_newPassTxt.getValue());
        else {
            NotificationUtils.showMsgWarning("Нууц үгнүүд хоорондоо таарахгүй байна");
        }
    }

    @Command
    public void editUser(@BindingParam("user") Users users){

    }
}
