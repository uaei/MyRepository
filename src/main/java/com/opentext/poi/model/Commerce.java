package com.opentext.poi.model;


import lombok.Data;

import java.util.List;

/**
 * 区域商圈类
 */
@Data
public class Commerce {

    //区域code
    private int id;

    //区域名称
    private String name;

    List<CommerceArea> commerceAreas;

}
