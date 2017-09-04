package io.github.mysar.blog.common.FileManager;

/**
 * Tip:文件操作工具类
 * Created by Im.Yan on 2017/9/1.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     * @param sPath  要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        Boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 递归获取所有包含指定名称的所有文件
     * @param fileName  指定名称
     * @param file      指定路径的文件对象
     * @param list      存放获取到的文件的list集合
     * @return
     */
    public static List<File> getNameFiles(String fileName, File file, List<File> list){
        File[] files = file.listFiles();
        for (File file1 : files){
            if (file1.isDirectory()) getNameFiles(fileName, file1, list);
            if (file1.getName().contains(fileName)) list.add(file1);
        }
        return list;
    }

    /**
     * 读取文件中的字符串数据
     * @param file
     * @return
     */
    public static String readString(File file){
        String str;
        try {
            FileInputStream in=new FileInputStream(file);
            // 一次性读完
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            str=new String(buffer,"UTF-8");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    /**
     * 查找指定路径下所有以指定名字开头的文件
     * 并确定是否删除，
     * @param path      指定路径
     * @param fileName  指定名称
     * @param isDel     是否删除
     * @return  如果有文件或删除成功则返回true
     */
    public static Boolean checkAndDel(String path, String fileName, Boolean isDel){
        File file = new File(path);
        if (file.exists() && file.isDirectory()){
            List<File> list = getNameFiles(fileName, new File(path), new ArrayList<File>());
            if (list != null && list.size() > 0){
                if (isDel){
                    for(File delFile : list){
                        Boolean b = delFile.delete();
                        if (!b) return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] strings){
        String path = "D:/work/";       // 文件所在目录
        Boolean b = FileUtil.checkAndDel(path, "123.txt", true);
        if (b){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
    }
}

