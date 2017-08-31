package io.github.mysar.blog.modal.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 用户个人信息简介
 */
@Getter
@Setter
@ToString
@Alias("userInfo")
public class UserInfo implements Serializable {

    private String username;   //用户名

    private String avatar;     //图像src

    private String nickname;   //昵称

    private String phone;      //电话号码

    private String email;      //邮箱

    private String signature;  //个性签名

    private String address;    //地址

    private String announcement; //公告

    private String telegram;   //tg

    private String wechart;    //微信

}
