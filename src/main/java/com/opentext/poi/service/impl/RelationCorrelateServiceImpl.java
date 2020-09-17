package com.opentext.poi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.opentext.poi.entity.GaodeCity;
import com.opentext.poi.entity.MeituanCity;
import com.opentext.poi.entity.SgmCity;
import com.opentext.poi.mapper.GaodeCityMapper;
import com.opentext.poi.mapper.MeituanCityMapper;
import com.opentext.poi.mapper.SgmCityMapper;
import com.opentext.poi.service.RelationCorrelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RelationCorrelateServiceImpl implements RelationCorrelateService{


    @Autowired
    private SgmCityMapper sgmCityMapper;

    @Autowired
    private GaodeCityMapper gaodeCityMapper;

    @Autowired
    private MeituanCityMapper meituanCityMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer correlateDataMt() {

        int count = 0;

        QueryWrapper<SgmCity> sgmCityQueryWrapper = new QueryWrapper<>();
//        sgmCityQueryWrapper.and(wrapper -> wrapper.likeRight(SgmCity.CODE,"01").likeRight(SgmCity.CODE,"02"));
        sgmCityQueryWrapper.likeRight(SgmCity.CODE,"01").or().likeRight(SgmCity.CODE,"02");

        List<SgmCity> sgmCityList = sgmCityMapper.selectList(sgmCityQueryWrapper);

        for (SgmCity sgmCity : sgmCityList) {

            UpdateWrapper<GaodeCity> gaodeCityWrapper = new UpdateWrapper<>();
            gaodeCityWrapper.eq(GaodeCity.NAME,sgmCity.getName());
            List<GaodeCity> gaodeCitiyList = gaodeCityMapper.selectList(gaodeCityWrapper);

            if (ObjectUtils.isEmpty(gaodeCitiyList)){
                continue;
            }
            if (gaodeCitiyList.size()==1){
                GaodeCity gaodeCity = new GaodeCity();
                gaodeCity.setSgmCityId(sgmCity.getCode());
                count = gaodeCityMapper.update(gaodeCity,gaodeCityWrapper);
                count++;
                continue;
            }

            //根据sgm名称查高德名称查出多条数据时
            if (gaodeCitiyList.size()>1){

                QueryWrapper<MeituanCity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(MeituanCity.NAME,sgmCity.getName());
                List<MeituanCity> meituanCities = meituanCityMapper.selectList(queryWrapper);

                for (MeituanCity meituanCity : meituanCities) {

                    QueryWrapper<MeituanCity> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq(MeituanCity.CODE,meituanCity.getParentCode());
                    queryWrapper1.eq(MeituanCity.PARENT_CODE,"--NULL--");
                    MeituanCity meituanCity1 = meituanCityMapper.selectOne(queryWrapper1);

                    String meituanPname = meituanCity1.getName();

                    for (GaodeCity gaodeCity : gaodeCitiyList) {

                        UpdateWrapper<GaodeCity> gaodeCityUpdateWrapper = new UpdateWrapper<>();
                        gaodeCityUpdateWrapper.eq(GaodeCity.CODE,gaodeCity.getParentCode());
                        GaodeCity gaodeCity1 = gaodeCityMapper.selectOne(gaodeCityUpdateWrapper);

                        if (!gaodeCity1.getName().contains(meituanPname)){
                            continue;
                        }

                        gaodeCity.setSgmCityId(sgmCity.getCode());
                        count = gaodeCityMapper.updateById(gaodeCity);
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer correlateDataMtCity() {

        int count =0;

        //高德的所有市和区
        QueryWrapper<GaodeCity> gaodeCityQueryWrapper = new QueryWrapper<>();
        gaodeCityQueryWrapper.likeLeft(GaodeCity.NAME,"市");
//        gaodeCityQueryWrapper.likeLeft(GaodeCity.NAME,"0100");
        List<GaodeCity> gaodeCityList = gaodeCityMapper.selectList(gaodeCityQueryWrapper);

        //美团的所有市
        QueryWrapper<MeituanCity> meituanCityQueryWrapper = new QueryWrapper<>();
        meituanCityQueryWrapper.eq(MeituanCity.PARENT_CODE,"--NULL--");
        List<MeituanCity> meituanCityList = meituanCityMapper.selectList(meituanCityQueryWrapper);

        for (GaodeCity gaodeCity : gaodeCityList) {
            String gaodeName = gaodeCity.getName().substring(0, gaodeCity.getName().length() - 1);

            for (MeituanCity meituanCity : meituanCityList) {
                if (gaodeCity.getName().equals(meituanCity.getName()) || gaodeName.equals(meituanCity.getName())){
                    gaodeCity.setSgmCityId(meituanCity.getSgmCityId());
                    gaodeCityMapper.updateById(gaodeCity);
                    count++;
                }
            }
        }
        return count;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer correlateDataMtArea() {

        int count =0;
        //高德的所有市和区
        QueryWrapper<GaodeCity> gaodeCityQueryWrapper = new QueryWrapper<>();
        gaodeCityQueryWrapper.eq(GaodeCity.SGM_CITY_ID,"--NULL--");
//        gaodeCityQueryWrapper.likeLeft(GaodeCity.NAME,"市辖区");
        List<GaodeCity> gaodeCityList = gaodeCityMapper.selectList(gaodeCityQueryWrapper);

        //美团的所有市和区
//        QueryWrapper<MeituanCity> meituanCityQueryWrapper = new QueryWrapper<>();
//        meituanCityQueryWrapper.eq(MeituanCity.PARENT_CODE,"--NULL--");
        List<MeituanCity> meituanCityList = meituanCityMapper.selectList(null);

        for (GaodeCity gaodeCity : gaodeCityList) {

            String gaodeCityName = gaodeCity.getName();

            for (MeituanCity meituanCity : meituanCityList) {

                String meituanCityName = meituanCity.getName();

                //区域名称不同
                if (!gaodeCityName.equals(meituanCityName)){
                    continue;
                }

                //区域名称相同时，如果父城市名称相同则更新数据
                //查出高德的父城市
                QueryWrapper<GaodeCity> gaodeQueryWrapper = new QueryWrapper<>();
                gaodeQueryWrapper.eq(GaodeCity.CODE,gaodeCity.getParentCode());
                GaodeCity gaodeP = gaodeCityMapper.selectOne(gaodeQueryWrapper);
                if (ObjectUtils.isEmpty(gaodeP)){
                    continue;
                }
                String pName = gaodeP.getName();
                String substring = pName;

                if (pName.endsWith("市")){
                    substring = pName.substring(0, gaodeP.getName().length() - 1);
                }
                if (pName.contains("市辖区")){
                    substring =  pName.substring(0, gaodeP.getName().length() - 4);
                }

                //先查询出美团的父城市名称
                QueryWrapper<MeituanCity> meituanQueryWrapper = new QueryWrapper<>();
                meituanQueryWrapper.eq(MeituanCity.CODE,meituanCity.getParentCode());
                meituanQueryWrapper.eq(MeituanCity.PARENT_CODE,"--NULL--");
                MeituanCity meituanP = meituanCityMapper.selectOne(meituanQueryWrapper);
                if (ObjectUtils.isEmpty(meituanP)){
                    continue;
                }
                String meituanPName = meituanP.getName();

                if (pName.equals(meituanPName) || substring.equals(meituanPName)){
                    gaodeCity.setSgmCityId(meituanCity.getSgmCityId());
                    gaodeCityMapper.updateById(gaodeCity);
                    count++;
                }
            }
        }
        System.out.println("------------映射结束-----------------"+count);
        return count;
    }


}
