package io.github.mysar.blog.common.Error;

import io.github.mysar.blog.common.entity.RespError;

/**
 * Created by Im.Yan on 2017/8/24.
 * 描述:
 */
public class ErrorRespGenUtil {

    private static RespError error;
    /**
     * 生成错误的响应
     * @param statusCode
     * @param errorMsg
     * @return
     */
    public static RespError generateErrorResp(int statusCode, String errorMsg){
        if (error == null) {
            error = new RespError(statusCode, errorMsg);
        }else {
            error.setErr_code(statusCode);
            error.setError_msg(errorMsg);
        }
        return error;
    }
}
