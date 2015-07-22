package com.model.other;

import com.model.entity.Employee;
import com.model.entity.Position;

/**
 * Ажилтаны сарын цалин бодож дэлгэрэнгүйгээр харуулах POJO
 * Created by Arti on 7/22/2015.
 */
public class EmployeeTimesheetPojo {
    /**
     * Тухайн Ажилтан
     */
    Employee employee;

    /**
     * Түүний Албан Тушаал
     */
    Position position;
    /**
     * Үндсэн цалин
     */
    double mainSalary;

    /**
     * Ажиллах ёстой цаг
     */
    int workHours;
    /**
     * Ажилласан цаг
     * double байгаан учир нь тал цаг ажиллаж болож учир
     */
    double workedHours;
    /**
     * Нийт Амралттай байсан өдөр
     */
    double totalLeaveHours;
    /**
     * Нийт илүү ажилласан цаг
     */
    double totalOvertime;
    /**
     * Хэрэв шийтгэлтэй байгаа бол шийтгэлээр хасагдаж буй цалин
     */
    double probationAmount;
    /**
     * Нийгмийн Даатгалын Шимтгэлийн хэмжээ
     */
    double sitAmount;
    /**
     * Хүн амийн орлогийн албан татвар
     */
    double vatAmount;
    /**
     * Эцсийн цалин
     * database-d employee_work_month table final_salary баган
     */
    double finalSalary;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getMainSalary() {
        return mainSalary;
    }

    public void setMainSalary(double mainSalary) {
        this.mainSalary = mainSalary;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }

    public double getTotalLeaveHours() {
        return totalLeaveHours;
    }

    public void setTotalLeaveHours(double totalLeaveHours) {
        this.totalLeaveHours = totalLeaveHours;
    }

    public double getTotalOvertime() {
        return totalOvertime;
    }

    public void setTotalOvertime(double totalOvertime) {
        this.totalOvertime = totalOvertime;
    }

    public double getProbationAmount() {
        return probationAmount;
    }

    public void setProbationAmount(double probationAmount) {
        this.probationAmount = probationAmount;
    }

    public double getSitAmount() {
        return sitAmount;
    }

    public void setSitAmount(double sitAmount) {
        this.sitAmount = sitAmount;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }
}
