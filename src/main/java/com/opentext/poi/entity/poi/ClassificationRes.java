package com.opentext.poi.entity.poi;

import com.opentext.poi.common.annotation.Param;


/**
 * poi分类接口返回数据封装接口
 */


public class ClassificationRes {

    @Param(description = "分类id，唯一标识")
    private String id;

    @Param(description = "名称")
    private String name;

    @Param(description = "分类英文名称")
    private String enName;

    @Param(description = "分类类型code")
    private String type;

    @Param(description = "分类类型父code")
    private String parentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

}