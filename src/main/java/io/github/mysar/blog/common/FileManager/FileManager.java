package io.github.mysar.blog.common.FileManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 上传下载文件管理器
 * Created by zyj on 2017/2/21.
 */
@Component
public class FileManager {

    public final static Logger logger = LoggerFactory.getLogger(FileManager.class);

    /**
     * 文件存储路径
     */
    @Value("D:/data")
//   @Value("#{config.fileMngDir}")
    private String fileMngDir;

  //  @Value("#{config.templateDir}")
    private String templateDir;

    public File saveUploadFile(MultipartFile file) throws IOException {
        return saveFile(file, fileMngDir);
    }

    public File saveJasperFile(MultipartFile file) throws IOException {
        return saveFile(file, templateDir);
    }

    private File saveFile(MultipartFile file, String path) throws IOException{
        String originName = file.getOriginalFilename();
        logger.debug("上传的文件名是: {}",originName);
        String newName = System.currentTimeMillis()+originName.substring(originName.lastIndexOf(".") - 1);
        File fileDir = new File(path);
        if(!fileDir.exists() || !fileDir.isDirectory()){
            fileDir.mkdirs();
        }
        File newFile = new File(fileDir,newName);
        file.transferTo(newFile);
        return newFile;
    }

//    /**
//     * 拷贝一个Excel的文件模板
//     * @param fileName
//     * @param tempType
//     * @return
//     */
//    public File copyExcelTemplate(String fileName, ExcelTempType tempType) throws IOException {
//        String result ="";
//        switch (tempType){
//            case TEACHER:
//                result = "teacher_template.xls";
//                break;
//            case JSXX:
//                result = "jsxx_template.xls";
//                break;
//            case XSXX:
//                result = "xsxx_template.xls";
//                break;
//            case KCXX:
//                result = "kcxx_template.xls";
//                break;
//            case XZB:
//                result = "xzb_template.xls";
//                break;
//            case BXZY:
//                result = "bxzy_template.xls";
//        }
//        File distFile = new File(fileMngDir+File.separator+ CommonUtil.getInstance().getEncryptKey(fileName) + ".xls");
//        if(distFile.exists()){
//            distFile.delete();
//        }
//        File srcFile = new File(templateDir+File.separator+result);
//        FileUtils.copyFile(srcFile,distFile);
//        return distFile;
//    }

    /**
     * 获取下载文件
     * @param fileName
     * @return
     */
    public File obtainDownloadFile(String fileName) {
        File distFile = null;
        if(fileName.endsWith("template.xls")){
            distFile = new File(templateDir + File.separator + fileName);
        }else
            distFile = new File(fileMngDir + File.separator + fileName);
        if(distFile.exists()){
            return distFile;
        }
        return null;
    }
}
