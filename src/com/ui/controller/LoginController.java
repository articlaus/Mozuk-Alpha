package com.ui.controller;

import com.model.bean.LoginBean;
import com.model.entity.Users;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 * Created by Articlaus on 6/19/15.
 */
public class LoginController extends MainComponent {

    Users user;
    LoginBean loginBean;

    @Wire
    Textbox usernameTxt, passwordTxt;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        loginBean = EBeanUtils.getBean(LoginBean.class);
    }


    @AfterCompose(superclass = true)
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }


    @Command
    public void login() {
        validate();
    }

    public boolean isNull(String value) {
        boolean result = true;
        if (value != null && value.trim().length() > 0
                && !value.trim().equalsIgnoreCase("null")) {
            result = false;
        }
        return result;
    }

    private boolean validate() {
        if (isNull(usernameTxt.getValue())) {
            Messagebox.show("Хэрэглэгчийн нэрээ оруулна уу !!!");
            usernameTxt.setFocus(true);
        } else if (isNull(passwordTxt.getValue())) {
            Messagebox.show("Хэрэглэгчийн нууц үгээ оруулна уу !!!");
            passwordTxt.setFocus(true);
        } else {
            try {
                Users users = loginBean.checkLogin(usernameTxt.getValue(), passwordTxt.getValue());
                if (users != null) {
                    Executions.getCurrent().getSession().setAttribute("currentUser", users);
                    Executions.sendRedirect("index.zul");
                } else NotificationUtils.showMsgWarning("Нэвтрэх нэр болон нууц үг буруу байна.");
            } catch (Exception e) {
                e.printStackTrace();
                Messagebox.show("Хэрэглэгчийн нэр нууц үг буруу байна !!! ");
                usernameTxt.setValue("");
                passwordTxt.setValue("");
                usernameTxt.setFocus(true);
            }
        }
        return false;
    }

}
