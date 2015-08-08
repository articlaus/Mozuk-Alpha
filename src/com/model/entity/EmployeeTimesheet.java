package com.model.entity;


import com.model.util.DataTypeUtils;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Ажилтаны сарын цалин бодож дэлгэрэнгүйгээр харуулах POJO
 * Ерөнхийдөө энд байгаа Entity утгуудийг дан String/Double/Int
 * төрлөөр хадгалан харин буцааж авахдаа Entity хэлбэрээр авбал сайн байна
 * <p/>
 * Created by Arti on 7/22/2015.
 */
@Entity
@Table(name = "EMPLOYEE_TIMESHEET", schema = "", catalog = "avocado")
@NamedQueries({
        @NamedQuery(name = "EmployeeTimesheet.findAll", query = "SELECT et FROM EmployeeTimesheet AS et"),
        @NamedQuery(name = "EmployeeTimesheet.findByWorkMonth", query = "SELECT et FROM EmployeeTimesheet AS et WHERE et.workMonth=:workMonth"),
        @NamedQuery(name = "EmployeeTimesheet.findByEmployeeRegister", query = "SELECT et FROM EmployeeTimesheet AS et WHERE et.employeeRegister=:employeeRegister"),
        @NamedQuery(name = "EmployeeTimesheet.findByEmployeeRegisterAndWorkMonth", query = "SELECT et FROM EmployeeTimesheet AS et WHERE et.employeeRegister=:employeeRegister AND et.workMonth=:workMonth"),
        @NamedQuery(name = "EmployeeTimesheet.deleteByWorkMonth", query = "DELETE FROM EmployeeTimesheet AS et WHERE et.workMonth=:workMonth"),
})
public class EmployeeTimesheet {
    /**
     * Тухайн Ажилтан
     * Эннээс Регистрийн дугаарыг авч хадгалан
     * Ажилтанг олж өгнө
     * Регистр ээр хайна
     */
    @Transient
    Employee employee;

    /**
     * Түүний Албан Тушаал
     * гаргаж өгнө
     */
    @Transient
    Position position;
    /**
     * Үндсэн цалин
     */
    @Basic
    @Column(name = "main_salary")
    double mainSalary;

    /**
     * Ажиллах ёстой цаг
     */
    @Basic
    @Column(name = "work_hours")
    int workHours;
    /**
     * Ажилласан цаг
     * double байгаан учир нь тал цаг ажиллаж болож учир
     */

    @Basic
    @Column(name = "worked_hours")
    double workedHours;
    /**
     * Нийт Амралттай байсан өдөр
     */
    @Basic
    @Column(name = "total_leave_hours")
    double totalLeaveHours;
    /**
     * Нийт илүү ажилласан цаг
     */

    /**
     * Хэрэв шийтгэлтэй байгаа бол шийтгэлээр хасагдаж буй цалин
     * <p/>
     * Энэ Доорхи 3 нь утга болох @probationAmount / @sitAmount / @vatAmount
     * <p/>
     * нийлж Transient deduction string үүсгэн
     * Жич
     * ШИ: 1000,ХАОАТ: 1,000, НДТ: 1,000
     * <p/>
     * гэх мэт
     */
    @Transient
    private String deducation;


    @Basic
    @Column(name = "probation_amount")
    double probationAmount;
    /**
     * Нийгмийн Даатгалын Шимтгэлийн хэмжээ
     */
    @Basic
    @Column(name = "sit_amount")
    double sitAmount;
    /**
     * Хүн амийн орлогийн албан татвар
     */
    @Basic
    @Column(name = "vat_amount")
    double vatAmount;
    /**
     * Эцсийн цалин
     * database-d employee_work_month table final_salary баган
     */
    @Basic
    @Column(name = "final_salary")
    double finalSalary;
    /**
     * Энэ цаг бүртгэлийн мэдээлэл али ажлын сард хамрагдах вэ гэх мэдээлэл
     * чи эндээс жил сарын салгаж авж хадгалан шүү
     */
    @Transient
    private WorkMonths workMonths;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic
    @Column(name = "employee_register")
    private String employeeRegister;
    @Basic
    @Column(name = "employee_position")
    private String employeePosition;
    @Basic
    @Column(name = "total_overtime_hours")
    private Double totalOvertimeHours;
    @Basic
    @Column(name = "work_month")
    private BigDecimal workMonth;

    @Basic
    @Column(name = "bonus_additions")
    private String bonusAdditions;

    public String getDeducation() {
        String deducation = "";
        if (probationAmount > 0d) {
            deducation += "Шийтгэл : " + DataTypeUtils.doubleToString(probationAmount);
        }
        deducation += "ХАОАТ: " + DataTypeUtils.doubleToString(sitAmount) + " НДТ : " + DataTypeUtils.doubleToString(vatAmount);
        return deducation;
    }

    public void setDeducation(String deducation) {
        this.deducation = deducation;
    }


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


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


    public String getEmployeeRegister() {
        return employeeRegister;
    }

    public void setEmployeeRegister(String employeeRegister) {
        this.employeeRegister = employeeRegister;
    }


    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }


    public Double getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(Double totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }


    public BigDecimal getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(BigDecimal workMonth) {
        this.workMonth = workMonth;
    }

    public String getBonusAdditions() {
        return bonusAdditions;
    }

    public void setBonusAdditions(String bonusAdditions) {
        this.bonusAdditions = bonusAdditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeTimesheet that = (EmployeeTimesheet) o;

        if (Double.compare(that.mainSalary, mainSalary) != 0) return false;
        if (workHours != that.workHours) return false;
        if (Double.compare(that.workedHours, workedHours) != 0) return false;
        if (Double.compare(that.totalLeaveHours, totalLeaveHours) != 0) return false;
        if (Double.compare(that.probationAmount, probationAmount) != 0) return false;
        if (Double.compare(that.sitAmount, sitAmount) != 0) return false;
        if (Double.compare(that.vatAmount, vatAmount) != 0) return false;
        if (Double.compare(that.finalSalary, finalSalary) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (employeeRegister != null ? !employeeRegister.equals(that.employeeRegister) : that.employeeRegister != null)
            return false;
        if (employeePosition != null ? !employeePosition.equals(that.employeePosition) : that.employeePosition != null)
            return false;
        if (totalOvertimeHours != null ? !totalOvertimeHours.equals(that.totalOvertimeHours) : that.totalOvertimeHours != null)
            return false;
        if (workMonth != null ? !workMonth.equals(that.workMonth) : that.workMonth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (employeeRegister != null ? employeeRegister.hashCode() : 0);
        result = 31 * result + (employeePosition != null ? employeePosition.hashCode() : 0);
        temp = Double.doubleToLongBits(mainSalary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + workHours;
        temp = Double.doubleToLongBits(workedHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalLeaveHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (totalOvertimeHours != null ? totalOvertimeHours.hashCode() : 0);
        temp = Double.doubleToLongBits(probationAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sitAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(vatAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(finalSalary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (workMonth != null ? workMonth.hashCode() : 0);
        return result;
    }


    @Transient
    private EmployeeWorkMonth employeeWorkMonth;

    public EmployeeWorkMonth getEmployeeWorkMonth() {
        return employeeWorkMonth;
    }

    public void setEmployeeWorkMonth(EmployeeWorkMonth employeeWorkMonth) {
        this.employeeWorkMonth = employeeWorkMonth;
    }

    public WorkMonths getWorkMonths() {
        return workMonths;
    }

    public void setWorkMonths(WorkMonths workMonths) {
        this.workMonths = workMonths;
    }


    @Override
    public String toString() {
        return "EmployeeTimesheet{" +
                ", id=" + id +
                ", mainSalary=" + mainSalary +
                ", workHours=" + workHours +
                ", workedHours=" + workedHours +
                ", totalLeaveHours=" + totalLeaveHours +
                ", deducation='" + deducation + '\'' +
                ", probationAmount=" + probationAmount +
                ", sitAmount=" + sitAmount +
                ", vatAmount=" + vatAmount +
                ", finalSalary=" + finalSalary +
                ", employeeRegister='" + employeeRegister + '\'' +
                ", employeePosition='" + employeePosition + '\'' +
                ", totalOvertimeHours=" + totalOvertimeHours +
                ", workMonth=" + workMonth +
                ", bonusAdditions='" + bonusAdditions + '\'' +
                '}';
    }
}
