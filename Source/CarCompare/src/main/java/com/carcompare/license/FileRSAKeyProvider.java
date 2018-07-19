package com.carcompare.license;

import com.carcompare.utils.AppUtil;
import com.carcompare.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于文件的RSA密钥提供类
 */
public class FileRSAKeyProvider implements IRSAKeyProvider {
    static final Logger logger = LoggerFactory.getLogger(FileRSAKeyProvider.class);

    /**
     * 许可证密钥文件
     * 解密许可证的公钥文件
     */
    static final String FILE_NAME = "public.key";

    /**
     * 获取公钥
     * @return
     */
    @Override
    public String getPublicKey() {
        String content = readFile();
        if(content == null){
            logger.error("许可证密钥["+FILE_NAME+"]读取失败");
            return null;
        }

        try {
            return content;
        }catch (Exception ex){
            logger.error("许可证密钥加载失败:" + ex.getMessage());
            return null;
        }
    }

    /**
     * 读取密钥内容
     * @return
     */
    private String readFile(){
        String file = getFile();
        if(!FileUtil.fileExists(file)){
            logger.warn("文件未找到：" + file);
            return null;
        }

        return FileUtil.readFile(file);
    }

    /**
     * 获取文件路径
     * @return
     */
    private String getFile(){
        return AppUtil.getClassPath().concat(FILE_NAME);
    }
}
