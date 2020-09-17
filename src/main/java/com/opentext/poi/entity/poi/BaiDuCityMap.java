package com.opentext.poi.entity.poi;

import java.util.Objects;

/**
 * @Author GuYaWei
 * @created 2020/8/19 17:01
 * @description
 */
public class BaiDuCityMap {

    private String cityCode;

    private String cityName;

    public BaiDuCityMap() {
    }

    public BaiDuCityMap(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaiDuCityMap that = (BaiDuCityMap) o;
        return Objects.equals(cityCode, that.cityCode) && Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cityCode, cityName);
    }

    @Override
    public String toString() {
        return "BaiDuCityMap{" + "cityCode='" + cityCode + '\'' + ", cityName='" + cityName + '\'' + '}';
    }
}
