package com.opentext.poi.mapper;

import com.opentext.poi.entity.GaodeCity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 保存高德城市级别数据，包含省，地级市，县级市 Mapper 接口
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
public interface GaodeCityMapper extends BaseMapper<GaodeCity> {

    @Select("select * from TM_GAODE_CITY where name like '%市' and CODE = PARENT_CODE and SGM_CITY_ID != '--NULL--' ")
    List<GaodeCity> selectGaoDeCity();
}
