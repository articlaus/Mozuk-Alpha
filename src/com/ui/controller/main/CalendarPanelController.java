package com.ui.controller.main;

import com.model.bean.OtherBean;
import com.model.other.CalendarPojo;
import com.ui.component.base.EBeanUtils;
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
import java.util.List;

/**
 * Created by Articlaus on 6/23/15.
 */
public class CalendarPanelController extends MainComponent {

    String initCalendar;
    String javascript;
    OtherBean otherBean;
    List<CalendarPojo> calendarPojos;


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
        loadValuesFromDatabase();
        initCalendar();
        loadCalendarValues();
        Clients.evalJavaScript(initCalendar + javascript);
    }


    public void initCalendar() {
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        initCalendar = "$('#calendar').fullCalendar({lang:'en-gb',height: 700,header: {left: 'prev,next today', center: 'title',right: 'month,agendaWeek,agendaDay'}," +
                "defaultDate: '" + format.format(currentDate) + "',editable: false,droppable: false,drop: function() {if (removeDraggable.is(':checked')) { $(this).remove();}},";
    }

    private void loadValuesFromDatabase() {
        calendarPojos = otherBean.getCalendarPojos();
    }


    public void loadCalendarValues() {
        javascript = "events: [";
        String title = "";
        String startDate = "";
        String endDate = "";

        for (CalendarPojo calendarPojo : calendarPojos) {
            title = "{title:'" + calendarPojo.getTitle() + "',";
            startDate = "start:'" + calendarPojo.getStartDate() + "',";
            endDate = "end:'" + calendarPojo.getEndDate() + "'}";
            javascript = javascript + title + startDate + endDate + ",";
        }


        javascript = javascript + "]});";
    }

}
