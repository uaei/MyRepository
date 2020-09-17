package com.opentext.poi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 百度分类表
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TM_BAIDU_CLASS")
public class BaiduClass extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId("BD_CLASS_ID")
    private String bdClassId;

    /**
     * 外键关联，sgm分类id
     */
    @TableField("SGM_CLASS_ID")
    private String sgmClassId;

    /**
     * 分类名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 分类代码
     */
    @TableField("TYPE")
    private String type;

    /**
     * 父分类代码
     */
    @TableField("PARENT_TYPE")
    private String parentType;

    /**
     * 状态（A--acitvate，I--inactivate，D--deleted）
     */
    @TableField("STATUS")
    private String status;


    public static final String BD_CLASS_ID = "BD_CLASS_ID";

    public static final String SGM_CLASS_ID = "SGM_CLASS_ID";

    public static final String NAME = "NAME";

    public static final String TYPE = "TYPE";

    public static final String PARENT_TYPE = "PARENT_TYPE";

    public static final String STATUS = "STATUS";

}
