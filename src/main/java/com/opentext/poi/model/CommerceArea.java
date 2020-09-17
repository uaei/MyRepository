package com.opentext.poi.model;

import lombok.Data;

/**
 * 商圈实体类
 */
@Data
public class CommerceArea {

    //商圈code
    private int id;

    //商圈名称
    private String name;

    private String sgmAreaCode;

    private String sgmCommerceCode;

}
