package com.opentext.poi.model;


import lombok.Data;

import java.util.List;

@Data
public class MtCommerceArea {


    private String name;

    //区域父code
    private String areaCode;

    //sgm自生成code
    private String sgmAreaCode;

    List<Area> areas;

}
