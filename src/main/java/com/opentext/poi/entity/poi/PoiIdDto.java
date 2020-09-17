package com.opentext.poi.entity.poi;

import com.opentext.poi.common.annotation.Param;


public class PoiIdDto {


    @Param(description = "用户搜索出的poi值")
    private String poiId;

    @Param(description = "标识该poi来源，md为美团，gd为高德")
    private String poiType;


    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }
}