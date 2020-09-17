package com.opentext.poi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opentext.poi.entity.BaiduCity;
import com.opentext.poi.mapper.BaiduCityMapper;
import com.opentext.poi.service.BaiduCityService;
import com.opentext.utils.uuid.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 百度城市表 服务实现类
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
@Service
public class BaiduCityServiceImpl extends ServiceImpl<BaiduCityMapper, BaiduCity> implements BaiduCityService {


    @Autowired
    private BaiduCityMapper baiduCityMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean importExcelToBDCity() throws Exception {

        HashMap<String, BaiduCity> hashMap = new HashMap<>();
        List<BaiduCity> baiduCities = new ArrayList<>();

        //创建Excel，读取文件内容
        File readFile = new File("C:/Users/opentext/Desktop/POI/百度/百度城市数据/baiDu_city_02.xlsx");
        XSSFWorkbook readWorkbook = new XSSFWorkbook(FileUtils.openInputStream(readFile));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet readSheet = readWorkbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int readLastRowNum = readSheet.getLastRowNum();
        for (int i = 1; i <= readLastRowNum; i++) {

            BaiduCity baiduCity = new BaiduCity();

            baiduCity.setBdCityId(UUIDUtils.getUUID());
            baiduCity.setStatus("A");
            baiduCity.setCreateUser("admin");
            baiduCity.setCreateTime(LocalDateTime.now());
            baiduCity.setUpdateUser("admin");
            baiduCity.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = readSheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                //0百度城市name	 1百度城市code	 2百度城市parentCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                list.add(value);
            }
            //将数据插入数据库中
            baiduCity.setSgmCityId("--NULL--");
            baiduCity.setName(list.get(0));
            baiduCity.setCode(list.get(1));
            baiduCity.setParentCode(list.get(2));

            hashMap.put(list.get(1),baiduCity);
            System.out.println();
        }

        System.out.println("------------------我是分割线------------------");

        //读取excel中的数据
        //创建Excel，读取文件内容
//        File file = new File("C:/Users/opentext/Desktop/POI/百度/百度城市数据/baiDu_city_02.xlsx");
        File file = new File("C:/Users/opentext/Desktop/POI/百度/百度城市数据/baiDu_area_02.xlsx");
        //根据excel文件创建excel对象
        FileInputStream fileInputStream = FileUtils.openInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        //两种方式获取指定的工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {

            BaiduCity baiduCity = new BaiduCity();

            baiduCity.setBdCityId(UUIDUtils.getUUID());
            baiduCity.setStatus("A");
            baiduCity.setCreateUser("admin");
            baiduCity.setCreateTime(LocalDateTime.now());
            baiduCity.setUpdateUser("admin");
            baiduCity.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                //0百度城市name	 1百度城市code	 2百度城市parentCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                list.add(value);
            }
            //将数据插入数据库中
            baiduCity.setSgmCityId("--NULL--");
            baiduCity.setName(list.get(0));
            baiduCity.setCode(list.get(1));
            baiduCity.setParentCode(list.get(2));

            hashMap.put(list.get(1),baiduCity);
            System.out.println();
        }
        System.out.println("------------------开始插入------------------");
        //MybatisPlus批处理插入
        Set<Map.Entry<String, BaiduCity>> entries = hashMap.entrySet();
        for (Map.Entry<String, BaiduCity> entry : entries) {

            BaiduCity baiduCity = entry.getValue();
            baiduCities.add(baiduCity);
        }
        boolean bool = this.saveBatch(baiduCities);
        fileInputStream.close();
        workbook.close();
        System.out.println("导入数据成功："+baiduCities.size());
        return bool;
    }

    @Override
    public BaiduCity selectBDCityByCode(String code) {

        BaiduCity baiduCity = this.baiduCityMapper.selectById(code);

        return baiduCity;
    }

}
