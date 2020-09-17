package com.opentext.poi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 保存SGM自定义的城市级别信息，用户使用商圈信息，按照美团的标准建立，包含省，地级市，县级市，商圈。
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TM_SGM_CITY")
public class SgmCity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("SGM_CITY_ID")
    private String sgmCityId;

    /**
     * SGM城市名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 城市代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 城市父代码
     */
    @TableField("PARENT_CODE")
    private String parentCode;

    /**
     * 状态（A--activate，I--inactivate，D--deleted）
     */
    @TableField("STATUS")
    private String status;

    /**
     * 拼音
     */
    @TableField("PINYIN")
    private String pinyin;

    /**
     * 城市首字母
     */
    @TableField("CITY_INITIAL")
    private String cityInitial;


    public String getSgmCityId() {
        return sgmCityId;
    }

    public void setSgmCityId(String sgmCityId) {
        this.sgmCityId = sgmCityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCityInitial() {
        return cityInitial;
    }

    public void setCityInitial(String cityInitial) {
        this.cityInitial = cityInitial;
    }

    public static final String SGM_CITY_ID = "SGM_CITY_ID";

    public static final String NAME = "NAME";

    public static final String CODE = "CODE";

    public static final String PARENT_CODE = "PARENT_CODE";

    public static final String STATUS = "STATUS";

    public static final String PINYIN = "PINYIN";

    public static final String CITY_INITIAL = "CITY_INITIAL";

}
