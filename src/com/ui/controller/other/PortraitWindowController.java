package com.ui.controller.other;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Image;

import java.io.IOException;

/**
 * Created by Arti on 6/28/2015.
 */
public class PortraitWindowController extends MainComponent {


    @Wire
    Image portraitImage;

    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        try {
            loadImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImage() throws IOException {
        AImage aImage = new AImage("", (byte[]) getArgument("image"));

        portraitImage.setContent(aImage);
        getBinder().loadComponent(portraitImage, true);
    }
}
