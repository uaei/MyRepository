package com.opentext.poi.entity.poi;

import java.util.Objects;

/**
 * @Author GuYaWei
 * @created 2020/8/19 16:59
 * @description
 */
public class BaiDuOriginalArea {

    private String areaCode;

    private String areaName;

    public BaiDuOriginalArea() {
    }

    public BaiDuOriginalArea(String areaCode, String areaName) {
        this.areaCode = areaCode;
        this.areaName = areaName;
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
    public String toString() {
        return "BaiDuArea{" + "areaCode='" + areaCode + '\'' + ", areaName='" + areaName + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        boolean b = o == null;
        boolean c = getClass() != o.getClass();

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaiDuOriginalArea that = (BaiDuOriginalArea) o;

        boolean f = areaCode == that.areaCode;
        boolean g = areaName == that.areaName;

        return Objects.equals(areaCode, that.areaCode) && Objects.equals(areaName, that.areaName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(areaCode, areaName);
    }

    public static void main(String[] args) {
        BaiDuOriginalArea baiDuOriginalArea = new BaiDuOriginalArea();

        boolean equals = baiDuOriginalArea.equals(new BaiDuOriginalArea("",""));

        System.out.println(equals);
    }

}
