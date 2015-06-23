package com.ui.controller;

import com.model.bean.TestBean;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

/**
 * Created by Articlaus on 6/19/15.
 */
public class IndexController extends MainComponent {

    TestBean testBean;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();

        testBean = EBeanUtils.getBean(TestBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {

        testBean.findAll();

    }
}
