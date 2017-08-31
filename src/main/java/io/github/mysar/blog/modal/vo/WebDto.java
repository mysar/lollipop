package io.github.mysar.blog.modal.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Created by Im.Yan on 2017/8/24.
 * 描述:
 */
@Data
@Alias("webdto")
public class WebDto {

    private Integer id;

    //网站名称
    private String name;

    //网址
    private String url;

    //用户名
    private String username;

    //密码
    private String password;

    //日均访问量
    private Integer readCount;



}
