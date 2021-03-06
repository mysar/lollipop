package io.github.mysar.blog.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import io.github.mysar.blog.modal.vo.Pager;
import io.github.mysar.blog.service.CategoryService;
import io.github.mysar.blog.modal.vo.ArticleCustom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章归档的controller
 *
 */
@Controller
public class ArchiveController {

    @Resource
    @Reference
    private CategoryService categoryService;

    /**
     * 文章归档列表
     *
     * 2017.5.29 fixed bug 归档的标题错误问题
     * 设置名称出错
     * @param createTime
     * @param pager
     * @param model
     * @return
     */
    @RequestMapping("/archive/load/{createTime}")
    public String categoryList(@PathVariable String createTime, Pager pager, Model model){
        List<ArticleCustom> articleList = categoryService.loadArticleByArchive(createTime,pager);
        if (articleList != null && !articleList.isEmpty()) {
            model.addAttribute("articleList", articleList);
            model.addAttribute("pager", pager);
            model.addAttribute("createTime", createTime);
        }
        return "blog/part/archiveSummary";
    }
}