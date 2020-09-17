package com.opentext.poi.entity.poi;

import com.opentext.poi.common.annotation.Param;

/**
 * 评价详情
 */


public class CommentDto {

    @Param(description = "评论用户昵称")
    private String name;

    @Param(description = "用户头像url")
    private String head;

    @Param(description = "评分")
    private String rating;

    @Param(description = "评论内容")
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}