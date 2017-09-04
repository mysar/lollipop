package io.github.mysar.blog.modal.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("tag")
public class Tag implements Serializable {

    private Integer id;

    private String tagName;    //标签名

    private String aliasName;  //别名

}
