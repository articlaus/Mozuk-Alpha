package com.ui.controller.other;

import com.model.bean.OtherBean;
import com.model.entity.Position;
import com.ui.component.base.EBeanUtils;
import com.ui.component.base.MainComponent;
import com.ui.util.NotificationUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;

/**
 * Created by Arti on 6/26/2015.
 */
public class PositionWindowController extends MainComponent {
    Position position;
    OtherBean otherBean;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
        otherBean = EBeanUtils.getBean(OtherBean.class);
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        position = new Position();
    }

    @Command
    public void save() {
        if (otherBean.saveByPosition(position) != null) {
            NotificationUtils.showSuccess();
        } else
            NotificationUtils.showFailure();

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
