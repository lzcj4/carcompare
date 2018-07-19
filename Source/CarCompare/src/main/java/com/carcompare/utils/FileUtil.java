package com.carcompare.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 文件工具类
 */
public class FileUtil {
    static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 文件是否存在
     * @param file
     * @return
     */
    public static Boolean fileExists(String file) {
        try {
            return new File(file).exists();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    public static Boolean deleteFile(String path){
        File file = new File(path);

        if(file.isFile() && file.exists())
            file.delete();
        return  ClearEmptyDir(new File(file.getParent()));
    }

    public  static  boolean ClearEmptyDir(File file){
        if(file.isDirectory() && file.listFiles().length == 0 && file.exists()){
            file.delete();
            return ClearEmptyDir(new File(file.getParent()));
        }else {
            return  true;
        }
    }

    /**
     * 读取文件
     * @param filePath
     * @return
     */
    public static String readFile(String filePath){
        InputStream in = null;
        byte[] readBytes = new byte[1024];
        int n = -1;
        StringBuilder sb = null;

        try
        {
            in = new FileInputStream(filePath);
            sb = new StringBuilder();

            while ((n = in.read(readBytes, 0, readBytes.length)) != -1){
                sb.append(new String(readBytes, 0, n));
            }

            return sb.toString();
        }catch (Exception ex){
            logger.error(String.format("读取文件[%s]失败：\r\n%s",filePath, ex.getMessage()));
            return null;
        }finally {
            if(in != null){
                try
                {
                    in.close();
                }catch (Exception ex){
                }
            }
        }
    }

    /**
     * 写文件
     * @param filePath
     * @param content
     */
    public static void writeFile(String filePath, String content){
        OutputStream out = null;

        try {
            out = new FileOutputStream(filePath);
            out.write(content.getBytes());
            out.close();
            out = null;
        }catch (Exception ex){
            logger.error(String.format("写入文件[%s]失败：\r\n%s",filePath, ex.getMessage()));
        }finally {
            if(out != null){
                try
                {
                    out.close();
                }catch (Exception ex){
                }
            }
        }
    }

    /**
     * 创建文件
     */
    public static void createFile(String filePath){
        try {
            File file = new File(filePath);
            if(file.exists()){
                return;
            }

            file.createNewFile();
        }catch(Exception ex){
            logger.error(String.format("创建文件[%s]失败：\r\n%s",filePath, ex.getMessage()));
        }
    }

    /**
     * 设置文件隐藏
     * @param filePath
     */
    public static void hideFile(String filePath){
        try {
            String command = String.format("attrib +H \"%s\"", filePath);
            Runtime.getRuntime().exec(command);
        }catch(Exception ex){
            logger.error(String.format("隐藏文件[%s]失败：\r\n%s",filePath, ex.getMessage()));
        }
    }
}
