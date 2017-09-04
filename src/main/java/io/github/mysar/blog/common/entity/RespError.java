package io.github.mysar.blog.common.entity;

/**
 * Created by Im.Yan on 2017/8/24.
 * 描述: 请求响应错误
 */
public class RespError extends RespStatus{
    /**
     * 处理错误的消息
     */
    private String err_msg;

    public RespError(int err_code, String err_msg) {
        super(err_code);
        this.err_msg = err_msg;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setError_msg(String err_msg) {
        this.err_msg = err_msg;
    }

}
