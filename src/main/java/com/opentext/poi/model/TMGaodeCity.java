package com.opentext.poi.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
//@Table(name = "TM_GAODE_CITY")
public class TMGaodeCity {
    /**
     * 主键
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String gdCityId;

    /**
     * 外键关联，城市id
     */
    private String sgmCityId;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 父代码
     */
    private String parentCode;

    /**
     * 状态（A--activate：激活，I--inactivate，D--deleted）
     */
    private String status;

    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

}
