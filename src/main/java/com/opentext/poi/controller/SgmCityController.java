package com.opentext.poi.controller;


import com.opentext.poi.service.SgmCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 保存SGM自定义的城市级别信息，用户使用商圈信息，按照美团的标准建立，包含省，地级市，县级市，商圈。 前端控制器
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@RestController
public class SgmCityController {

    @Autowired
    private SgmCityService sgmCityService;

    @GetMapping("/importExcelForSgmCity")
    private Boolean importExcelForSgmCity() throws Exception {
        Boolean bool = sgmCityService.importExcelForSgmCity();
        return bool;
    }


    @GetMapping("/importExcelForSgmArea")
    private Boolean importExcelForSgmArea() throws Exception {
        Boolean bool = sgmCityService.importExcelForSgmArea();
        return bool;
    }


    @GetMapping("/importExcelForSgmCommerceArea")
    private Boolean importExcelForSgmCommerceArea() throws Exception {
        Boolean bool = sgmCityService.importExcelForSgmCommerceArea();
        return bool;
    }


    /**
     * 根据高德城市将sgm城市分级，分城市，城市找区域，区域找商圈
     */
    @GetMapping("/findCity")
    private Boolean findCity() throws Exception {
        Boolean bool = sgmCityService.findCity();
        return bool;
    }

    @GetMapping(value = "/addAreaInitial")
    public Integer addAreaInitial(){

        int count = sgmCityService.addAreaInitial();
        return count;
    }


    @GetMapping(value = "/selectSgmIdNull")
    public Integer selectSgmIdNull(){

        int count = sgmCityService.selectSgmIdNull();
        return count;
    }


}

