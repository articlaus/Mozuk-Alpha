package com.ui.controller.main.probation;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;

/**
 * Created by ganbat.b on 7/27/2015.
 */
public class ProbationWindowController extends MainComponent {
    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
    }
}
