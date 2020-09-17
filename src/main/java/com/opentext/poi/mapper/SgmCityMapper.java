package com.opentext.poi.mapper;

import com.opentext.poi.entity.SgmCity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 保存SGM自定义的城市级别信息，用户使用商圈信息，按照美团的标准建立，包含省，地级市，县级市，商圈。 Mapper 接口
 * </p>
 *
 * @author gyw
 * @since 2020-06-03
 */
public interface SgmCityMapper extends BaseMapper<SgmCity> {

    @Select("select * from TM_SGM_CITY t where t.PINYIN is not NULL")
    List<SgmCity> selectSgmCity();
}
