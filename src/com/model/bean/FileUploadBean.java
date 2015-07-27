package com.model.bean;

import com.model.entity.Employee;
import com.model.entity.EmployeePosition;
import com.model.entity.EmployeeWorkMonth;
import com.model.entity.WorkMonths;
import com.model.exception.CommonFault;
import com.model.util.BaseEJB;
import com.model.util.ExcelUtil;
import org.apache.poi.ss.usermodel.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.*;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huslee on 7/16/2015.
 */
@LocalBean
@Stateless
public class FileUploadBean extends BaseEJB {

    @Inject
    private EmployeeBean employeeBean;

    @Inject
    private OtherBean otherBean;

    private static int NUMBERING = 1;
    private static int EMPLOYEE_CODE = 2;
    private static int EMPLOYEE_REGISTER = 3;
    private static int EMPLOYEE_NAME = 4;
    private static int EMPLOYEE_POSITION = 5;
    private static int WORK_HOURS = 6;

    private InputStream is = null;

    private void getFileInputStream() {
        try {
//            is = new FileInputStream(new File("C:\\Users\\huslee\\IdeaProjects\\Mozuk-Alpha\\src\\META-INF\\employee_excel_template.xlsx"));
            is = new FileInputStream(new File(filePath + "employee_excel_template.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readAndWriteXls(String extension) {
        getFileInputStream();
        try {
            Workbook workbook = ExcelUtil.getExcelWorkbook(is, extension);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> o = sheet.rowIterator();
            int rowNumNumber = 0;
//            CellStyle cellStyle = null;
            while (o.hasNext()) {
                Row row = o.next();
                rowNumNumber = row.getRowNum();
//                Cell numbering = row.getCell(NUMBERING);
//                Cell employee_code = row.getCell(EMPLOYEE_CODE);
//                Cell employee_register = row.getCell(EMPLOYEE_REGISTER);
//                Cell employee_name = row.getCell(EMPLOYEE_NAME);
//                Cell employee_position = row.getCell(EMPLOYEE_POSITION);
//                Cell work_hours = row.getCell(WORK_HOURS);
//                cellStyle = numbering.getCellStyle();

//                System.out.println("numbering = " + numbering);
//                System.out.println("employee_code = " + employee_code);
//                System.out.println("employee_register = " + employee_register);
//                System.out.println("employee_name = " + employee_name);
//                System.out.println("employee_position = " + employee_position);
//                System.out.println("work_hours = " + work_hours);
            }
//            System.out.println("rowNumNumber = " + rowNumNumber);
//            XSSFCellStyle style=workbook.createCellStyle();
//            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);


//            WorkMonths workMonths = otherBean.findByYearAndMonth();
//            if (workMonths == null) {
//                workMonths = otherBean.findByPreviosYearAndMonth();
//            }

//            List<EmployeeWorkMonth> employeeWorkMonthList = employeeBean.findWorkMonthByWorkMonth(workMonths);

            List<EmployeeWorkMonth> employeeWorkMonthList = employeeBean.findEmployeeWorkMonthByIsActive(true);
            for (int i = 0; i < employeeWorkMonthList.size(); i++) {
                Employee employee = employeeWorkMonthList.get(i).getEmployeeCode();
                Row row = sheet.createRow(rowNumNumber + i + 1);
                Cell numbering = row.createCell(NUMBERING);
                numbering.setCellValue(i + 1);
                numbering.setCellType(0);
                Cell employee_code = row.createCell(EMPLOYEE_CODE);
                employee_code.setCellValue(employee.getCode());
                Cell employee_register = row.createCell(EMPLOYEE_REGISTER);
                employee_register.setCellValue(employee.getSocialSecurityNumber());
                Cell employee_name = row.createCell(EMPLOYEE_NAME);
                employee_name.setCellValue(employee.getFullName());
                Cell employee_position = row.createCell(EMPLOYEE_POSITION);
                EmployeePosition employeePosition = employeeBean.findByEmployeeCodeAndIsActive(employee, true);
                employee_position.setCellValue(employeePosition.getPositionCode().getPositionTitle());
                Cell work_hours = row.createCell(WORK_HOURS);
                work_hours.setCellType(0);
                work_hours.setCellValue(employeeWorkMonthList.get(i).getWorkedHours());
            }
            is.close();

            FileOutputStream output_file = new FileOutputStream(new File(filePath + "employee_list.xlsx"));  //Open FileOutputStream to write updates
            workbook.write(output_file); //write changes
            output_file.close();
        } catch (IOException | CommonFault e) {
            e.printStackTrace();
        }
    }

    private void readXls(String extension, WorkMonths workMonths) {
        try {
            Workbook workbook = ExcelUtil.getExcelWorkbook(is, extension);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> o = sheet.rowIterator();
            List<Row> rowList = new ArrayList<>();
            while (o.hasNext()) {
                Row row = o.next();

                Cell numbering = row.getCell(NUMBERING);
                Cell employee_code = row.getCell(EMPLOYEE_CODE);
                Cell employee_register = row.getCell(EMPLOYEE_REGISTER);
                Cell employee_name = row.getCell(EMPLOYEE_NAME);
                Cell employee_position = row.getCell(EMPLOYEE_POSITION);
                Cell work_hours = row.getCell(WORK_HOURS);

                if (numbering.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    rowList.add(row);
                }
                System.out.println("rowList = " + rowList.size());
//                System.out.println("cell.getStringCellValue() = " + numbering.getStringCellValue());
//                System.out.println("cell.getStringCellValue() = " + secondCell.getStringCellValue());
//                System.out.println("cell.getStringCellValue() = " + thirdCell.getStringCellValue());
            }
            saveEmployeeWorkMonth(rowList, workMonths);

        } catch (CommonFault commonFault) {
            commonFault.printStackTrace();
        }
    }

    private void saveEmployeeWorkMonth(List<Row> rowList, WorkMonths workMonths) {
        List<EmployeeWorkMonth> employeeWorkMonths = employeeBean.findWorkMonthByWorkMonth(workMonths);
        for (Row row : rowList) {
//            Cell numbering = row.getCell(NUMBERING);
            Cell employee_code = row.getCell(EMPLOYEE_CODE);
            Cell employee_register = row.getCell(EMPLOYEE_REGISTER);
            Cell employee_name = row.getCell(EMPLOYEE_NAME);
//            Cell employee_position = row.getCell(EMPLOYEE_POSITION);
            Cell work_hours = row.getCell(WORK_HOURS);
            System.out.println("employee_register = " + employee_register.getStringCellValue());
            System.out.println("employee_name = " + employee_name.getStringCellValue());
            System.out.println("work_hours.getNumericCellValue() = " + work_hours.getNumericCellValue());
            for (EmployeeWorkMonth employeeWorkMonth : employeeWorkMonths) {
                Employee employee = employeeWorkMonth.getEmployeeCode();
                if (employee.getCode().equals(employee_code.getStringCellValue())
                        && employee.getSocialSecurityNumber().equals(employee_register.getStringCellValue())) {
                    employeeWorkMonth.setWorkedHours((int) work_hours.getNumericCellValue());
                    getEm().merge(employeeWorkMonth);
                    System.out.println("employee = " + employee.getFullName());
                    System.out.println("employeeWorkMonth.getWorkedHours() = " + employeeWorkMonth.getWorkedHours());
                }
            }
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public File downloadXlsTemplate() {
        readAndWriteXls("xlsx");
        return new File(filePath + "employee_list.xlsx");
    }

    public boolean uploadXlsx(InputStream is, String extension, WorkMonths workMonths) {
        this.is = is;
        readXls(extension, workMonths);
        return true;
    }

    public void deleteXlsTemplate() {
        File file = new File(getFilePath() + "employee_list.xlsx");
        file.delete();
    }
}
