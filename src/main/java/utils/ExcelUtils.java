package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author Gladson Antony
 * @Date 21-Feb-2017
 */
public class ExcelUtils
{
    private static XSSFSheet xlsxWorkSheet;
    private static XSSFWorkbook xlsxWorkBook;

    private static HSSFSheet xlsWorkSheet;
    private static HSSFWorkbook xlsWorkBook;

    /**
     * To get the Excel-XLSX File with Path and SheetName
     */
    public static void getExcelFile(String Path, String SheetName) throws Exception {
        try {
            File file = new File(Path);
            if (file.getAbsolutePath().endsWith(".xlsx"))
            {
                FileInputStream fis = new FileInputStream(file);
                xlsxWorkBook = new XSSFWorkbook(fis);
                xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);
            }
            else if (file.getAbsolutePath().endsWith(".xls"))
            {
                FileInputStream fis = new FileInputStream(file);
                xlsWorkBook = new HSSFWorkbook(fis);
                xlsWorkSheet = xlsWorkBook.getSheet(SheetName);
            }
        } catch (Exception e) {
            throw (e);
        }
    }


    /**
     * To Return the Excel-XLSX Values given Path to the File and Sheet Name
     */
    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        Object[][] tabArray = null;
        try {
            File file = new File(FilePath);
            if (file.getAbsolutePath().endsWith(".xlsx")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsxWorkBook = new XSSFWorkbook(ExcelFile);
                xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsxRowCount();
                int totalCols = ExcelUtils.xlsxColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLSX(i, j);
                        cj++;
                    }
                    ci++;
                }
            } else if (file.getAbsolutePath().endsWith(".xls")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsWorkBook = new HSSFWorkbook(ExcelFile);
                xlsWorkSheet = xlsWorkBook.getSheet(SheetName);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsRowCount();
                int totalCols = ExcelUtils.xlsColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLS(i, j);
                        cj++;
                    }
                    ci++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Could not Find the Excel File/Sheet");
        } catch (Exception e) {
            throw new Exception("Could not Open the Excel File");
        }
        return (tabArray);
    }


    /**
     * To Return the Excel-XLSX Values given Path to the File
     */
    public static Object[][] getTableArray(String FilePath) throws Exception {
        Object[][] tabArray = null;
        try {
            File file = new File(FilePath);
            if (file.getAbsolutePath().endsWith(".xlsx")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsxWorkBook = new XSSFWorkbook(ExcelFile);
                xlsxWorkSheet = xlsxWorkBook.getSheetAt(0);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsxRowCount();
                int totalCols = ExcelUtils.xlsxColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLSX(i, j);
                        cj++;
                    }
                    ci++;
                }
            } else if (file.getAbsolutePath().endsWith(".xls")) {
                FileInputStream ExcelFile = new FileInputStream(file);
                xlsWorkBook = new HSSFWorkbook(ExcelFile);
                xlsWorkSheet = xlsWorkBook.getSheetAt(0);

                int startRow = 1;
                int startCol = 0;
                int ci, cj;
                int totalRows = ExcelUtils.xlsRowCount();
                int totalCols = ExcelUtils.xlsColumnCount();
                tabArray = new Object[totalRows - 1][totalCols];
                ci = 0;
                for (int i = startRow; i < totalRows; i++) {
                    cj = 0;
                    for (int j = startCol; j < totalCols; j++) {
                        tabArray[ci][cj] = getCellData_XLS(i, j);
                        cj++;
                    }
                    ci++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Could not Find the Excel File/Sheet");
        } catch (Exception e) {
            throw new Exception("Could not Open the Excel File");
        }
        return (tabArray);
    }


    /**
     * To get cell data from Excel-XLSX
     */
    public static Object getCellData_XLSX(int RowNum, int ColNum) throws Exception {
        Object CellData = null;
        try {
            XSSFCell xlsxCell = xlsxWorkSheet.getRow(RowNum).getCell(ColNum);
            if (xlsxCell.getCellType() == Cell.CELL_TYPE_STRING) {
                CellData = xlsxCell.getStringCellValue();
            } else if (xlsxCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                CellData = xlsxCell.getNumericCellValue();
            } else if (xlsxCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                CellData = xlsxCell.getBooleanCellValue();
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * To get cell data from Excel-XLS
     */
    public static Object getCellData_XLS(int RowNum, int ColNum) throws Exception {
        Object CellData = null;
        try {
            HSSFCell xlsCell = xlsWorkSheet.getRow(RowNum).getCell(ColNum);
            if (xlsCell.getCellType() == Cell.CELL_TYPE_STRING) {
                CellData = xlsCell.getStringCellValue();
            } else if (xlsCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                CellData = xlsCell.getNumericCellValue();
            } else if (xlsCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                CellData = xlsCell.getBooleanCellValue();
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * To get Excel-XLSX Row Count
     */
    public static int xlsxRowCount() {
        return xlsxWorkSheet.getLastRowNum() + 1;
    }

    /**
     * To get Excel-XLS Row Count
     */
    public static int xlsRowCount() {
        return xlsWorkSheet.getLastRowNum() + 1;
    }

    /**
     * To get Excel-XLSX Column Count
     */
    public static int xlsxColumnCount() {
        return xlsxWorkSheet.getRow(0).getLastCellNum();
    }

    /**
     * To get Excel-XLS Column Count
     */
    public static int xlsColumnCount() {
        return xlsWorkSheet.getRow(0).getLastCellNum();
    }
}