package com.opentext.utils.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcel {

    /**
     * POI 创建高版本Excel文件
     * @author yangtingting
     * @date 2019/07/29
     */
    public static void main(String[] args) throws IOException {
        //创建Excel文件薄
        XSSFWorkbook writeWorkbook = new XSSFWorkbook();
        //创建工作表sheeet
        Sheet writeSheet = writeWorkbook.createSheet();
        //创建第一行
        Row row = writeSheet.createRow(0);
        String[] title ={"name","code","parentCode"};
        Cell cell = null;
        for (int i= 0; i < title.length; i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据
        for (int i=1;i<10;i++){
            //创建第i行
            Row nextRow=writeSheet.createRow(i);

            //第i行第一个单元格
            Cell cell2=nextRow.createCell(0);
            cell2.setCellValue("a"+i);

            //第i行第二个单元格
            cell2=nextRow.createCell(1);
            cell2.setCellValue("user"+i);

            //第i行第三个单元格
            cell2=nextRow.createCell(2);
            cell2.setCellValue("男");
        }


        //创建一个文件
        File writeFile = new File("C:/Users/opentext/Desktop/poi_test.xlsx");
        writeFile.createNewFile();
        FileOutputStream writeStream= FileUtils.openOutputStream(writeFile);
        writeWorkbook.write(writeStream);
        writeStream.close();
        writeWorkbook.close();
    }

}
