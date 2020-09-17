package com.opentext.poi.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
//@Table(name = "TM_MEITUAN_CITY")
public class MtMeituanCity implements Serializable{

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String mtCityId;

    //sgm外键
    private String sgmCityId;
    //城市名称
    private String name;
    //城市code
    private String code;
    //父城市code
    private String parentCode;
    //状态
    private String status;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

}
