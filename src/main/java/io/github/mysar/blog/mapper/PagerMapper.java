package io.github.mysar.blog.mapper;

/**
 * Created by Im.Yan on 2017/8/22.
 * 描述: 分页
 */

public interface PagerMapper {
    /**
     * 找到目标分类下的分页条件
     * @param categoryId
     * @return
     */
    int loadCategoryPager(Integer categoryId);

    /**
     * 通过tagId获取文章总数量
     * @param tagId
     * @return
     */
    int loadTagPager(Integer tagId);

    /**
     * 初始化时间归档的分页信息
     * @param createTime
     * @return
     */
    int loadArchivePager(String createTime);
}
