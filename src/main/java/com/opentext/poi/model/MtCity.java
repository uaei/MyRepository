package com.opentext.poi.model;

import lombok.Data;

import java.util.List;

@Data
public class MtCity {

    private int id;

    private String name;

    List<HttpArea> httpAreas;

}
