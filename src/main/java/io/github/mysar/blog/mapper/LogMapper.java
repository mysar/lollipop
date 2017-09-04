package io.github.mysar.blog.mapper;

import io.github.mysar.blog.modal.vo.LogInfo;
/**
 * @author
 * 日志信息
 *
 */
public interface LogMapper {
    /**
     * 保存日志信息
     * @param log
     */
    void save(LogInfo log);
}
