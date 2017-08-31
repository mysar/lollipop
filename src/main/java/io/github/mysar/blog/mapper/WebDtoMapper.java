package io.github.mysar.blog.mapper;

import io.github.mysar.blog.modal.vo.Pager;
import io.github.mysar.blog.modal.vo.WebDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Im.Yan on 2017/8/24.
 * 描述:
 */

public interface WebDtoMapper {

    List<WebDto> getAll();

    int initPage(Pager pager);

    List<WebDto> loadWebDto(@Param("pager") Pager pager, @Param("param") String param);

}
