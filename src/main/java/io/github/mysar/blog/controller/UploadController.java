package io.github.mysar.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


/**
 * Created by Im.Yan on 2017/8/24.
 * 描述:
 */
@Controller
public class UploadController {

//    //访问路径为：http://127.0.0.1:8080/file
//    @RequestMapping("/file")
//    public String file(){
//        return"upload/FileManager";
//    }

    /**
     * 文件上传具体实现方法;
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("upload-filename")MultipartFile file){
        // 通过界面传入要上传的文件名 - upload-filename
        String fileName = null;
        if(!file.isEmpty()){
            try {
              /*
               * 这段代码执行完毕之后，图片上传到了工程的跟路径；
               * 大家自己扩散下思维，如果我们想把图片上传到 d:/files大家是否能实现呢？
               * 等等;
               * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如：
               * 1、文件路径；
               * 2、文件名；
               * 3、文件格式;
               * 4、文件大小的限制;
               *
               */

//                // 前端传入mulFileSource
//                     // 创建压缩前源文件
//                File fileSourcePath = new File("tmp/source/");
//                File fileSource = new File(fileSourcePath, FileManager.getOriginalFilename());
//                if (!fileSourcePath.exists()) {
//                    fileSourcePath.mkdirs();
//                }
//                // 将接收得图片暂存到临时文件中
//                //mulFileSource.transferTo(fileSource);
//               FileManager.transferTo(fileSource);

//                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(FileManager.getOriginalFilename())));
//                //保存上传的文件到指定的目录
//                System.out.println(FileManager.getOriginalFilename());
//
//                out.write(FileManager.getBytes());


//                out.flush();
//                out.close();

                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =  new BufferedOutputStream(new FileOutputStream(new File("D:/data/" + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                System.out.println(fileName);
                return "你已成功上传 " + fileName;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            }
            //return"上传成功";
        }else{
            return"上传失败，因为文件是空的.";
        }
    }
}