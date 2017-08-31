package io.github.mysar.blog.modal.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by Im.Yan on 2017/8/27.
 * 描述: 上传附件管理
 */
@Data
@Alias("attach")
public class Attach implements Serializable{

    private Integer id;

    private String fname;

    private String ftype;

    private String fkey;

    private Integer authorId;

    private Integer created;


}
