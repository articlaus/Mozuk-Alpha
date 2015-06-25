package com.ui.controller.main;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Articlaus on 6/23/15.
 */
public class CalendarPanelController extends MainComponent {

    String initCalendar;
    String javascript;


    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
        initCalendar();
        loadCalendarValues();
        Clients.evalJavaScript(initCalendar + javascript);
    }


    public void initCalendar() {
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        initCalendar = "$('#calendar').fullCalendar({height: 700,header: {left: 'prev,next today', center: 'title',right: 'month,agendaWeek,agendaDay'}," +
                "defaultDate: '" + format.format(currentDate) + "',editable: false,droppable: false,drop: function() {if (removeDraggable.is(':checked')) { $(this).remove();}},";
    }


    public void loadCalendarValues() {
        javascript = "events: [";

        String title = "{title:'Танилцуулга Бэлдэх',";
        String startDate = "start:'2015-06-01',";
        String endDate = "end:'2015-07-01'}";

        javascript = javascript + title + startDate + endDate + "]});";
    }

}
