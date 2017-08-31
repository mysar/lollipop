package io.github.mysar.blog.modal.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
/**
 * 文章标签实体
 * @author Im.Yan
 */
@Data
@Alias("articleTag")
public class ArticleTag implements Serializable {

    private Integer articleId;  //文章id

    private Integer tagId;      //标签id

    private String tagName;     //标签名

}
