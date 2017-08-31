package io.github.mysar.blog.service;

import io.github.mysar.blog.modal.vo.Attach;
import io.github.mysar.blog.modal.vo.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Im.Yan on 2017/8/27.
 * 描述:
 */
public interface AttachService {
    /**
     * 查询所有
     * @return
     */
    List<Attach> findAll();

    /**
     * 保存
     */
    void saveAttach(String fname, String fkey, String ftype, Integer author);

    /**
     * 分页查询好友列表
     * @param pager 分页条件
     * @param param
     * @return
     */
    List<Attach> loadAttach(@Param("pager") Pager pager, @Param("param") String param);

    /**
     * 通过id获取
     * @param id
     */
    Attach getAttachById(Integer id);

    /**
     * 删除一条
     * @param id
     */
    void deleteAttach(Integer id);

    /**
     * 获取友链数量
     * @param pager
     * @return
     */
    void initPage(Pager pager);
}
