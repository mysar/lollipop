package io.github.mysar.blog.common.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.mysar.blog.common.FileManager.Constant;
import io.github.mysar.blog.common.FileManager.FileManager;
import io.github.mysar.blog.common.Error.ErrorRespGenUtil;
import io.github.mysar.blog.common.ErrorCode.ErrorCode;
import io.github.mysar.blog.common.ErrorCode.ParamErrCode;
import io.github.mysar.blog.common.flagcode.NormalCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Tip: 文件上传
 * Created by Im.Yan on 2017/8/30.
 */
@Controller
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileManager fileManager;

    //@Value("#{config.accessoriesDir}")
    @Value("D:/data/")
 	private String accessoriesDir;

    //访问路径为：http://127.0.0.1:8080/file
    @RequestMapping("/file")
    public String file(){
        return"upload/file";
    }


    /**
     * 上传附件
     * @param file
     * @return
     */
    @RequestMapping("/file/uploadFile")
    @ResponseBody
    public Object uploadFile(@RequestParam("file")MultipartFile file){
        if (file == null)
            return ErrorRespGenUtil.generateErrorResp(ParamErrCode.PARAM_NO_SET_ERR, "请选择您要上传的文件！");
        String originName = file.getOriginalFilename();
        logger.debug("上传的文件名是: {}",originName);
        String newName = System.currentTimeMillis()+originName.substring(originName.lastIndexOf(".") - 1);
        File newFile = new File(accessoriesDir, newName);
        FileOutputStream fos = null;
        try {
            byte[] buffer = new byte[1024];
            InputStream is = file.getInputStream();
            fos = new FileOutputStream(newFile);
            int i = is.read(buffer);
            while (i != -1) {
                fos.write(buffer, 0, i);
                i = is.read(buffer);
            }
            JSONObject json = new JSONObject();
            json.put(Constant.ERR_CODE, NormalCode.NORMAL_CODE);
            json.put("fileName", newFile.getName());
            return json;
        } catch (IOException e){
            return ErrorRespGenUtil.generateErrorResp(ErrorCode.UPLOAD_FILE_ERROR, "上传失败！");
        }finally {
            try {
                if (fos != null)
                    fos.close();
            }catch (IOException e1){
                return ErrorRespGenUtil.generateErrorResp(ErrorCode.UPLOAD_FILE_ERROR, "上传失败！");
            }
        }
    }

    /**
     * 删除附件
     * @param fileName
     * @return
     */
    @RequestMapping("delFile")
    @ResponseBody
    public Object delFile(String fileName){
        if (StringUtils.isEmpty(fileName)){
            return ErrorRespGenUtil.generateErrorResp(ParamErrCode.PARAM_NO_SET_ERR, "请求参数不能为空！");
        }
        File file = new File(accessoriesDir, fileName);
        if (!file.exists())
            return ErrorRespGenUtil.generateErrorResp(ParamErrCode.PARAM_NO_EXITS, "您选择的文件不存在！");
        if(file.delete())
            return ErrorRespGenUtil.generateErrorResp(NormalCode.NORMAL_CODE, "删除成功！");
        return ErrorRespGenUtil.generateErrorResp(ErrorCode.DELETE_FILE_ERROR, "删除失败！");
    }

    /**
     * 下载附件
     * @param fileName
     * @param response
     * @return
     */
    @RequestMapping("/downloadFile")
    @ResponseBody
    public Object downloadFile(String fileName,HttpServletResponse response) {
        if (StringUtils.isBlank(fileName)) {
            return ErrorRespGenUtil.generateErrorResp(ParamErrCode.PARAM_ERR, "请求参数错误！");
        }
        try {
            File destFile = new File(accessoriesDir, fileName);
            if (destFile == null) {
                return ErrorRespGenUtil.generateErrorResp(ErrorCode.DOWNLOAD_FILE_ERROR, "没有要下载的文件");
            }
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(destFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            bis.close();
            fis.close();
            return ErrorRespGenUtil.generateErrorResp(NormalCode.NORMAL_CODE, "下载完成");
        } catch (Exception e) {
            logger.error("下载教师导入数据错误信息异常：{}", e);
            return ErrorRespGenUtil.generateErrorResp(ErrorCode.DOWNLOAD_FILE_ERROR, "没有找到要下载的文件");
        }
    }


    /**
     * 下载Excel模板或错误数据
     */
    @RequestMapping("/download")
    @ResponseBody
    public Object downExcelError(String fileName,HttpServletResponse response) {
        if (StringUtils.isBlank(fileName)) {
            return ErrorRespGenUtil.generateErrorResp(ParamErrCode.PARAM_ERR, "请求参数错误！");
        }
        try {
            File destFile = fileManager.obtainDownloadFile(fileName);
            if (destFile == null) {
                return ErrorRespGenUtil.generateErrorResp(ErrorCode.DOWNLOAD_FILE_ERROR, "没有要下载的文件");
            }
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(destFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            bis.close();
            fis.close();
            return ErrorRespGenUtil.generateErrorResp(NormalCode.NORMAL_CODE, "下载完成");
        } catch (Exception e) {
            logger.error("下载教师导入数据错误信息异常：{}", e);
            return ErrorRespGenUtil.generateErrorResp(ErrorCode.DOWNLOAD_FILE_ERROR, "没有找到要下载的文件");
        }
    }
}

