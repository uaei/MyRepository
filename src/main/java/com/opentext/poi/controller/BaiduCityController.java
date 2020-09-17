package com.opentext.poi.controller;


import com.opentext.poi.entity.BaiduCity;
import com.opentext.poi.exception.exceptionHandler2.MyException;
import com.opentext.poi.response.ResponseModel;
import com.opentext.poi.service.BaiduCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 百度城市表 前端控制器
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
@RestController
//@RequestMapping("/baidu-city")
public class BaiduCityController {

    @Autowired
    private BaiduCityService baiduCityService;

    @GetMapping("/importExcelToBDCity")
    private Boolean importExcelToBDCity() throws Exception {
        Boolean bool = baiduCityService.importExcelToBDCity();
        return bool;
    }


    @GetMapping("/selectBDCityByCode")
    private ResponseModel selectBDCityByCode(@RequestParam("code") String code) throws Exception {

        if (StringUtils.isEmpty(code)){
//            throw new Exception("Sam 错误");
            throw new MyException("101", "Sam 错误");
        }
        BaiduCity baiduCity = baiduCityService.selectBDCityByCode(code);
        return ResponseModel.ok(baiduCity);
    }

}

