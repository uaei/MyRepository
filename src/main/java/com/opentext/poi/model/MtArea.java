package com.opentext.poi.model;

import lombok.Data;

@Data
public class MtArea {

    private String name;

    private int code;

    private int parentCode;

    private String sgmAreaCode;

    private String sgmAresParentCode;
}
