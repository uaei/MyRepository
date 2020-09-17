package com.opentext.poi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opentext.poi.entity.GaodeCity;
import com.opentext.poi.entity.SgmCity;
import com.opentext.poi.mapper.GaodeCityMapper;
import com.opentext.poi.mapper.SgmCityMapper;
import com.opentext.poi.service.SgmCityService;
import com.opentext.utils.uuid.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
 * 保存SGM自定义的城市级别信息，用户使用商圈信息，按照美团的标准建立，包含省，地级市，县级市，商圈。 服务实现类
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@Service
public class SgmCityServiceImpl extends ServiceImpl<SgmCityMapper, SgmCity> implements SgmCityService {


    @Autowired
    private SgmCityMapper sgmCityMapper;

    @Autowired
    private GaodeCityMapper gaodeCityMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean importExcelForSgmCity() throws IOException {

            List<SgmCity> sgmCityList = new ArrayList<>();

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

                SgmCity sgmCity = new SgmCity();
                sgmCity.setSgmCityId(UUIDUtils.getUUID());
                sgmCity.setStatus("A");
                sgmCity.setCreateUser("admin");
                sgmCity.setCreateTime(LocalDateTime.now());
                sgmCity.setUpdateUser("admin");
                sgmCity.setUpdateTime(LocalDateTime.now());

                List<String> list = new ArrayList<>();
                Row row = sheet.getRow(i);
                //获取当前行最后单元格列号
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    // 美团城市名称	美团城市code	 美团城市parentCode	美团城市名称拼音	sgmParentCode	sgmCode
                    DataFormatter formatter = new DataFormatter();
                    String value = formatter.formatCellValue(row.getCell(j));
                    list.add(value);
                }
                //将数据插入数据库中
                sgmCity.setName(list.get(0));
                sgmCity.setCode(list.get(5));
                sgmCity.setParentCode(list.get(5));
                sgmCity.setPinyin(list.get(3));
                String initial = list.get(3).substring(0, 1).toUpperCase();
                sgmCity.setCityInitial(initial);
                sgmCityList.add(sgmCity);
                System.out.println();
            }
            //MybatisPlus批处理插入
            boolean bool = this.saveBatch(sgmCityList);
            fileInputStream.close();
            return bool;
        }

    @Override
    public Boolean importExcelForSgmArea() throws IOException {

        List<SgmCity> sgmCityList = new ArrayList<>();

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

            SgmCity sgmCity = new SgmCity();
            sgmCity.setSgmCityId(UUIDUtils.getUUID());
            sgmCity.setStatus("A");
            sgmCity.setCreateUser("admin");
            sgmCity.setCreateTime(LocalDateTime.now());
            sgmCity.setUpdateUser("admin");
            sgmCity.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {

                // 美团区域名称	美团区域code	 美团区域parentCode	 sgmCode   sgmParentCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));

                list.add(value);
            }
            //将数据插入数据库中
            sgmCity.setName(list.get(0));
            sgmCity.setCode(list.get(3));
            sgmCity.setParentCode(list.get(4));

            sgmCityList.add(sgmCity);
            System.out.println();
        }
        //MybatisPlus批处理插入
        boolean bool = this.saveBatch(sgmCityList);
        fileInputStream.close();
        return bool;
    }

    @Override
    public Boolean importExcelForSgmCommerceArea() throws IOException {

        List<SgmCity> sgmCityList = new ArrayList<>();

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

            SgmCity sgmCity = new SgmCity();
            sgmCity.setSgmCityId(UUIDUtils.getUUID());
            sgmCity.setStatus("A");
            sgmCity.setCreateUser("admin");
            sgmCity.setCreateTime(LocalDateTime.now());
            sgmCity.setUpdateUser("admin");
            sgmCity.setUpdateTime(LocalDateTime.now());

            List<String> list = new ArrayList<>();
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {

                //商圈名称	商圈code  	区域code 	sgmCommerceCode	   sgmAreaCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));

                list.add(value);
            }
            //将数据插入数据库中
            sgmCity.setName(list.get(0));
            sgmCity.setCode(list.get(3));
            sgmCity.setParentCode(list.get(4));
            sgmCityList.add(sgmCity);
            System.out.println();
        }
        //MybatisPlus批处理插入
        boolean bool = this.saveBatch(sgmCityList);
        fileInputStream.close();
        return bool;
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean findCity() {

        int cityCount = 0;

//        QueryWrapper<GaodeCity> gaodeCityQueryWrapper = new QueryWrapper<>();
//        gaodeCityQueryWrapper.likeLeft(GaodeCity.NAME,"市");
//        gaodeCityQueryWrapper.eq(GaodeCity.CODE,GaodeCity.PARENT_CODE);
//        gaodeCityQueryWrapper.ne(GaodeCity.SGM_CITY_ID,"--NULL--");
//        //高德城市集合：296
//        List<GaodeCity> gaodeCityList = gaodeCityMapper.selectList(gaodeCityQueryWrapper);
//
//        UpdateWrapper<SgmCity> sgmCityUpdateWrapper = new UpdateWrapper<>();
//        sgmCityUpdateWrapper.ne(SgmCity.PINYIN,null);
//        //sgm城市集合：1196
//        List<SgmCity> sgmCityList = sgmCityMapper.selectList(sgmCityUpdateWrapper);

        List<GaodeCity> gaodeCityList =  gaodeCityMapper.selectGaoDeCity();

        List<SgmCity> sgmCityList = sgmCityMapper.selectSgmCity();

        //根据高德的城市名称，查找sgm的城市名称，修改多余的区域拼音
        for (GaodeCity gaodeCity : gaodeCityList) {
            String gaodeCityName = gaodeCity.getName();

            for (SgmCity sgmCity : sgmCityList) {
                String cityName = sgmCity.getName();

                String sgmCityName = cityName.concat("市");

                //城市名称相同时，为城市直接跳过
                if (gaodeCityName.equals(sgmCityName)){
                    String pinyin = sgmCity.getPinyin();
                    String initial = pinyin.substring(0, 1).toUpperCase();
                    sgmCity.setCityInitial(initial);
                    sgmCityMapper.updateById(sgmCity);
                    cityCount++;
                    break;
                }
                //名称不同的为区域，将sgm的区域拼音置为null
            }
        }

        System.out.println("城市数量"+cityCount);
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addAreaInitial() {

        int count = 0;
        LambdaUpdateWrapper<SgmCity> sgmCityLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        sgmCityLambdaUpdateWrapper.isNotNull(SgmCity::getPinyin);
        sgmCityLambdaUpdateWrapper.isNull(SgmCity::getCityInitial);
        List<SgmCity> sgmCities = sgmCityMapper.selectList(sgmCityLambdaUpdateWrapper);
        for (SgmCity sgmCity : sgmCities) {
            String pinyin = sgmCity.getPinyin().substring(0, 1).toLowerCase();
            sgmCity.setCityInitial(pinyin);
            sgmCity.setUpdateTime(LocalDateTime.now());
            int i = sgmCityMapper.updateById(sgmCity);
            count+=i;
        }
        return count;
    }

    @Override
    public int selectSgmIdNull() {

        int count = 0;
        LambdaUpdateWrapper<SgmCity> sgmCityLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        sgmCityLambdaUpdateWrapper.isNotNull(SgmCity::getCityInitial);
        List<SgmCity> sgmCities = sgmCityMapper.selectList(sgmCityLambdaUpdateWrapper);
        for (SgmCity sgmCity : sgmCities) {
            if (StringUtils.isAllUpperCase(sgmCity.getCityInitial())){
                continue;
            }
            LambdaUpdateWrapper<SgmCity> sgmCityLambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
            sgmCityLambdaUpdateWrapper1.eq(SgmCity::getParentCode,sgmCity.getCode());
            sgmCityLambdaUpdateWrapper1.ne(SgmCity::getCode,sgmCity.getCode());
//            Integer integer = sgmCityMapper.selectCount(sgmCityLambdaUpdateWrapper1);
//            if (integer==0){
//                continue;
//            }
            List<SgmCity> sgmCities1 = sgmCityMapper.selectList(sgmCityLambdaUpdateWrapper1);
            for (SgmCity city : sgmCities1) {
                LambdaUpdateWrapper<SgmCity> sgmCityLambdaUpdateWrapper2 = new LambdaUpdateWrapper<>();
                sgmCityLambdaUpdateWrapper2.eq(SgmCity::getParentCode,city.getCode());
                Integer integer1 = sgmCityMapper.selectCount(sgmCityLambdaUpdateWrapper2);
                if (integer1==0){
                    count++;
                    System.out.println("没有商圈的城市："+sgmCity.getName());
                    break;
                }
                if (integer1>0){
                    break;
                }
            }
        }
        return count;
    }



}
