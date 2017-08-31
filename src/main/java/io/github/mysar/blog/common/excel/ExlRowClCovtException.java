package io.github.mysar.blog.common.excel;

/**
 * Created by Im.Yan on 2017/8/28.
 * 描述: cell转换异常
 */
public class ExlRowClCovtException extends Exception {

    //错误消息
    private String err_msg;

    //excel的数据模型
    private ExcelModel model;

    public ExlRowClCovtException(ExcelModel model, String err_msg) {
        this.err_msg = err_msg;
        this.model = model;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public ExcelModel getModel() {
        return model;
    }
}

