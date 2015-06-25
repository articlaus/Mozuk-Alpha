package com.ui.controller;

import com.model.entity.Users;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import java.util.HashMap;

/**
 * Created by Articlaus on 6/19/15.
 */
public class LoginController extends MainComponent {

    Users user;

    @Wire
    Textbox usernameTxt, passwordTxt;


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
//                Users entity = loginBean.checkLogin(usernameTxt.getValue(), passwordTxt.getValue());
//                Executions.getCurrent().getSession().setAttribute("user", entity);

                Executions.sendRedirect("index.zul");
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
