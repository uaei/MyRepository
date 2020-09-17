package com.opentext.poi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 百度城市表
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TM_BAIDU_CITY")
public class BaiduCity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId("BD_CITY_ID")
    private String bdCityId;

    /**
     * 外键关联，sgm城市id
     */
    @TableField("SGM_CITY_ID")
    private String sgmCityId;

    /**
     * 城市名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 城市代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 父城市代码
     */
    @TableField("PARENT_CODE")
    private String parentCode;

    /**
     * 状态（A--acitvate，I--inactivate，D--deleted）
     */
    @TableField("STATUS")
    private String status;


    public static final String BD_CITY_ID = "BD_CITY_ID";

    public static final String SGM_CITY_ID = "SGM_CITY_ID";

    public static final String NAME = "NAME";

    public static final String CODE = "CODE";

    public static final String PARENT_CODE = "PARENT_CODE";

    public static final String STATUS = "STATUS";

}
