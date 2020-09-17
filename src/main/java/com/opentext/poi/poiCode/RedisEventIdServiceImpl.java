/*
package com.opentext.mybatis.poi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.TreeMap;

@Service
public class RedisEventIdServiceImpl {


    */
/**
     * 校验是否为新的搜索请求，true：是，false：不是
     * @param requestDto poi搜索数据
     *
     * @return
     *//*

    public boolean checkEventId(RequestDto requestDto){

        String sgmeventid = requestDto.getSgmeventid();

        //3.1：校验eventid是否为空
        if (StringUtils.isBlank(sgmeventid)){
            return true;
        }else {

            String $evnetId = sgmeventid.substring(0, sgmeventid.lastIndexOf("$"));
            //生成sign
            String newSign = getSignEventIdToCompare(requestDto);

            //比较sign
            if (!newSign.equals($evnetId)){
                //不同，表示是新的查询，
                return true;
            }
        }
        return false;
    }


    */
/**
     * 3.3：如果不是新的请求，将poi搜索数据放入redis中
     * @param responseDto poi搜索的数据
     * @return
     *//*

    public boolean putPoiSearchDataInRedis(ResponseDto responseDto){

        String sgmeventid = responseDto.getSgmeventid();
        String $evnetId = sgmeventid.substring(0, sgmeventid.lastIndexOf("$"));

        List<PoisResponseDto> poisResponseDtos = new ArrayList<>();

        List<PoisResponseDto> pois = responseDto.getPois();

        int count = 0;
        int pageNum = 0;
        for (int i = 0; i < pois.size(); i++) {
            if (count<6){
                PoisResponseDto poisResponseDto = pois.get(i);
                poisResponseDtos.add(poisResponseDto);
                count++;
            }
            if (count==5){
                pageNum++;
                responseDto.setOffset(String.valueOf(pageNum));
                responseDto.setPois(poisResponseDtos);
                String redisEventId = $evnetId.concat("$").concat(String.valueOf(pageNum));
                RedisKit.setForJson(redisEventId,responseDto,600L);
                poisResponseDtos.clear();
                count=0;
            }
        }
        return false;
    }


    */
/**
     * 从redis中获取poi信息，根据页数，每次获取5条数据
     * @param sgmeventid
     * @param offset
     * @return
     *//*

    public ResponseDto getPoiSearchDataFromRedis(String sgmeventid,String offset){

        boolean bool = checkPageTotal(sgmeventid, offset);
        if (bool == false){
            return null;
        }

        String eventId = sgmeventid.substring(0, sgmeventid.lastIndexOf("$"));
        String responseDtoString = RedisKit.get(eventId.concat(offset));

        Jackson jackson = new Jackson();
        ResponseDto responseDto = jackson.parse(responseDtoString, ResponseDto.class);
        return responseDto;
    }


    */
/**
     * 判断请求页数是否大于总页数，false: 大于 ， true：不大于
     * @param sgmeventid
     * @param offset 请求页数
     * @return
     *//*

    public boolean checkPageTotal(String sgmeventid, String offset){

        String $totalPageNum = sgmeventid.substring(sgmeventid.lastIndexOf("$")+1 , sgmeventid.length());
        if (Integer.parseInt(offset) > Integer.parseInt($totalPageNum)){
            System.out.println("当前查询页数已经大于总页数");
            return false;
        }
        return true;
    }


    */
/**
     * 根据请求参数加密生成sgmeventid
     *  @param requestDto
     *//*

    public String getSignEventIdToCompare(RequestDto requestDto){

        //3.2：校验sign是否相同，是否为新的请求
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("keywords", requestDto.getKeywords());
        treeMap.put("city", requestDto.getCity());
        treeMap.put("sgmid", requestDto.getSgmid());

        if (!StringUtils.isBlank(requestDto.getType())) {
            treeMap.put("type", requestDto.getType());
        }
        if (!StringUtils.isBlank(requestDto.getDistrictid())) {
            treeMap.put("districtid", requestDto.getDistrictid());
        }
        if (!StringUtils.isBlank(requestDto.getDistance())) {
            treeMap.put("distance", requestDto.getDistance());
        }
        if (!StringUtils.isBlank(requestDto.getOrderby())) {
            treeMap.put("orderby", requestDto.getOrderby());
        }
        if (!StringUtils.isBlank(requestDto.getExttype())) {
            treeMap.put("exttype", requestDto.getExttype());
        }

        //生成新的sign
        String newSign = Sign.genSign(treeMap);
        return newSign;
    }


    */
/**
     * 生成新的sgmEventId，拼接了总页数，用于返回给前端
     * @param requestDto
     * @param TotalPageNum 总页数
     * @return
     *//*

    public String getSignEventIdToRedis(RequestDto requestDto,String TotalPageNum){

        String newSign = getSignEventIdToCompare(requestDto);
        String totalSign = newSign.concat("$" + TotalPageNum);

        return totalSign;
    }


}
*/
