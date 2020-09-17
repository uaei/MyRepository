package com.opentext.poi.entity.poi;

import com.opentext.poi.common.annotation.Param;


/**
 * 美食分类显示 团购信息
 */


public class GrouponDto {

    @Param(description = "美团用户")
    private String name;

    @Param(description = "图片")
    private String picture;

    @Param(description = "描述")
    private String description;

    @Param(description = "团购金额")
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}