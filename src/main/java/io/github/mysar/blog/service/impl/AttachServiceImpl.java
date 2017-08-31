package io.github.mysar.blog.service.impl;

import io.github.mysar.blog.common.utils.DateKit;
import io.github.mysar.blog.mapper.AttachMapper;
import io.github.mysar.blog.modal.vo.Attach;
import io.github.mysar.blog.modal.vo.Pager;
import io.github.mysar.blog.service.AttachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Im.Yan on 2017/8/27.
 * 描述:
 */
public class AttachServiceImpl implements AttachService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachServiceImpl.class);

    @Resource
    private AttachMapper attachMapper;

    @Override
    public List<Attach> findAll() {
        return attachMapper.findAll();
    }

    @Override
    public void saveAttach(String fname, String fkey, String ftype, Integer author) {
        Attach attach = new Attach();
        attach.setFname(fname);
        attach.setAuthorId(author);
        attach.setFkey(fkey);
        attach.setFtype(ftype);
        attach.setCreated(DateKit.getCurrentUnixTime());
        attachMapper.saveAttach(attach);

    }

    @Override
    public List<Attach> loadAttach(Pager pager, String param) {
        pager.setStart(pager.getStart());
        return attachMapper.loadAttach(pager,param);
    }

    @Override
    public Attach getAttachById(Integer id) {
        if(null != id){
            return attachMapper.getAttachById(id);
        }
        return null;
    }

    @Override
    public void deleteAttach(Integer id) {
        if (null != id) {
            attachMapper.deleteAttach(id);
        }
    }

    @Override
    public void initPage(Pager pager) {
        int count = attachMapper.initPage(pager);
        pager.setTotalCount(count);
    }

}
