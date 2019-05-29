package com.hth.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * excel表格处理
 *
 * @Author HeTongHao
 * @Date 18/10/27 17:28
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    private static final String XLS = "xls";
    private static final String XLS_X = "xlsx";

    /**
     * 获取 WorkBook工作薄对象
     *
     * @param is       文件流
     * @param fileName 文件名称
     * @return
     * @throws
     */
    public static Workbook getWorkBook(InputStream is, String fileName) {
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            try {
                if (fileName.endsWith(XLS)) {
                    //2003
                    workbook = new HSSFWorkbook(is);
                } else if (fileName.endsWith(XLS_X)) {
                    //2007
                    workbook = new XSSFWorkbook(is);
                }
            } finally {
                //关闭数据流
                is.close();
            }
        } catch (IOException e) {
            logger.error("获取 WorkBook 异常：" + e.getMessage());
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 创建一个列并设置值
     *
     * @param row
     * @return
     */
    public static HSSFCell createCellAndSetValue(HSSFRow row, Integer celNumber, Object value) {
        HSSFCell cell = row.createCell(celNumber);
        cell.setCellValue(String.valueOf(value));
        return cell;
    }

    /**
     * 创建下一个列
     *
     * @param row
     * @return
     */
    public static HSSFCell createNextCell(HSSFRow row) {
        int rowNumber = row.getLastCellNum();
        HSSFCell cell = row.createCell(rowNumber == -1 ? 0 : rowNumber);
        return cell;
    }

    /**
     * 创建下一个列,并写入值
     *
     * @param row
     * @param value
     * @return
     */
    public static HSSFCell createNextCell(HSSFRow row, Object value) {
        int rowNumber = row.getLastCellNum();
        HSSFCell cell = row.createCell(rowNumber == -1 ? 0 : rowNumber);
        cell.setCellValue(value != null ? String.valueOf(value) : "");
        return cell;
    }

    /**
     * 创建下一个行
     *
     * @param sheet
     * @return
     */
    public static HSSFRow createNextRow(HSSFSheet sheet) {
        int rowNumber = sheet.getLastRowNum();
        if (rowNumber == 0 && sheet.getRow(0) == null) {
            return sheet.createRow(0);
        }
        return sheet.createRow(rowNumber + 1);
    }

    /**
     * 响应文件流
     *
     * @param fileName
     * @param workbook
     * @param response
     * @throws IOException
     */
    public static void responseFileStream(String fileName, HSSFWorkbook workbook, HttpServletResponse response) throws IOException {
        OutputStream outputStream = null;
        try {
            //文件名
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls\"");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            workbook.write(outputStream);
            logger.info("'" + fileName + "'表格导出成功!");
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    /**
     *         设置风格代码
     *         HSSFCellStyle cellStyle = workbook.createCellStyle();
     *         cellStyle.setAlignment(HorizontalAlignment.CENTER);  //列中间对其
     *         headCell.setCellStyle(cellStyle);  //设置头的风格
     *         sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));  //合并第一行 5列单元格
     */
}
