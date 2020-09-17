//package com.opentext.mybatis.poi;
//
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.TreeMap;
//
//@RestController
//public class PoiRedisEventId {
//
//
//    @Autowired
//    private RedisEventIdService redisEventIdService;
//
//    @Autowired
//    private PoiService poiService;
//
//
//    @GetMapping(value = "getPoiSearchDataByEventId")
//    public void getPoiSearchDataByEventId(RequestDto requestDto) {
//
//        //校验数据是否安全?
//        String sign = requestDto.getSign();
//
//
//        //eventid为空，直接调用poi搜索接口查询数据，并生成行的eventid返回
//        if (StringUtils.isBlank(requestDto.getSgmeventid())) {
//
//        }
//
//        //eventid不为空，拼接所有的参数使用sign加密生成新的eventid，然后对比比较是否相同，相同，去redis查数据，不同，调用poi搜索接口查询数据
//        TreeMap<String, String> treeMap = new TreeMap<>();
//        treeMap.put("keywords", requestDto.getKeywords());
////        treeMap.put("pos", requestDto.getPos());
//        treeMap.put("city", requestDto.getCity());
//        treeMap.put("sgmid", requestDto.getSgmid());
//
//        if (!StringUtils.isBlank(requestDto.getType())) {
//            treeMap.put("type", requestDto.getType());
//        }
//        if (!StringUtils.isBlank(requestDto.getDistrictid())) {
//            treeMap.put("districtid", requestDto.getDistrictid());
//        }
//        if (!StringUtils.isBlank(requestDto.getDistance())) {
//            treeMap.put("distance", requestDto.getDistance());
//        }
//        if (!StringUtils.isBlank(requestDto.getOrderby())) {
//            treeMap.put("orderby", requestDto.getOrderby());
//        }
//        if (!StringUtils.isBlank(requestDto.getExttype())) {
//            treeMap.put("exttype", requestDto.getExttype());
//        }
//
//        //生成sign
//        String newSign = Sign.genSign(treeMap);
//
//        //比较sign
//        if (!newSign.equals(requestDto.getSgmeventid())) {
//            //不同，表示是新的查询，
//
//
//        } else {
//
//
//        }
//
//    }
//
//}
//
