package com.opentext.poi.entity.poi;

import java.util.Objects;

/**
 * @Author GuYaWei
 * @created 2020/9/3 16:38
 * @description
 */
public class BaiDuOneModel {


    private String type;

    private String name;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaiDuOneModel that = (BaiDuOneModel) o;
        return Objects.equals(type, that.type) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        return "BaiDuOneModel{" + "type='" + type + '\'' + ", name='" + name + '\'' + '}';
    }
}
