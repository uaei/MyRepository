package com.opentext.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opentext.poi.entity.SgmCity;
import com.opentext.poi.entity.poi.*;
import com.opentext.poi.model.*;
import com.opentext.poi.service.impl.SgmCityServiceImpl;
import com.opentext.utils.uuid.SgmIdKit;
import com.opentext.utils.uuid.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MybatisPulsGeneratorApplication.class)
class MybatisPulsGeneratorApplicationTests {

    @Autowired
    private RestTemplate restTemplate;


	@Test
	void contextLoads() {
	}

    /**
     * 查询所有城市的测试接口
     */
    @Test
    public void httpGetCity() {
        String MtDTOStr = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/city", String.class);
        JSONObject jsonObject = JSON.parseObject(MtDTOStr);
        JSONArray data = jsonObject.getJSONArray("data");
        List<MtDTO> cityList = data.toJavaList(MtDTO.class);
        for (MtDTO mtDTO : cityList) {
            System.out.println(mtDTO);
        }
    }


    /**
     * 查询城市和区域的测试接口
     */
    @Test
    public void httpGetCityAndArea(){
        String MtDTOStr = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/city", String.class);
        JSONObject jsonObject = JSON.parseObject(MtDTOStr);
        JSONArray data = jsonObject.getJSONArray("data");
        List<MtDTO> list = data.toJavaList(MtDTO.class);

        List<MtCity> mtCities = new ArrayList<>();

        for (MtDTO mtDTO : list) {
            MtCity mtCity = new MtCity();
            mtCity.setId(mtDTO.getId());
            mtCity.setName(mtDTO.getName());
            String forObject = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/district?cityid={id}", String.class, mtDTO.getId());
            JSONObject jsonObject1 = JSON.parseObject(forObject);
            JSONArray data1 = jsonObject1.getJSONArray("data");
            List<HttpArea> httpAreas = data1.toJavaList(HttpArea.class);
            List<HttpArea> httpAreas1 = new ArrayList<>();
            for (HttpArea httpArea : httpAreas) {
                httpAreas1.add(httpArea);
            }
            mtCity.setHttpAreas(httpAreas1);
            mtCities.add(mtCity);
        }

        System.out.println(mtCities.toString());
    }


    /**
     * 查询区域的测试接口
     */
    @Test
    public void httpGetArea(){

        String forObject = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/district?cityid={id}", String.class, 1);

        JSONObject jsonObject = JSON.parseObject(forObject);
        JSONArray data = jsonObject.getJSONArray("data");
        List<HttpArea> httpAreas = data.toJavaList(HttpArea.class);
        for (HttpArea httpArea : httpAreas) {
            System.out.println(httpArea);
        }
    }


    /**
     * 查询商圈的测试接口
     */
    @Test
    public void  getCommerce(){

        List<Commerce> commerceList = new ArrayList<>();

        String forObject = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/area?cityid={id}", String.class, 1);
        JSONObject jsonObject = JSON.parseObject(forObject);
        JSONArray dataJson = jsonObject.getJSONArray("data");
        System.out.println(dataJson.toJSONString());

        for (int i = 0; i < dataJson.size(); i++) {
            String commerceStr = dataJson.get(i).toString();
            JSONObject commerceJson = JSON.parseObject(commerceStr);

            int id = commerceJson.getIntValue("id");
            String name = commerceJson.getString("name");
            JSONArray area = commerceJson.getJSONArray("area");
            List<CommerceArea> commerceAreas = area.toJavaList(CommerceArea.class);

            Commerce commerce = new Commerce();
            commerce.setId(id);
            commerce.setName(name);
            commerce.setCommerceAreas(commerceAreas);

            commerceList.add(commerce);
        }

        for (Commerce commerce : commerceList) {
//            System.out.println(commerce);
        }
    }


    /**
     * 创建美团城市excel
     * @throws IOException
     */
    @Test
    public void createMtCityExcel() throws IOException {

        String MtDTOStr = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/city", String.class);
        JSONObject jsonObject = JSON.parseObject(MtDTOStr);
        JSONArray data = jsonObject.getJSONArray("data");
        List<MtDTO> cityList = data.toJavaList(MtDTO.class);

        //创建Excel文件薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表sheet
        Sheet sheet = workbook.createSheet();
        //创建第一行
        Row row= sheet.createRow(0);
        String[] title={"美团城市名称","美团城市code","美团城市parentCode","美团城市名称拼音","sgmParentCode","sgmCode"};
        Cell cell = null;
        for (int i= 0; i < title.length; i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }

        SgmIdKit sgmIdKit = new SgmIdKit("0000000000",4,3,3);

        //追加数据
        for (int i = 0; i < cityList.size(); i++) {

            //创建第i+1行
            Row nextrow=sheet.createRow(i+1);

            //第i+1行第一个单元格：城市名称
            Cell cell1 = nextrow.createCell(0);
            cell1.setCellValue(cityList.get(i).getName());

            //第i+1行第二个单元格：美团城市code
            cell1=nextrow.createCell(1);
            cell1.setCellValue(cityList.get(i).getId());

            //第i+1行第三个单元格：美团城市parentCode
            cell1=nextrow.createCell(2);
            cell1.setCellValue((String) null);

            //城市名称拼音
            cell1=nextrow.createCell(3);
            cell1.setCellValue(cityList.get(i).getPinyin());

            //sgmParentCode
            cell1=nextrow.createCell(4);
            cell1.setCellValue(sgmIdKit.nextBig().toString());

            //第i+1行第三个单元格：sgmCode
            cell1=nextrow.createCell(5);
            cell1.setCellValue(sgmIdKit.nextBig().toString());
            System.out.println();
        }

        //创建一个文件
        File file=new File("C:/Users/opentext/Desktop/mt_city_3.xlsx");
        file.createNewFile();
        FileOutputStream stream = FileUtils.openOutputStream(file);
        workbook.write(stream);
        stream.close();
        workbook.close();
        System.out.println("城市excel创建完成。。。。。。。。。。。。。。");
    }



    /**
     * 创建美团区域excel
     * @throws IOException
     */
    @Test
    public void createMtAreaExcel() throws IOException {
        Map<Integer,String> cityCodeList = new HashMap<>();

        String mtDtoStr = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/city", String.class);
        JSONObject mtDtoJson = JSON.parseObject(mtDtoStr);
        JSONArray mtData = mtDtoJson.getJSONArray("data");
        List<MtDTO> cityList = mtData.toJavaList(MtDTO.class);
        SgmIdKit sgmIdKit = new SgmIdKit("0000000000",4,3,3);
        //先获取所有城市的code，将城市code放入集合中
        for (MtDTO mtDTO : cityList) {
            cityCodeList.put(mtDTO.getId(),sgmIdKit.nextBig().toString());
        }

        List<MtArea> mtAreaList = new ArrayList<>();
        //通过城市的code查询所有的区域
        for (Map.Entry<Integer,String> e : cityCodeList.entrySet()) {
            Integer cityCode = e.getKey();
            sgmIdKit = new SgmIdKit(e.getValue(),4,3,3);
            String sgmAresParentCode=sgmIdKit.toString();

            //通过城市code查询所有的区域code
            String areaStr = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/district?cityid={id}", String.class, cityCode);
            JSONObject areaJson = JSON.parseObject(areaStr);
            JSONArray areaJsonArray = areaJson.getJSONArray("data");
            List<HttpArea> areaList = areaJsonArray.toJavaList(HttpArea.class);
            for (int i = 0; i < areaList.size(); i++) {
                MtArea mtArea = new MtArea();
                mtArea.setParentCode(cityCode);
                mtArea.setName(areaList.get(i).getName());
                mtArea.setCode(areaList.get(i).getId());
                mtArea.setSgmAresParentCode(sgmAresParentCode);
                mtArea.setSgmAreaCode(sgmIdKit.nextMid().toString());
                mtAreaList.add(mtArea);
                System.out.println();
            }
        }

        //创建Excel文件薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表sheeet
        Sheet sheet = workbook.createSheet();
        //创建第一行
        Row row= sheet.createRow(0);
        String[] title={"美团区域名称","美团区域code","美团区域parentCode","sgmCode","sgmParentCode"};
        Cell cell = null;
        for (int i= 0; i < title.length; i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据
        for (int i = 0; i < mtAreaList.size(); i++) {
            //创建第i+1行
            Row nextrow=sheet.createRow(i+1);

            //第i+1行第一个单元格：美团区域名称
            Cell cell1 = nextrow.createCell(0);
            cell1.setCellValue(mtAreaList.get(i).getName());

            //第i+1行第二个单元格：美团区域code
            cell1=nextrow.createCell(1);
            cell1.setCellValue(mtAreaList.get(i).getCode());

            //第i+1行第三个单元格：美团区域parentCode
            cell1=nextrow.createCell(2);
            cell1.setCellValue(mtAreaList.get(i).getParentCode());

            //sgmCode
            cell1=nextrow.createCell(3);
            cell1.setCellValue(mtAreaList.get(i).getSgmAreaCode());

            //sgmParentCode
            cell1=nextrow.createCell(4);
            cell1.setCellValue(mtAreaList.get(i).getSgmAresParentCode());
        }

        //创建一个文件
        File file=new File("C:/Users/opentext/Desktop/mt_area_3.xlsx");
        file.createNewFile();
        FileOutputStream stream = FileUtils.openOutputStream(file);
        workbook.write(stream);
        stream.close();
        workbook.close();
        System.out.println("区域excel创建完成。。。。。。。。。。。。。。");

    }


    Map<Integer,MtCommerceArea> areaMap(Integer id){

        SgmIdKit sgmIdKit = new SgmIdKit("0000000000", 4, 3, 3);

        //通过城市的id查询所有的商圈
        String commerceAreaDataString = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/area?cityid={id}", String.class, id);
        JSONObject commerceAreaData = JSON.parseObject(commerceAreaDataString);
        JSONArray commerceAreaDateJsonArray = commerceAreaData.getJSONArray("data");
        //有的城市存在没有商圈的情况(港澳台和一些偏僻的37个地区无商圈)
        if (ObjectUtils.isEmpty(commerceAreaDateJsonArray)){
            return Collections.emptyMap();
        }

        Map<Integer,MtCommerceArea> ret = new HashMap<>();

        for (int i = 0; i < commerceAreaDateJsonArray.size(); i++) {
            Object commerceAreaDateJsonObj = commerceAreaDateJsonArray.get(i);
            JSONObject commerceAreaJsonObj = JSON.parseObject(commerceAreaDateJsonObj.toString());
            //获取区域code
            int areaCode = commerceAreaJsonObj.getIntValue("id");
            String areaName = commerceAreaJsonObj.getString("name");


            List<Area> areas = new ArrayList<>();
            JSONArray commerceAreaJsonArray = JSON.parseArray(commerceAreaJsonObj.get("area").toString());
            for (int j = 0; j < commerceAreaJsonArray.size(); j++) {
                String commerceAreaStr = commerceAreaJsonArray.get(j).toString();
                JSONObject commerceAreaJson = JSON.parseObject(commerceAreaStr);
                //获取商圈名称和code
                int commerceAreaCode = commerceAreaJson.getIntValue("id");
                String commerceAreaName = commerceAreaJson.getString("name");
                Area area = new Area();
                //商圈code
                area.setCommerceAreaCode(String.valueOf(commerceAreaCode));
                //商圈自生成code
                area.setSgmCommerceAreaCode(sgmIdKit.nextSub().toString());
                area.setName(commerceAreaName);
                areas.add(area);
            }

            MtCommerceArea mtCommerceArea = new MtCommerceArea();
            //区域父code
            mtCommerceArea.setAreaCode(String.valueOf(areaCode));
            //sgm自生成code
            mtCommerceArea.setSgmAreaCode(sgmIdKit.nextMid().toString());
            mtCommerceArea.setName(areaName);
            mtCommerceArea.setAreas(areas);
            ret.put(areaCode,mtCommerceArea);
        }

        return ret;
    }


    /**
     * 创建美团商圈excel
     * @throws IOException
     */
    @Test
    public void createMtCommerceAreaExcel() throws IOException {
        //先获取所有城市的code
        Map<Integer, String> cityCodeList = new HashMap<>();
        SgmIdKit sgmIdKit = new SgmIdKit("0000000000", 4, 3, 3);

        //查询所有美团城市包含区域
        String mtDtoStr = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/city", String.class);
        JSONObject mtDtoJson = JSON.parseObject(mtDtoStr);
        JSONArray mtData = mtDtoJson.getJSONArray("data");
        List<MtDTO> cityList = mtData.toJavaList(MtDTO.class);
        //先获取所有城市的code，将城市code放入集合中
        for (MtDTO mtDTO : cityList) {
            cityCodeList.put(mtDTO.getId(), sgmIdKit.nextBig().toString());
        }

        //创建一个文件
        File file = new File("C:/Users/opentext/Desktop/mt_commerceArea_2.xlsx");
        file.createNewFile();
        FileOutputStream stream = FileUtils.openOutputStream(file);
        //创建Excel文件薄
        XSSFWorkbook workbook = new XSSFWorkbook();

        for (Map.Entry<Integer, String> e : cityCodeList.entrySet()) {

            //根据城市id查询所有的区域
            Integer cityCode = e.getKey();
            String areaDataString = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/district?cityid={id}", String.class, cityCode);
            JSONObject areaDataJson = JSON.parseObject(areaDataString);
            JSONArray dataJsonArray = areaDataJson.getJSONArray("data");
            if (ObjectUtils.isEmpty(dataJsonArray)) {
                continue;
            }

            SgmIdKit sgmIdKit2 = new SgmIdKit(e.getValue(), 4, 3, 3);
            String parentCode = sgmIdKit2.toString();
            Map<Integer,MtCommerceArea> areaMap = areaMap(cityCode);

            //所有区域信息
            List<HttpArea> httpAreas = new ArrayList<>();
            List<MtCommerceArea> mtCommerceAreas = new ArrayList<>();

            for (int i = 0; i < dataJsonArray.size(); i++) {
                Object o = dataJsonArray.get(i);
                JSONObject jsonObject = JSON.parseObject(o.toString());
                HttpArea httpArea = new HttpArea();
                int id = jsonObject.getIntValue("id");
                httpArea.setId(id);
                String name = jsonObject.getString("name");
                httpArea.setName(name);
                httpAreas.add(httpArea);
            }

            //创建工作表sheeet
            Sheet sheet = workbook.createSheet();
            //创建第一行
            Row row = sheet.createRow(0);
            String[] title = {"美团商圈名称", "美团商圈code", "美团商圈parentCode", "sgmCommerceAreaCode", "sgmCommerceAreaParentCode"};
            Cell cell = null;
            for (int i = 0; i < title.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
            }
            //追加数据
            int row_num=1;
            for (MtCommerceArea commerceArea: mtCommerceAreas) {
                for(Area area:commerceArea.getAreas()){
                    //创建第i+1行
                    Row nextrow = sheet.createRow(row_num++);

                    //美团商圈名称
                    Cell cell1 = nextrow.createCell(0);
                    cell1.setCellValue(area.getName());

                    //美团商圈code
                    cell1 = nextrow.createCell(1);
                    cell1.setCellValue(area.getCommerceAreaCode());

                    //区域父code
                    cell1 = nextrow.createCell(2);
                    cell1.setCellValue(commerceArea.getAreaCode());

                    //商圈自生成code
                    cell1 = nextrow.createCell(3);
                    cell1.setCellValue(area.getSgmCommerceAreaCode());

                    //sgm自生成code
                    cell1 = nextrow.createCell(4);
                    cell1.setCellValue(commerceArea.getSgmAreaCode());
                }

            }
        }
        workbook.write(stream);
        stream.close();
        workbook.close();
        System.out.println("商圈excel创建完成。。。。。。。。。。。。。。");
    }



    /**
     * 批量导入数据到数据库
     */
    @Autowired
    SgmCityServiceImpl sgmCityServiceImpl;

    @Test
    @Transactional(rollbackFor = Exception.class)
    public Boolean importExcelToOracle() throws IOException {

        List<SgmCity> sgmCityList = new ArrayList<>();

        //读取excel中的数据
        //创建Excel，读取文件内容
        File file = new File("C:/Users/opentext/Desktop/mt_city_1.xlsx");
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

                // 美团城市名称	美团城市code	美团城市parentCode	美团城市名称拼音	sgmParentCode	sgmCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));

//                row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
//                Cell cell = row.getCell(j);
//                String value = cell.getStringCellValue();

                list.add(value);
            }
            //将数据插入数据库中
            sgmCity.setName(list.get(0));
            sgmCity.setCode(list.get(1));
            sgmCity.setParentCode(list.get(2));
            sgmCity.setPinyin(list.get(3));
            String initial = list.get(3).substring(0, 1).toUpperCase();
            sgmCity.setCityInitial(initial);
            sgmCityList.add(sgmCity);
            System.out.println();
        }
        //MybatisPlus批处理插入
        boolean bool = sgmCityServiceImpl.saveBatch(sgmCityList);
        fileInputStream.close();
        return bool;
    }


    List<MtNewCity> getAllArea() throws IOException {

        List<MtNewCity> mtNewCities = new ArrayList<>();

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
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            MtNewCity mtNewCity = new MtNewCity();
            for (int j = 0; j < lastCellNum; j++) {
                //美团区域名称	美团区域code	 美团区域parentCode	 sgmCode	sgmParentCode
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                switch (j) {
                    case 0:
                        mtNewCity.setAreaName(value);
                        break;
                    case 1:
                        mtNewCity.setAreaCode(Integer.valueOf(value));
                        break;
                    case 2:
                        mtNewCity.setCityCode(Integer.valueOf(value));
                        break;
                    case 3:
                        mtNewCity.setSgmAreaCode(value);
                        break;
                    case 4:
                        mtNewCity.setSgmCityCode(value);
                        break;
                }
            }
            mtNewCities.add(mtNewCity);
        }
        fileInputStream.close();
        workbook.close();
        return mtNewCities;
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void AreaCommerce()  throws IOException{

        //excel读取所有的区域数据
        List<MtNewCity> allArea = getAllArea();

        Set<Integer> cityCodeSet = new HashSet<>();
        for (MtNewCity mtNewCity : allArea) {
            int cityCode = mtNewCity.getCityCode();
            cityCodeSet.add(cityCode);
        }

        List<Commerce> commerceList = new ArrayList<>();
        for (Integer integer : cityCodeSet) {

            //查询美团商圈
            String forObject = this.restTemplate.getForObject("https://openapi.meituan.com/poiCode/area?cityid={id}", String.class, integer);
            JSONObject jsonObject = JSON.parseObject(forObject);
            JSONArray dataJson = jsonObject.getJSONArray("data");
            if (ObjectUtils.isEmpty(dataJson)){
                continue;
            }

            for (int i = 0; i < dataJson.size(); i++) {
                String commerceStr = dataJson.get(i).toString();
                JSONObject commerceJson = JSON.parseObject(commerceStr);

                int id = commerceJson.getIntValue("id");
                String name = commerceJson.getString("name");
                JSONArray area = commerceJson.getJSONArray("area");
                List<CommerceArea> commerceAreas = area.toJavaList(CommerceArea.class);

                Commerce commerce = new Commerce();
                commerce.setId(id);
                commerce.setName(name);
                commerce.setCommerceAreas(commerceAreas);

                commerceList.add(commerce);
            }
        }

        for (MtNewCity mtNewCity : allArea) {

            int areaCode = mtNewCity.getAreaCode();

            for (Commerce commerce : commerceList) {
                int id = commerce.getId();
                //如果区域code不同
                if (areaCode != id){
                    continue;
                }
                //区域code相同
                //自生成sgm区域code
                String sgmAreaCode = mtNewCity.getSgmAreaCode();//0001001000
                SgmIdKit sgmIdKit = new SgmIdKit(sgmAreaCode, 4, 3, 3);

                List<CommerceArea> commerceAreas = commerce.getCommerceAreas();
                for (CommerceArea commerceArea : commerceAreas) {
                    commerceArea.setSgmAreaCode(sgmAreaCode);
                    commerceArea.setSgmCommerceCode(sgmIdKit.nextSub().toString());
                }
                break;
            }
        }

        List<MtNewCommerce> mtNewCommerces = new ArrayList<>();
        for (Commerce commerce : commerceList) {

            for (CommerceArea commerceArea : commerce.getCommerceAreas()) {

                MtNewCommerce mtNewCommerce = new MtNewCommerce();
                mtNewCommerce.setCommerceName(commerceArea.getName());
                mtNewCommerce.setAreaCode(String.valueOf(commerce.getId()));
                mtNewCommerce.setCommerceCode(String.valueOf(commerceArea.getId()));
                mtNewCommerce.setSgmAreaCode(commerceArea.getSgmAreaCode());
                mtNewCommerce.setSgmCommerceCode(commerceArea.getSgmCommerceCode());
                mtNewCommerces.add(mtNewCommerce);
            }
        }

        //创建Excel文件薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表sheet
        Sheet sheet = workbook.createSheet();
        //创建第一行
        Row row= sheet.createRow(0);
        String[] title={"商圈名称","商圈code","区域code","sgmCommerceCode","sgmAreaCode"};
        Cell cell = null;
        for (int i= 0; i < title.length; i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据

        for (int i = 0; i < mtNewCommerces.size(); i++) {

            //创建第i行
            Row nextrow=sheet.createRow(i+1);

            //商圈名称
            Cell cell2=nextrow.createCell(0);
            cell2.setCellValue(mtNewCommerces.get(i).getCommerceName());

            //商圈code
            cell2=nextrow.createCell(1);
            cell2.setCellValue(mtNewCommerces.get(i).getCommerceCode());

            //区域code
            cell2=nextrow.createCell(2);
            cell2.setCellValue(mtNewCommerces.get(i).getAreaCode());

            //sgmCommerceCode
            cell2=nextrow.createCell(3);
            cell2.setCellValue(mtNewCommerces.get(i).getSgmCommerceCode());

            //sgmAreaCode
            cell2=nextrow.createCell(4);
            cell2.setCellValue(mtNewCommerces.get(i).getSgmAreaCode());
        }

        //创建一个文件
        File file=new File("C:/Users/opentext/Desktop/mt_commerce_2.xlsx");
        file.createNewFile();
        FileOutputStream stream = FileUtils.openOutputStream(file);
        workbook.write(stream);
        stream.close();
        workbook.close();
        System.out.println("区域excel创建完成。。。。。。。。。。。。。。");
    }


    @Test
    public void createBdCityExcel() throws IOException {

        Set<BaiDuOriginal> baiDuOriginals = new HashSet<>();

        //创建Excel，读取文件内容
        File file = new File("C:/Users/opentext/Desktop/POI/百度/百度城市数据/百度city.xlsx");
        XSSFWorkbook readWorkbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = readWorkbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();

            List<String> list = new ArrayList<>();

            for (int j = 0; j < lastCellNum; j++) {
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(row.getCell(j));
                list.add(value);
            }
            if (!CollectionUtils.isEmpty(list)) {

                for (int j = 0; j < list.size(); j++) {
                    BaiDuOriginal baiDuOriginal = new BaiDuOriginal();
                    baiDuOriginal.setCityCode(list.get(0));
                    baiDuOriginal.setCityName(list.get(1));
                    baiDuOriginal.setAreaCode(list.get(2));
                    baiDuOriginal.setAreaName(list.get(3));
                    baiDuOriginals.add(baiDuOriginal);
                }
            }
        }

        HashMap<BaiDuCityMap, List<BaiDuOriginalArea>> baiDuCityMapListHashMap = new HashMap<>();

        for (BaiDuOriginal baiDuOriginal : baiDuOriginals) {

            BaiDuOriginalArea baiDuOriginalArea = new BaiDuOriginalArea();
            baiDuOriginalArea.setAreaCode(baiDuOriginal.getAreaCode());
            baiDuOriginalArea.setAreaName(baiDuOriginal.getAreaName());

            BaiDuCityMap baiDuCityMap = new BaiDuCityMap();
            baiDuCityMap.setCityCode(baiDuOriginal.getCityCode());
            baiDuCityMap.setCityName(baiDuOriginal.getCityName());

            Set<BaiDuCityMap> baiDuCityMaps = baiDuCityMapListHashMap.keySet();
            if (!baiDuCityMaps.contains(baiDuCityMap)) {
                baiDuCityMapListHashMap.put(baiDuCityMap, Lists.newArrayList(baiDuOriginalArea));
            } else {
                baiDuCityMapListHashMap.get(baiDuCityMap).add(baiDuOriginalArea);
            }
        }


        //创建Excel文件薄
        XSSFWorkbook writeWorkbook = new XSSFWorkbook();
        //创建工作表sheet
        Sheet writeSheet = writeWorkbook.createSheet();
        //创建第一行
        Row row = writeSheet.createRow(0);
        String[] title = {"百度城市name","百度城市code","百度城市parentCode"};
        Cell cell = null;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据

        int i = 0;

        for (Map.Entry<BaiDuCityMap, List<BaiDuOriginalArea>> baiDuCityMapListEntry : baiDuCityMapListHashMap.entrySet()) {
//            System.out.println(baiDuCityMapListEntry.getKey().toString()+"------"+baiDuCityMapListEntry.getValue().toString());
            BaiDuCityMap baiDuCityMap = baiDuCityMapListEntry.getKey();
            List<BaiDuOriginalArea> baiDuOriginalAreas = baiDuCityMapListEntry.getValue();


            //生成百度城市数据
          if (i < baiDuCityMapListHashMap.entrySet().size()){

                i=i+1;
                //创建第i行
                Row nextRow = writeSheet.createRow(i);
                //名称
                Cell cell2 = nextRow.createCell(0);
                cell2.setCellValue(baiDuCityMap.getCityName());
                //code
                cell2 = nextRow.createCell(1);
                cell2.setCellValue(baiDuCityMap.getCityCode());
                //parentCode
                cell2 = nextRow.createCell(2);
                cell2.setCellValue(baiDuCityMap.getCityCode());
            }


            //生成百度区域数据
            if (baiDuCityMapListHashMap.entrySet().iterator().hasNext()){

                for (int j = 0; j < baiDuOriginalAreas.size(); j++) {

                    i=i+1;

                    //创建第i行
                    Row nextRow = writeSheet.createRow(i);
                    //名称
                    Cell cell2 = nextRow.createCell(0);
                    cell2.setCellValue(baiDuOriginalAreas.get(j).getAreaName());
                    //code
                    cell2 = nextRow.createCell(1);
                    cell2.setCellValue(baiDuOriginalAreas.get(j).getAreaCode());
                    //parentCode
                    cell2 = nextRow.createCell(2);
                    cell2.setCellValue(baiDuCityMap.getCityCode());
                }
            }


        }
        //创建一个文件
        File writeFile = new File("C:/Users/opentext/Desktop/baiDu_area_03.xlsx");
        writeFile.createNewFile();
        FileOutputStream stream = FileUtils.openOutputStream(writeFile);
        writeWorkbook.write(stream);
        stream.close();
        readWorkbook.close();
        writeWorkbook.close();
        System.out.println("百度城市excel创建完成。。。。。。。。。。。。。。");
    }

    @Test
    public void createBdClassExcel() throws IOException {

        //创建Excel，读取文件内容
        File readFile = new File("C:/Users/opentext/Desktop/POI/百度/百度tag.xlsx");
        XSSFWorkbook readWorkbook = new XSSFWorkbook(FileUtils.openInputStream(readFile));
        //两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet readSheet = readWorkbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = readSheet.getLastRowNum();

        Map<BaiDuOneModel, List<BaiDuTwoModel>> integerListHashMap = new HashMap<>();

        for (int i = 1; i <= lastRowNum; i++) {

            List<BaiDuTwoModel> baiDuTwoModels = new ArrayList<>();
            Row row = readSheet.getRow(i);

            Cell cell1 = row.getCell(0);
            String value1 = cell1.getStringCellValue();
//            System.out.println(value1);//美食

            BigDecimal decimal = new BigDecimal(i*100);
            String parent = decimal.toString();
            if (Integer.valueOf(i)<10){
                parent = "0" + parent;
            }

            Cell cell = row.getCell(1);
            String value = cell.getStringCellValue();
            List<String> result = Arrays.asList(value.split(","));

            for (int k = 0; k < result.size(); k++) {
                BigDecimal decimal1 = new BigDecimal((i)*100+k+1);
                String s2 = decimal1.toString();
                BaiDuTwoModel baiDuTwoModel = new BaiDuTwoModel();
                baiDuTwoModel.setType(i>=10?s2:"0"+s2);
                baiDuTwoModel.setName(result.get(k));
                baiDuTwoModels.add(baiDuTwoModel);
            }
            BaiDuOneModel baiDuOneModel = new BaiDuOneModel();
            baiDuOneModel.setName(value1);
            baiDuOneModel.setType(parent);
            integerListHashMap.put(baiDuOneModel, baiDuTwoModels);
        }

        //创建Excel文件薄
        XSSFWorkbook writeWorkbook = new XSSFWorkbook();
        //创建工作表sheet
        Sheet writeSheet = writeWorkbook.createSheet();
        //创建第一行
        Row row = writeSheet.createRow(0);
        String[] title = {"百度一级分类name","百度一级分类type","百度一级分类parentType"};
        Cell cell = null;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }

        int i = 0;
        for (Map.Entry<BaiDuOneModel, List<BaiDuTwoModel>> entry : integerListHashMap.entrySet()) {

//            System.out.println(entry.getKey().toString());
//            System.out.println(entry.getKey().toString()+"："+entry.getValue().toString());

            for (BaiDuTwoModel baiDuTwoModel : entry.getValue()) {
                i=i+1;
                //创建第i行
                Row nextRow = writeSheet.createRow(i);
                //名称
                Cell cell2 = nextRow.createCell(0);
                cell2.setCellValue(baiDuTwoModel.getName());
                //type
                cell2 = nextRow.createCell(1);
                cell2.setCellValue(baiDuTwoModel.getType());
                //parentType
                cell2 = nextRow.createCell(2);
                cell2.setCellValue(entry.getKey().getType());
            }
        }

        //创建一个文件
        File writeFile = new File("C:/Users/opentext/Desktop/baiDu_class_02.xlsx");
        writeFile.createNewFile();
        FileOutputStream stream = FileUtils.openOutputStream(writeFile);
        writeWorkbook.write(stream);
        stream.close();
        readWorkbook.close();
        writeWorkbook.close();
        System.out.println("百度分类excel创建完成。。。。。。。。。。。。。。");
    }

}
