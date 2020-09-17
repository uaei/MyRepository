package com.opentext.poi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.opentext.poi.entity.MeituanCity;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 保存美团城市级别信息，最低到包含省，地级市，县级市，商圈 服务类
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
public interface MeituanCityService extends IService<MeituanCity> {

    List<MeituanCity> selectMeituanCity(String name);

    Boolean importExcelToMtCity() throws IOException;

    Boolean importExcelToMtArea() throws IOException;

    Boolean importExcelToMtCommerceArea() throws IOException;

    Integer correlateDataGdArea();
}
