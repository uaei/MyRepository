package com.opentext.poi.entity.poi;

import java.util.Objects;

/**
 * @Author GuYaWei
 * @created 2020/8/20 10:18
 * @description
 */
public class BaiDuOriginal {

    private String cityCode;

    private String cityName;

    private String areaCode;

    private String areaName;

    public BaiDuOriginal() {
    }

    public BaiDuOriginal(String cityCode, String cityName, String areaCode, String areaName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.areaCode = areaCode;
        this.areaName = areaName;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaiDuOriginal that = (BaiDuOriginal) o;
        return Objects.equals(cityCode, that.cityCode) && Objects.equals(cityName, that.cityName) && Objects.equals(areaCode, that.areaCode) && Objects.equals(areaName, that.areaName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cityCode, cityName, areaCode, areaName);
    }

    @Override
    public String toString() {
        return "BaiDuOriginal{" + "cityCode='" + cityCode + '\'' + ", cityName='" + cityName + '\'' + ", areaCode='" + areaCode + '\'' + ", areaName='" + areaName + '\'' + '}';
    }

}
