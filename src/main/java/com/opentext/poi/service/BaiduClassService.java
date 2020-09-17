package com.opentext.poi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.opentext.poi.entity.BaiduClass;

/**
 * <p>
 * 百度分类表 服务类
 * </p>
 *
 * @author gyw
 * @since 2020-08-19
 */
public interface BaiduClassService extends IService<BaiduClass> {

    Boolean importExcelToBDClass() throws Exception;
}
