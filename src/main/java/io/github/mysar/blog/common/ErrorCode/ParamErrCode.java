package io.github.mysar.blog.common.ErrorCode;

/**
 * 这是参数错误
 * Created by Im.Yan on 2017/8/24.
 * 描述: 请求参数错误返回状态码
 */
public class ParamErrCode {

    /**
     * 请求参数格式错误
     */
    public final static int PARAM_FORMAT_ERR=4001;

    /**
     * 请求参数没有设置
     */
    public final static int PARAM_NO_SET_ERR=4002;

    /**
     * 请求参数错误
     */
    public final static int PARAM_ERR=4003;

    /**
     * 参数不存在
     */
    public final static int PARAM_NO_EXITS=4004;

    /**
     * 参数已存在
     */
    public final static int PARAM_EXITS=4005;

    /**
     * 数据已经使用
     */
    public final static int PARAM_USE=4006;
}
