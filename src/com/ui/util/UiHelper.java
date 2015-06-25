package com.ui.util;

import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;

/**
 * Created by tseegii on 12/2/14.
 */
public class UiHelper {

    public static Div drawLabelContainer(String label, String value) {
        Div container = new Div();
        Label label1 = new Label(label);
        Label value1 = new Label(value);
        value1.setStyle("font-weight: bold;");
        container.appendChild(label1);
        container.appendChild(value1);
        return container;
    }

    public static Div drawLayoutContainer(String title) {
        Div col = new Div();
        col.setSclass("col-md-3 col-sm-10");
        col.setStyle("margin-left:10px;margin-bottom:10px");

        Div widget = new Div();
        widget.setSclass("pricing-widget");
        Div head = new Div();
        head.appendChild(new Html(title));
        head.setSclass("pricing-head");
        widget.appendChild(head);
        Div body = new Div();
        body.setSclass("pricing-body clearfix");
        Div list = new Div();
        list.setSclass("pricing-list text-left");
        list.setStyle("padding-left:20px");
        col.setAttribute("widget", widget);
        col.setAttribute("list", list);
        return col;
    }


    public static Html getBandboxStyles(String name, String code) {
        return new Html("<strong>" + name + "</strong>" + " <font size='0.5' color='gray'>" + code + "</font>");
    }
}
