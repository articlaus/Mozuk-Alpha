package com.model.util;

import com.model.exception.CommonFault;
import com.model.other.CodeMessage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by huslee on 7/6/2015.
 */
public class ExcelUtil {
    public static Workbook getExcelWorkbook(InputStream is, String format) throws CommonFault {
        Workbook w;
        if (format.endsWith("xls")) {
            w = getXlsWorkbook(is);
        } else if (format.endsWith("xlsx")) {
            w = getXlsxWorkbook(is);
        } else {
            throw new CommonFault(new CodeMessage("Microsoft excel-ийн файл биш байна."));
        }
        return w;
    }

    private static XSSFWorkbook getXlsxWorkbook(InputStream is) throws CommonFault {
        try {
            return new XSSFWorkbook(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommonFault(new CodeMessage("EX1001", "Excel файл олдсонгүй"));
        } catch (IOException ex) {
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommonFault(new CodeMessage("EX1002", "Excel файл уншиж чадсангүй"));
        }
    }

    private static HSSFWorkbook getXlsWorkbook(InputStream is) throws CommonFault {
        try {
            return new HSSFWorkbook(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommonFault(new CodeMessage("EX1001", "Excel файл олдсонгүй"));
        } catch (IOException ex) {
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommonFault(new CodeMessage("EX1002", "Excel файл уншиж чадсангүй"));
        }
    }
}
