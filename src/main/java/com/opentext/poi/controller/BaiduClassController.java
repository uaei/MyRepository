package com.opentext.poi.controller;


import com.opentext.poi.service.BaiduClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 百度分类表 前端控制器
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
@RestController
//@RequestMapping("/baidu-class")
public class BaiduClassController {

    @Autowired
    private BaiduClassService baiduClassService;

    @GetMapping("/importExcelToBDClass")
    private Boolean importExcelToBDClass() throws Exception {
        Boolean bool = baiduClassService.importExcelToBDClass();
        return bool;
    }

}

