package com.ecpss.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Execel(2003->xls,2007以上->xlsx)解析工具类
 *
 * @author liaoxuan
 */
public class ParseExcelUtil {

    /**
     * 2003版本 最大支持65536 行
     */
    private static final String EXCEL_XLS = "xls";
    /**
     * 2007 版本以上 最大支持1048576行
     */
    private static final String EXCEL_XLSX = "xlsx";
    /**
     * 要解析excel中的列名
     */
    private static List<String> columns;
    /**
     * 要解析的sheet下标
     */
    private static int sheetNum = 0;

    public static List<String> getColumns() {
        return ParseExcelUtil.columns;
    }

    public static void setColumns(List<String> columns) {
        ParseExcelUtil.columns = columns;
    }

    public static int getSheetNum() {
        return sheetNum;
    }

    public static void setSheetNum(int sheetNum) {
        ParseExcelUtil.sheetNum = sheetNum;
    }

    /**
     * 直接传入输入流和文件名（xxx.xls或xxx.xlsx）获取对应的Workbook
     *
     * @param ins
     * @param fileName
     * @return
     */
    public static Workbook getWorkbook(InputStream ins, String fileName) {
        Workbook wb;
        if (fileName.endsWith(EXCEL_XLS)) {
            //Excel2003及以下
            try {
                wb = new HSSFWorkbook(ins);
            } catch (Exception e) {
                e.printStackTrace();
                wb = null;
            }
        } else if (fileName.endsWith(EXCEL_XLSX)) {
            //Excel2007及以上
            try {
                wb = new XSSFWorkbook(ins);
            } catch (Exception e) {
                e.printStackTrace();
                wb = null;
            }
        } else {
            throw new RuntimeException("");
        }
        return wb;
    }

    /**
     * 判断Excel文件是否有效,无效时抛出异常
     *
     * @param file
     * @throws Exception
     */
    public static void checkExcelValid(File file) throws Exception {
        if (!file.exists()) {
            //文件不存在
            throw new Exception("文件不存在");
        }
        boolean result = !(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)));
        if (result) {
            throw new Exception("不是标准的Excel文件");
        }
    }

    /**
     * 网页上传文件时，通过流的方式更为便捷，从startIndex到(总行数-endIndex)之间的数据，返回json数组
     *
     * @param ins
     * @param fileName
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String readExcel(InputStream ins, String fileName, int startIndex, int endIndex) {
        StringBuilder retJson = new StringBuilder();
        Workbook workbook = getWorkbook(ins, fileName);
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            //最后一行
            int lastRowNum = sheet.getLastRowNum() + 1;
            retJson.append("[");
            for (int i = startIndex; i < lastRowNum - endIndex; i++) {
                //获得行
                Row row = sheet.getRow(i);
                if (row != null) {
                    String rowJson = readExcelRow(row);
                    retJson.append(rowJson);
                    if (i < lastRowNum - 1) {
                        retJson.append(",");
                    }
                }
            }
            retJson.append("]");
        }
        try {
            //关闭资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return retJson.toString();
        }
    }

    /**
     * 将json转换为集合，使用阿里的fastjson框架非常便捷
     *
     * @param json
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> Collection<E> readExcel(String json, Class<E> clazz) {
        return JSONObject.parseArray(json, clazz);
    }

    /**
     * 读取每行Row的数据，每行数据对应一个javabean
     *
     * @param row
     * @return 返回一个json对象
     */
    private static String readExcelRow(Row row) {
        StringBuilder rowJson = new StringBuilder();
        //最后一个单元格
        int lastCellNum = ParseExcelUtil.columns.size();
        rowJson.append("{");
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i);
            String cellVal = readCellValue(cell);
            rowJson.append(toJsonItem(columns.get(i), cellVal));
            if (i < lastCellNum - 1) {
                rowJson.append(",");
            }
        }
        rowJson.append("}");
        return rowJson.toString();
    }

    /**
     * 读取每个单元格Cell的value,对NUMERIC类型的Cell需要做特别处理
     *
     * @param cell
     * @return 返回Cell的value
     */
    @SuppressWarnings("static-access")
    private static String readCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        CellType type = cell.getCellTypeEnum();
        String cellValue;
        switch (type) {
            case BLANK:
                cellValue = "";
                break;
            case _NONE:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "";
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //日期类型
                    Date date = cell.getDateCellValue();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = format.format(date);
                } else {
                    //货币类型 等等
                    //如果Cell是科学计数法类型的数据或者货币类型时，获取不到想要的字符串，此时通过NumberToTextConverter工具类
                    // 的toText(...)方法可以解决该问题，获取字符串
                    cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;

    }

    /**
     * 转换为json对
     *
     * @return
     */
    private static String toJsonItem(String name, String val) {
        val = val == null ? "" : val;
        return "\"" + name + "\":\"" + val + "\"";
    }
}
