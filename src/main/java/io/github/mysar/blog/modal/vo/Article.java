package io.github.mysar.blog.modal.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 */
@Data
@Alias("article")
public class Article implements Serializable {

    private Integer id;          //文章id

    private Integer categoryId;  //文章分类id

    private String categoryName; //分类名称

    private String title;        //标题

    private String content;      //内容

    private String description;  //描述

    private Integer status;      //状态

    private String author;       //作者

    private Date createTime;     //创建时间

    private Date updateTime;     //更新时间

    private Integer showCount;   //浏览数
}
