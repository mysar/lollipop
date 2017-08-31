package io.github.mysar.blog.common.FileManager;

import io.github.mysar.blog.modal.vo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Tip:
 * Created by Im.Yan on 2017/8/30.
 */

public class Constant {

    /**
     * 是否是开发期间的测试版（开发期间使用token）
     */
    public static final Boolean IS_TOKEN = true;

    /**
     * 开发期间使用token时，保存登录用户信息
     * key：每登录一个用户随机生成一个MD5码
     * value：登录用户信息
     */
    public static Map<String, User> LOGIN_USER = new HashMap<String, User>();

    /**
     * 时间格式化类型
     */
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 响应码
     */
    public static final String ERR_CODE = "err_code";

    /**
     * 响应消息
     */
    public static final String ERR_MSG = "err_msg";

    /**
     * 响应实体
     */
    public static final String ENTITY = "entity";

    /**
     * 响应数据总条数
     */
    public static final String TOTAL = "total";

    /**
     * 响应结果
     */
    public static final String ROWS = "rows";

    /**
     * 分页处理数据的信息条数
     */
    public static final int PAGE_COUNT = 100;

    /**
     * 上课时间节次最大支持30周,开始的1为标识位
     */
    public static final String TIME_SLICE_OK_CODE = "1111111111111111111111111111111";

    /**
     * 不上课时间节次最大支持30周,开始1为标识位
     */
    public static final String TIME_SLICE_NO_CODE = "1000000000000000000000000000000";
}
