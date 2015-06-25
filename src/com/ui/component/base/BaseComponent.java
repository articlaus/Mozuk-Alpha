package com.ui.component.base;

import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;

/**
 * Created with IntelliJ IDEA.
 * User: tseegii
 * Date: 6/17/13
 * Time: 7:43 PM
 */
public interface BaseComponent extends AfterCompose {
    /**
     * ZUL file-дээр хэрэв DataBinder ашиглаж байгаа бол
     * үүнийг ашиглаж мэдээллийг zul файл-руу илгээнэ.
     */
    public AnnotateDataBinder getBinder();

    /*public void validate();

    public void check(Component component);

    public void checkIsValid(Component component);*/
}
