package com.model.entity;


/**
 * Ажилтаны сарын цалин бодож дэлгэрэнгүйгээр харуулах POJO
 * Ерөнхийдөө энд байгаа Entity утгуудийг дан String/Double/Int
 * төрлөөр хадгалан харин буцааж авахдаа Entity хэлбэрээр авбал сайн байна
 * <p>
 * Created by Arti on 7/22/2015.
 */
public class EmployeeTimesheet {
    /**
     * Тухайн Ажилтан
     * Эннээс Регистрийн дугаарыг авч хадгалан
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
     * <p>
     * Энэ Доорхи 3 нь утга болох @probationAmount / @sitAmount / @vatAmount
     * <p>
     * нийлж Transient deduction string үүсгэн
     * Жич
     * ХАОАТ: 1,000,НДТ: 1,000
     * <p>
     * гэх мэт
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
    /**
     * Энэ цаг бүртгэлийн мэдээлэл али ажлын сард хамрагдах вэ гэх мэдээлэл
     * чи эндээс жил сарын салгаж авж хадгалан шүү
     */
    private WorkMonths workMonths;


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
