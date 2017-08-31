package io.github.mysar.blog.modal.vo;

import lombok.*;

/**
 * 上传图片的响应类
 * @Auther Im.Yan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PhotoResult {

    private int success;    //成功标准 0失败 1成功
    private String url;     //图片url
    private String message; //错误信息
}
