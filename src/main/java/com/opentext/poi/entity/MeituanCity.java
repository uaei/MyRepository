package com.opentext.poi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 保存美团城市级别信息，最低到包含省，地级市，县级市，商圈
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TM_MEITUAN_CITY")
public class MeituanCity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("MT_CITY_ID")
    private String mtCityId;

    /**
     * 外键关联 sgm city id
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




    public static final String MT_CITY_ID = "MT_CITY_ID";

    public static final String SGM_CITY_ID = "SGM_CITY_ID";

    public static final String NAME = "NAME";

    public static final String CODE = "CODE";

    public static final String PARENT_CODE = "PARENT_CODE";

    public static final String STATUS = "STATUS";

    public static final String PINYIN = "PINYIN";

}
