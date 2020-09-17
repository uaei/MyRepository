package com.opentext.poi.controller;


import com.opentext.poi.entity.MeituanCity;
import com.opentext.poi.service.MeituanCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 保存美团城市级别信息，最低到包含省，地级市，县级市，商圈 前端控制器
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@RestController
public class MeituanCityController {


    @Autowired
    private MeituanCityService meituanCityService;

    @GetMapping(value = "helloMybatisPlus")
    public ResponseEntity<List<MeituanCity>> helloMybatisPuls(String name){
        List<MeituanCity> meituanCities = meituanCityService.selectMeituanCity(name);
        return new ResponseEntity<>(meituanCities, HttpStatus.OK);
    }

    @GetMapping("/importExcelToMtCity")
    private Boolean importExcelToMtCity() throws Exception {
        Boolean bool = meituanCityService.importExcelToMtCity();
        return bool;
    }

    @GetMapping("/importExcelToMtArea")
    private Boolean importExcelToMtArea() throws Exception {
        Boolean bool = meituanCityService.importExcelToMtArea();
        return bool;
    }

    @GetMapping("/importExcelToMtCommerceArea")
    private Boolean importExcelToMtCommerceArea() throws Exception {
        Boolean bool = meituanCityService.importExcelToMtCommerceArea();
        return bool;
    }


    /**
     * 二次映射高德区域
     * @return
     */
    @GetMapping(value = "/correlateDataGdArea")
    public Integer correlateDataMtArea(){
        Integer count = meituanCityService.correlateDataGdArea();
        return count;
    }

}

