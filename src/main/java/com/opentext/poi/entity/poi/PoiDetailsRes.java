package com.opentext.poi.entity.poi;

import com.opentext.poi.common.annotation.Param;

import java.util.List;

/**
 * poi详情结果接口返回数据封装对象
 * poi查看收藏详细信息接口
 */


public class PoiDetailsRes {

    @Param(description = "poi名称")
    private String name;

    @Param(description = "POI相册")
    private List<AlbumDto> photoAlums;

    @Param(description = "评分")
    private String grade;

    @Param(description = "类型(二级分类)")
    private String type;

    @Param(description = "商圈")
    private String zone;

    @Param(description = "营业时间")
    private String time;

    @Param(description = "poi地址")
    private String address;

    @Param(description = "")
    private String description;

    @Param(description = "poi地址距离当前用户的距离")
    private String distance;

    @Param(description = "GPS经纬度，经度在前，纬度在后")
    private String pos;

    @Param(description = "电话号码")
    private String phone;

    @Param(description = "点评列表显示")
    private List<CommentDto> comments;

    @Param(description = "美食分类显示-推荐菜")
    private String recommendations;

    @Param(description = "美食分类显示 团购")
    private List<GrouponDto> groupons;

    @Param(description = "POI唯一标识，用于点击请求详情")
    private String poiId;

    @Param(description = "POI类型，用于标识该POI来自高德还是美团,gd标识高德，mt标识美团")
    private String poiType;

    @Param(description = "后台提供用于跟踪后续使用该缓存操作，32位UUID")
    private String sgmEventId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlbumDto> getPhotoAlums() {
        return photoAlums;
    }

    public void setPhotoAlums(List<AlbumDto> photoAlums) {
        this.photoAlums = photoAlums;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public List<GrouponDto> getGroupons() {
        return groupons;
    }

    public void setGroupons(List<GrouponDto> groupons) {
        this.groupons = groupons;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getSgmEventId() {
        return sgmEventId;
    }

    public void setSgmEventId(String sgmEventId) {
        this.sgmEventId = sgmEventId;
    }
}