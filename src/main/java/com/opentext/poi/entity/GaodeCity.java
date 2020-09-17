package com.opentext.poi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 保存高德城市级别数据，包含省，地级市，县级市
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TM_GAODE_CITY")
public class GaodeCity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("GD_CITY_ID")
    private String gdCityId;

    /**
     * 外键关联，城市id
     */
    @TableField("SGM_CITY_ID")
    private String sgmCityId;

    /**
     * 名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 父代码
     */
    @TableField("PARENT_CODE")
    private String parentCode;

    /**
     * 状态（A--acitvate，I--inactivate，D--deleted）
     */
    @TableField("STATUS")
    private String status;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGdCityId() {
        return gdCityId;
    }

    public void setGdCityId(String gdCityId) {
        this.gdCityId = gdCityId;
    }

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



    public static final String GD_CITY_ID = "GD_CITY_ID";

    public static final String SGM_CITY_ID = "SGM_CITY_ID";

    public static final String NAME = "NAME";

    public static final String CODE = "CODE";

    public static final String PARENT_CODE = "PARENT_CODE";

    public static final String STATUS = "STATUS";

}
