package com.opentext.poi.entity.poi;

import java.util.List;
import java.util.Objects;

/**
 * @Author GuYaWei
 * @created 2020/8/19 17:01
 * @description
 */
public class BaiDuOriginalCity {

    private String cityCode;

    private String cityName;

    private List<BaiDuOriginalArea> baiDuOriginalAreaList;

    public BaiDuOriginalCity() {
    }

    public BaiDuOriginalCity(String cityCode, String cityName, List<BaiDuOriginalArea> baiDuOriginalAreaList) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.baiDuOriginalAreaList = baiDuOriginalAreaList;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<BaiDuOriginalArea> getBaiDuOriginalAreaList() {
        return baiDuOriginalAreaList;
    }

    public void setBaiDuOriginalAreaList(List<BaiDuOriginalArea> baiDuOriginalAreaList) {
        this.baiDuOriginalAreaList = baiDuOriginalAreaList;
    }

    @Override
    public String toString() {
        return "BaiDuCity{" + "cityCode='" + cityCode + '\'' + ", cityName='" + cityName + '\'' + ", baiDuAreaList=" + baiDuOriginalAreaList + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaiDuOriginalCity that = (BaiDuOriginalCity) o;
        return Objects.equals(cityCode, that.cityCode) && Objects.equals(cityName, that.cityName) && Objects.equals(baiDuOriginalAreaList, that.baiDuOriginalAreaList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cityCode, cityName, baiDuOriginalAreaList);
    }
}
