package io.github.mysar.blog.modal.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by Im.Yan on 2017/8/22.
 * 描述:分类实体
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("category")
public class Category implements Serializable {

    private Integer id;

    private String categoryName; //分类名称

    private String aliasName;  //分类别名

    private Integer sort;
}
