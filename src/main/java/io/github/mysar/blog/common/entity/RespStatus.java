package io.github.mysar.blog.common.entity;

/**
 * 请求响应状态
 *
 */
public class RespStatus {

    /**
     * 请求的响应状态
     * 200X:请求正常处理
     * 400X:请求参数解析错误
     * 500X:服务器处理请求错误
     */
    protected int err_code;

    public RespStatus() {
    }

    public RespStatus(int err_code) {
        this.err_code = err_code;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }
}
