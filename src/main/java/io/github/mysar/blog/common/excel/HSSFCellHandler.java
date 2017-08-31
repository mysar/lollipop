package io.github.mysar.blog.common.excel;
/**
 * poi HSSFCell处理器
 * Created by zyj on 2017/2/21.
 */
public interface HSSFCellHandler {

    /**
     * 以String类型处理Cell
     * @param index
     * @param value
     */
    void handleCellStr(int index,String value);
}
