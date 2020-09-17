package com.opentext.poi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.opentext.poi.entity.SgmCity;

import java.io.IOException;

/**
 * <p>
 * 保存SGM自定义的城市级别信息，用户使用商圈信息，按照美团的标准建立，包含省，地级市，县级市，商圈。 服务类
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
public interface SgmCityService extends IService<SgmCity> {

    Boolean importExcelForSgmCity() throws IOException;

    Boolean importExcelForSgmArea() throws IOException;

    Boolean importExcelForSgmCommerceArea() throws IOException;

    Boolean findCity();

    int addAreaInitial();

    int selectSgmIdNull();
}
