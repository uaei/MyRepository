package com.opentext.utils.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

public class ReadExcel {

    /**
     * POI 读取高版本Excel文件
     *
     * @author yangtingting
     * @date 2019/07/29
     */
    public static void main(String[] args) throws Exception {
        //创建Excel，读取文件内容
        File readFile = new File("C:/Users/opentext/Desktop/POI/gd_city.xlsx");
        XSSFWorkbook readWorkbook = new XSSFWorkbook(FileUtils.openInputStream(readFile));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet readSheet = readWorkbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = readSheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            Row row = readSheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                System.out.print(value + "  ");
            }
            System.out.println();
        }
        readWorkbook.close();
    }
}




