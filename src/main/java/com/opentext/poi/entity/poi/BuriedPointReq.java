package com.opentext.poi.entity.poi;

import com.opentext.poi.common.annotation.Param;

import java.util.List;


/**
 * poi埋点收集结果集接口请求数据封装bean
 */

public class BuriedPointReq {

    @Param(description = "SGM用户唯一标识")
    private String sgmId;

    @Param(description = "用户搜索出的poi结果集，一批为用户前台显示")
    private List<PoiIdDto> poiIds;

    @Param(description = "标明该请求是为poi搜索结果集埋点的类型，值为poiSearch")
    private String operation;

    public String getSgmId() {
        return sgmId;
    }

    public void setSgmId(String sgmId) {
        this.sgmId = sgmId;
    }

    public List<PoiIdDto> getPoiIds() {
        return poiIds;
    }

    public void setPoiIds(List<PoiIdDto> poiIds) {
        this.poiIds = poiIds;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}