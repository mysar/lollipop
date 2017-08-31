package io.github.mysar.blog.common.ErrorCode;

/**
 * 这个是处理异常
 * Created by Im.Yan on 2017/8/24.
 * 描述: 异常处理返回状态码
 */
public class ExceptionErrCode {

    /**
     * 系统发生未知异常
     */
    public final static int UNKOWN_EXCEPTION=5001;

    /**
     * 请求被拦截异常
     */
    public final static int REQUEST_FILTER_EXCEPTION = 5002;

    /**
     * 数据库录入异常
     */
    public final static int DB_INSERT_EXCEPTION = 5003;

    /**
     * 数据库查询异常
     */
    public final static int DB_SELECT_EXCEPTION = 5004;

    /**
     * 数据库修改异常
     */
    public final static int DB_UPDATE_EXCEPTION = 5005;

    /**
     * 数据库删除异常
     */
    public final static int DB_DELETE_EXCEPTION = 5006;

    /**
     * 数据库操作异常
     */
    public final static int DB_EXCEPTION = 5007;

    /**
     * 数据库导出文件异常
     */
    public final static int DB_EXPORT_FILE_EXCEPTION = 5008;

    /**
     * Excel上传表头错误
     */
    public final static int EXCEL_HEADER_PARSER_ERROR = 5009;

    /**
     * Excel上传数据错误
     */
    public final static int EXCEL_UPLOAD_DATA_ERROR = 5010;

    /**
     * 文件下载异常
     */
    public final static int FILE_DOWNLOAD_ERROR = 5011;

    /**
     * zip压缩或解压异常
     */
    public final static int ZIP_FILE_ERROR = 5012;

    /**
     * HttpClient请求异常
     */
    public final static int SERVER_CALL_EXCEPTION = 5013;

    /**
     * 数据库有数据未处理完成或数据错误
     */
    public final static int DB_DATA_EXCEPTION = 5014;

    /**
     * Excel导出字段不匹配
     */
    public final static int Excel_Down_EXCEPTION = 5015;
}
