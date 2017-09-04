package io.github.mysar.blog.mapper;

import io.github.mysar.blog.modal.vo.Attach;
import io.github.mysar.blog.modal.vo.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Im.Yan on 2017/8/27.
 * 描述:
 */

public interface AttachMapper {

    /**
     * 查询所有
     * @return
     */
    List<Attach> findAll();

    /**
     * 保存附件
     * @param attach
     */
    void saveAttach(Attach attach);

    /**
     * 分页查询好友列表
     * @param pager 分页条件
     * @param param
     * @return
     */
    List<Attach> loadAttach(@Param("pager") Pager pager, @Param("param") String param);

    /**
     * 通过id获取友情链接
     * @param id
     * @return
     */
    Attach getAttachById(Integer id);

    /**
     * 删除一条友链
     * @param id
     */
    void deleteAttach(Integer id);

    /**
     * 更新友链
     * @param
     */
    void updateAttach(Attach attach);

    /**
     * 获取友链数量
     * @param pager
     * @return
     */
    int initPage(Pager pager);
}
