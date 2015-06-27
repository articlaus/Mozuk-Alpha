package com.model.other;

/**
 * Created by Arti on 6/28/2015.
 */
public class CalendarPojo {
    /**
     * Ажилтаны Нэр болон Object-ын төрөл - амралт илуу цаг чөлөө гэх мэт
     */
    private String title;
    /**
     * Эхлэсэн Өдөр формат yyyy-MM-dd
     */
    private String startDate;
    /**
     * Дуссах өдөр формат yyyy-MM-dd
     */
    private String endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
