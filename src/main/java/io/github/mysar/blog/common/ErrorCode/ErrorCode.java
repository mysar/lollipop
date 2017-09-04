package io.github.mysar.blog.common.ErrorCode;

/**
 * Created by Im.Yan on 2017/8/24.
 * 描述: 响应代码
 */
public class ErrorCode {

    public final static int NORMAL_CODE = 200; // 正常


    public final static int PARAM_ERROR = 4001; //传递的参数错误

    public static int PRINT_DATA_ERROR = 5001;   //打印文件获取数据异常


    public final static int DELETE_FILE_ERROR = 101;  // 删除附件失败

    public final static int UPLOAD_FILE_ERROR = 102;  // 上传附件失败

    public final static int DOWNLOAD_FILE_ERROR= 103; // 下载附件失败


}
