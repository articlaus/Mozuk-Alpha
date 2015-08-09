package com.model.bean;

import com.model.entity.*;
import com.model.util.BaseEJB;
import com.model.util.DataTypeUtils;
import com.model.util.SequenceUtil;
import org.joda.time.DateTime;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tseegii on 7/31/15.
 */
@LocalBean
@Stateless
public class CalculationBean extends BaseEJB {


    @Inject
    private EmployeeBean employeeBean;

    @Inject
    private OvertimeBean overtimeBean;

    @Inject
    private LeaveAbsenceBean leaveAbsenceBean;

    @Inject
    private ProbationBean probationBean;

    private void deleteTimesheet(WorkMonths workMonths) {
        getEm().createNamedQuery("EmployeeTimesheet.deleteByWorkMonth")
                .setParameter("workMonth", workMonths.getId())
                .executeUpdate();
    }

    public List<EmployeeTimesheet> findByWorkMonth(WorkMonths workMonths) {
        return getEm().createNamedQuery("EmployeeTimesheet.findByWorkMonth", EmployeeTimesheet.class)
                .setParameter("workMonth", workMonths.getId())
                .getResultList();
    }

    public List<EmployeeTimesheet> findByRegister(String register) {
        return getEm().createNamedQuery("EmployeeTimesheet.findByEmployeeRegister", EmployeeTimesheet.class)
                .setParameter("employeeRegister", register)
                .getResultList();
    }

    public List<EmployeeTimesheet> findByRegisterAndWorkMonths(String register, WorkMonths workMonths) {
        return getEm().createNamedQuery("EmployeeTimesheet.findByEmployeeRegisterAndWorkMonth", EmployeeTimesheet.class)
                .setParameter("employeeRegister", register)
                .setParameter("workMonth", workMonths.getId())
                .getResultList();
    }

    public List<EmployeeTimesheet> calculateTimeSheet(WorkMonths workMonth) {

        List<EmployeeTimesheet> employeeTimesheets = build(workMonth);

        for (EmployeeTimesheet employeeTimesheet : employeeTimesheets) {
            if (employeeTimesheet.getPosition() != null) {
                double timeSalary = employeeTimesheet.getMainSalary() / workMonth.getTotalWorkHours();
                double salaryAddition = employeeBean.findAdditionalSalaryByEmployeeCode(employeeTimesheet.getEmployee());
                int leaveHours = leaveAbsenceBean.findHoursByEmployeeAndWorkMonthAndIsPaid(employeeTimesheet.getEmployee(), workMonth, false);
                double monthSalary = calculateSalaryOfTime(employeeTimesheet.getMainSalary(), workMonth.getTotalWorkHours(), employeeTimesheet.getEmployeeWorkMonth().getWorkedHours(), leaveHours, salaryAddition);
                System.out.println("salaryOfTIME monthSalary = " + monthSalary);
                double probationAmount = calculateProbation(monthSalary, employeeTimesheet.getEmployee(), workMonth);
                monthSalary -= probationAmount;
                System.out.println("probation monthSalary = " + monthSalary);
                double overtime = calculateOvertime(employeeTimesheet.getEmployee(), workMonth);
                monthSalary += timeSalary * overtime;
                System.out.println("overtime monthSalary = " + monthSalary);
                employeeTimesheet.setTotalLeaveHours(leaveHours);
                employeeTimesheet.setTotalOvertimeHours(overtime);
                employeeTimesheet.setProbationAmount(probationAmount);
                employeeTimesheet.setBonusAdditions(DataTypeUtils.doubleToString(salaryAddition));
                employeeTimesheet = calculateTax(monthSalary, employeeTimesheet);
            }
        }
        deleteTimesheet(workMonth);
        for (EmployeeTimesheet employeeTimesheet : employeeTimesheets) {
            getEm().persist(employeeTimesheet);
        }
        return employeeTimesheets;
    }

    private List<EmployeeTimesheet> build(WorkMonths workMonth) {
        List<EmployeeWorkMonth> employeeWorkMonths = employeeBean.findWorkMonthByWorkMonth(workMonth);
        List<EmployeeTimesheet> employeeTimesheets = new ArrayList<>(employeeWorkMonths.size());
        for (EmployeeWorkMonth employeeWorkMonth : employeeWorkMonths) {
            EmployeeTimesheet employeeTimesheet = new EmployeeTimesheet();
            employeeTimesheet.setId(SequenceUtil.nextBigDecimal());
            Employee employee = employeeWorkMonth.getEmployeeCode();
            employeeTimesheet.setEmployeeWorkMonth(employeeWorkMonth);
            employeeTimesheet.setEmployee(employee);
            employeeTimesheet.setEmployeeRegister(employee.getSocialSecurityNumber());
            employeeTimesheet.setWorkHours(workMonth.getTotalWorkHours());
            employeeTimesheet.setWorkedHours(employeeWorkMonth.getWorkedHours());
            employeeTimesheet.setWorkMonths(employeeWorkMonth.getWorkMonthsid());
            employeeTimesheet.setWorkMonth(employeeWorkMonth.getWorkMonthsid().getId());
            EmployeePosition employeePosition = null;
            try {
                employeePosition = employeeBean.findByEmployeeCodeAndIsActive(employee, true);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if (employeePosition != null) {
                employeeTimesheet.setPosition(employeePosition.getPositionCode());
                employeeTimesheet.setEmployeePosition(employeePosition.getPositionCode().getPositionTitle());
                employeeTimesheet.setMainSalary(employeePosition.getSalary());
                employeeTimesheets.add(employeeTimesheet);
            }


        }
        return employeeTimesheets;
    }

    private EmployeeTimesheet calculateTax(double incomeValue, EmployeeTimesheet employeeTimesheet) {
        System.out.println("incomeValue = " + incomeValue);
        double taxDiscount = 7000;
        double taxRate = ((.1 * incomeValue) < 100000000) ? (.1 * incomeValue) : (100000000);
        double healthInsuranceTaxValue = ((incomeValue - taxRate) > 0) ? (incomeValue - taxRate) : (0);
        double taxValue = ((.1 * healthInsuranceTaxValue) > 0) ? (.1 * healthInsuranceTaxValue) : (0);
        double taxValue2 = ((taxValue - taxDiscount) > 0) ? (taxValue - taxDiscount) : (0);
        double finalSalary = ((healthInsuranceTaxValue - taxValue2) > 0) ? (healthInsuranceTaxValue - taxValue2) : (0);
        employeeTimesheet.setSitAmount(taxRate);
        employeeTimesheet.setVatAmount(taxValue);
        employeeTimesheet.setFinalSalary(finalSalary);
        System.out.println("taxLine3 = " + ((taxRate > 0) ? (taxRate) : (0)));
        System.out.println("healthInsuranceTaxValue = " + healthInsuranceTaxValue);
        System.out.println("taxValue = " + taxValue);
        System.out.println("taxValue2 = " + taxValue2);
        System.out.println("finalSalary = " + finalSalary);
        return employeeTimesheet;
    }

    private double calculateSalaryOfTime(double salary, int mustWorkTime, int workedHours, int leaveHours, double additionalSalary) {
        double monthSalary = (salary / mustWorkTime) * (workedHours - leaveHours);
        monthSalary += additionalSalary;
        return monthSalary;
    }

    private double calculateProbation(double monthSalary, Employee employee, WorkMonths workMonth) {
        List<Probation> probations = probationBean.findByEmployeeCodeAndIsActive(employee, true);

        if (probations.size() == 0) {
            return 0d;
        } else {
            int deductionPercent = 0;
            for (Probation probation : probations) {
                DateTime endDate = new DateTime(probation.getEndDate());
                int endMonth = endDate.getMonthOfYear();
                if (endMonth >= workMonth.getMonth()) {
                    deductionPercent += probation.getDeductionPercent();
                }
            }
            return monthSalary / 100 * deductionPercent;
        }
    }

    private double calculateOvertime(Employee employee, WorkMonths workMonth) {
        double overtime = overtimeBean.findHoursByEmployeeAndWorkMonth(employee, workMonth);
        return overtime;
    }

    //    public void call() {
//        WorkMonths workMonths = getEm().find(WorkMonths.class, BigDecimal.valueOf(2));
////        List<EmployeeTimesheet> employeeTimesheets=calculateTimeSheet(workMonths);
//        List<EmployeeTimesheet> employeeTimesheets=findByWorkMonth(workMonths);
//
//        System.out.println("employeeTimesheets.size() = " + employeeTimesheets.size());
//        for (EmployeeTimesheet employeeTimesheet : employeeTimesheets) {
//            System.out.println("employeeTimesheet = " + employeeTimesheet);
//        }
//
//    }

}
