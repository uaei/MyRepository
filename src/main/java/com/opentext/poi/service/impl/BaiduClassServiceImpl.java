package com.opentext.poi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opentext.poi.entity.BaiduClass;
import com.opentext.poi.mapper.BaiduClassMapper;
import com.opentext.poi.service.BaiduClassService;
import com.opentext.utils.uuid.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 百度分类表 服务实现类
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
@Service
public class BaiduClassServiceImpl extends ServiceImpl<BaiduClassMapper, BaiduClass> implements BaiduClassService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean importExcelToBDClass() throws Exception {

        List<BaiduClass> baiduClasses = new ArrayList<>();

        //读取excel中的数据
        //创建Excel，读取文件内容
//        File file = new File("C:/Users/opentext/Desktop/POI/百度/百度分类数据/baiDu_class_01.xlsx");
        File file = new File("C:/Users/opentext/Desktop/POI/百度/百度分类数据/baiDu_class_02.xlsx");
        //根据excel文件创建excel对象
        FileInputStream fileInputStream = FileUtils.openInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        //两种方式获取指定的工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {

            BaiduClass baiduClass = new BaiduClass();
            baiduClass.setBdClassId(UUIDUtils.getUUID());
            baiduClass.setStatus("A");
            baiduClass.setCreateUser("admin");
            baiduClass.setCreateTime(LocalDateTime.now());
            baiduClass.setUpdateUser("admin");
            baiduClass.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                //0百度一级分类name	1百度一级分类type	  2百度一级分类parentType
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                list.add(value);
            }
            //将数据插入数据库中
            baiduClass.setSgmClassId("--NULL--");
            baiduClass.setName(list.get(0));
            baiduClass.setType(list.get(1));
            baiduClass.setParentType(list.get(2));

            baiduClasses.add(baiduClass);
            System.out.println();
        }
        //MybatisPlus批处理插入
        boolean bool = this.saveBatch(baiduClasses);
        fileInputStream.close();
        System.out.println("导入数据成功："+baiduClasses.size());
        return bool;
    }
}
