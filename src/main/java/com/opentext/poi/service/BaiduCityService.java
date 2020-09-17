package com.opentext.poi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.opentext.poi.entity.BaiduCity;

/**
 * <p>
 * 百度城市表 服务类
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
public interface BaiduCityService extends IService<BaiduCity> {

    Boolean importExcelToBDCity() throws Exception;

    BaiduCity selectBDCityByCode(String code);
}
