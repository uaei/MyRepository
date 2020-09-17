package com.opentext.poi.controller;


import com.opentext.poi.service.RelationCorrelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationCorrelate {


    @Autowired
    private RelationCorrelateService  relationCorrelateService;

    @GetMapping(value = "/correlateDataMt")
    public Integer correlateDataMt(){
        Integer count = relationCorrelateService.correlateDataMt();
        return count;
    }


    /**
     * 映射高德城市sgm_city_id
     * @return
     */
    @GetMapping(value = "/correlateDataMtCity")
    public Integer correlateDataMtCity(){
        Integer count = relationCorrelateService.correlateDataMtCity();
        return count;
    }


    /**
     * 映射高德区域
     * @return
     */
    @GetMapping(value = "/correlateDataMtArea")
    public Integer correlateDataMtArea(){
        Integer count = relationCorrelateService.correlateDataMtArea();
        return count;
    }

}
