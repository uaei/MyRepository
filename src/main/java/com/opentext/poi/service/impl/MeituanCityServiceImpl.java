package com.opentext.poi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opentext.poi.entity.GaodeCity;
import com.opentext.poi.entity.MeituanCity;
import com.opentext.poi.mapper.GaodeCityMapper;
import com.opentext.poi.mapper.MeituanCityMapper;
import com.opentext.poi.mapper.SgmCityMapper;
import com.opentext.poi.service.MeituanCityService;
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
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 保存美团城市级别信息，最低到包含省，地级市，县级市，商圈 服务实现类
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@Service
public class MeituanCityServiceImpl extends ServiceImpl<MeituanCityMapper, MeituanCity> implements MeituanCityService {

    @Autowired
    private MeituanCityMapper meituanCityMapper;

    @Autowired
    private SgmCityMapper sgmCityMapper;

    @Autowired
    private GaodeCityMapper gaodeCityMapper;



    @Override
    public List<MeituanCity>  selectMeituanCity(String name) {
        QueryWrapper<MeituanCity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MeituanCity.NAME,name);
        List<MeituanCity> meituanCityList = meituanCityMapper.selectList(queryWrapper);
        return meituanCityList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean importExcelToMtCity() throws IOException {

        List<MeituanCity> meituanCities = new ArrayList<>();

        //读取excel中的数据
        //创建Excel，读取文件内容
        File file = new File("C:/Users/opentext/Desktop/mt_city_3.xlsx");
        //根据excel文件创建excel对象
        FileInputStream fileInputStream = FileUtils.openInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        //两种方式获取指定的工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {

            MeituanCity mtMeituanCity = new MeituanCity();

            mtMeituanCity.setMtCityId(UUIDUtils.getUUID());
            mtMeituanCity.setStatus("A");
            mtMeituanCity.setCreateUser("admin");
            mtMeituanCity.setCreateTime(LocalDateTime.now());
            mtMeituanCity.setUpdateUser("admin");
            mtMeituanCity.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
            //美团城市名称	美团城市code	 美团城市parentCode	美团城市名称拼音	sgmParentCode	sgmCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                list.add(value);
            }
            //将数据插入数据库中
            mtMeituanCity.setSgmCityId(list.get(5));
            mtMeituanCity.setName(list.get(0));
            mtMeituanCity.setCode(list.get(1));
            mtMeituanCity.setParentCode("--NULL--");
            mtMeituanCity.setPinyin(list.get(3));
            meituanCities.add(mtMeituanCity);
            System.out.println();
        }
        //MybatisPlus批处理插入
        boolean bool = this.saveBatch(meituanCities);
        fileInputStream.close();
        return bool;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean importExcelToMtArea() throws IOException{

        List<MeituanCity> meituanCities = new ArrayList<>();

        //读取excel中的数据
        //创建Excel，读取文件内容
        File file = new File("C:/Users/opentext/Desktop/mt_area_3.xlsx");
        //根据excel文件创建excel对象
        FileInputStream fileInputStream = FileUtils.openInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        //两种方式获取指定的工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {

            MeituanCity mtMeituanCity = new MeituanCity();

            mtMeituanCity.setMtCityId(UUIDUtils.getUUID());
            mtMeituanCity.setStatus("A");
            mtMeituanCity.setCreateUser("admin");
            mtMeituanCity.setCreateTime(LocalDateTime.now());
            mtMeituanCity.setUpdateUser("admin");
            mtMeituanCity.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                //美团区域名称	美团区域code	  美团区域parentCode	  sgmCode	sgmParentCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                list.add(value);
            }
            //将数据插入数据库中
            mtMeituanCity.setSgmCityId(list.get(3));
            mtMeituanCity.setName(list.get(0));
            mtMeituanCity.setCode(list.get(1));
            mtMeituanCity.setParentCode(list.get(2));
            meituanCities.add(mtMeituanCity);
            System.out.println();
        }
        //MybatisPlus批处理插入
        boolean bool = this.saveBatch(meituanCities);
        fileInputStream.close();
        return bool;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean importExcelToMtCommerceArea() throws IOException {

        List<MeituanCity> meituanCities = new ArrayList<>();

        //读取excel中的数据
        //创建Excel，读取文件内容
        File file = new File("C:/Users/opentext/Desktop/mt_commerce_2.xlsx");
        //根据excel文件创建excel对象
        FileInputStream fileInputStream = FileUtils.openInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        //两种方式获取指定的工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {

            MeituanCity mtMeituanCity = new MeituanCity();

            mtMeituanCity.setMtCityId(UUIDUtils.getUUID());
            mtMeituanCity.setStatus("A");
            mtMeituanCity.setCreateUser("admin");
            mtMeituanCity.setCreateTime(LocalDateTime.now());
            mtMeituanCity.setUpdateUser("admin");
            mtMeituanCity.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                //商圈名称	商圈code	   区域code	   sgmCommerceCode	  sgmAreaCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                list.add(value);
            }
            //将数据插入数据库中
            mtMeituanCity.setName(list.get(0));
            mtMeituanCity.setCode(list.get(1));
            mtMeituanCity.setParentCode(list.get(2));
            mtMeituanCity.setSgmCityId(list.get(3));
            meituanCities.add(mtMeituanCity);
            System.out.println();
        }
        //MybatisPlus批处理插入
        boolean bool = this.saveBatch(meituanCities);
        fileInputStream.close();
        return bool;

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer correlateDataGdArea() {

        int count =0;
        //高德的所有市和区
        QueryWrapper<GaodeCity> gaodeCityQueryWrapper = new QueryWrapper<>();
        gaodeCityQueryWrapper.eq(GaodeCity.SGM_CITY_ID,"--NULL--");
        List<GaodeCity> gaodeCityList = gaodeCityMapper.selectList(gaodeCityQueryWrapper);

        //美团的所有市和区
        List<MeituanCity> meituanCityList = this.list(null);
//        List<MeituanCity> meituanCityList = meituanCityMapper.selectList(null);

        for (GaodeCity gaodeCity : gaodeCityList) {

            String gaodeCityName = gaodeCity.getName();

            for (MeituanCity meituanCity : meituanCityList) {

                String meituanCityName = meituanCity.getName();

                String substring =gaodeCityName;

                if (gaodeCityName.endsWith("区")){
                    substring = gaodeCityName.substring(0, gaodeCityName.length() - 1);
                }

                //区域名称不同
                if (gaodeCityName.equals(meituanCityName) || meituanCityName.equals(substring)){
                    gaodeCity.setSgmCityId(meituanCity.getSgmCityId());
                    gaodeCityMapper.updateById(gaodeCity);
                    count++;
                }
            }
        }
        System.out.println("------------高德区域关系映射结束-----------------"+count);
        return count;
    }

}
