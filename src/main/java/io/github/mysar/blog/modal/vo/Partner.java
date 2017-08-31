package io.github.mysar.blog.modal.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

//友链

@Data
@Alias("partner")
public class Partner implements Serializable {

    private Integer id;

    private String siteName;   //友链名称

    private String siteUrl;    //友链URL

    private String siteDesc;   //友链描述

    private Integer sort;      //排序
}
