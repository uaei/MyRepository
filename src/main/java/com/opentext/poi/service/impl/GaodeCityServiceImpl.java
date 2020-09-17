package com.opentext.poi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opentext.poi.entity.GaodeCity;
import com.opentext.poi.mapper.GaodeCityMapper;
import com.opentext.poi.service.GaodeCityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 保存高德城市级别数据，包含省，地级市，县级市 服务实现类
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
@Service
public class GaodeCityServiceImpl extends ServiceImpl<GaodeCityMapper, GaodeCity> implements GaodeCityService {


}
