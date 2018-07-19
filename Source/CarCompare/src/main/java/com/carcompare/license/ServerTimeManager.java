package com.carcompare.license;

import com.carcompare.base.UserFriendlyException;
import com.carcompare.utils.CacheUtil;
import com.carcompare.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务器时间管理器
 * 用于检查服务器时间的合法性
 */
@Component
class ServerTimeManager {
    static final Logger logger = LoggerFactory.getLogger(ServerTimeManager.class);

    /**
     * 记录服务器时间的文件名
     */
    static final String SERVER_TIME_FILE_NAME = "svrtime.dll";

    /**
     * 服务器时间缓存名
     */
    static final String SERVER_TIME_CACHE = "server_time";

    /**
     * RSA私钥
     */
    static final String RSA_PRIVATE_KEY = "MIICdAIBADANBgkqhkiG9w0BAQEFAASCAl4wggJaAgEAAoGBAJSHd6jXaw/GFY/37Iajgp6eFnrUpOKpUUk2TxadBMPItFo3TlKgQViiW/9zrsR+gDlsvwOR5SN5dzJDB/CtOQzjuBFlupCFgvKt9QhD4z3FbDqy/5hzu1NoJtQRtPkXA4Ii6AxkOYGv6zZZLoKY78xTEkeJxjSDjUZsF229+UZTAgEDAoGAGME+nCPnLUuuQqlSFnCVxRpZFHjGJcbi4YkNLm+Ay0weDwk3uHAK5BsPVT3ydhUVXud1K0L7hemT3bXWqBze1zpCaYrdf6aVQ4O4N8NMUgh/BYUksTvMKJZtNz7QWJF9Fi62PyorRftFWVviSoHoOqjHGwNqNyxw9BunRUxXpXcCQQDI2FnjFx9NSfkuVg6g/y+fbQ8woD9R3JEfkVraFsvEPqgh8mDHin3s/j/RSY+RdM2KPEwsPxiY3dzskchdLiYPAkEAvVE+QXJzULlkaU2rDxoh8wUKY4MxuxXOwgYtvrwZ6dZU6LAwn6tf30zeYJcz/Ame1CtZSQnQ4Amw2ZoFlr0/fQJBAIXlkUIPajOGph7kCcCqH7+eCiBq1OE9thULkea53S1/GsFMQIUG/p3+1TYxCmD4iQbS3XLUuxCT6J22hZN0GV8CQH42KYD294smQvDecgoRa/dYsZesy9IOidauySnSu/E5jfB1db/HlT+IlEBkzVKxFI1yO4YGi0AGdeZmrmR+KlMCQGIvomK4uA4IGaI0UiSDf3YTjWscGyG6U6bJwTDRRGpgxBpT812UnyJ7fIawcF23SNBA2wmhceB3qU9tHGQheXQ=";

    /**
     * RSA公钥
     */
    static final String RSA_PUBLIC_KEY = "MIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQCUh3eo12sPxhWP9+yGo4KenhZ61KTiqVFJNk8WnQTDyLRaN05SoEFYolv/c67EfoA5bL8DkeUjeXcyQwfwrTkM47gRZbqQhYLyrfUIQ+M9xWw6sv+Yc7tTaCbUEbT5FwOCIugMZDmBr+s2WS6CmO/MUxJHicY0g41GbBdtvflGUwIBAw==";

    /**
     * 时间篡改检查阀值(毫秒)，1天
     */
    static final int TIME_THRESHOLD = 24 * 60 * 60 * 1000;

    @Autowired
    CacheUtil cacheUtil;

    @Autowired
    Encoder encoder;

    @Autowired
    Decoder decoder;

    /**
     * 获取服务器时间
     */
    public Date getServerTime(){
        Date current = new Date();

        validateTime(current);

        return current;
    }

    /**
     * 同步服务器时间(每15秒钟同步一次)
     */
    @Scheduled(fixedDelay = 15000)
    private void UpdateServerTime(){
        Date current = new Date();
        Date lastest = getLastest();

        if(lastest != null){
            // 当前时间 > 上一次记录的时间
            long diff = current.getTime() - lastest.getTime() + TIME_THRESHOLD;
            if(diff >= 0){
                setLastest(current);
            }
        }else{
            setLastest(current);
        }
    }

    /**
     * 设置最新时间
     */
    private void setLastest(Date date){
        String dateString = new SimpleDateFormat().format(date);

        try
        {
            String encryptedText = getEncryptedText(dateString);

            synchronized (this){
                FileUtil.createFile(getFile());
                FileUtil.writeFile(getFile(), encryptedText);
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * 获取最近一次记录的服务器时间
     * @return
     */
    private Date getLastest(){
        String file = getFile();
        if(!FileUtil.fileExists(file)){
            return null;
        }

        String encryptedText;
        synchronized (this){
            encryptedText = FileUtil.readFile(getFile());
        }

        if(encryptedText == null){
            return null;
        }

        try
        {
            String dateString = getDecryptedText(encryptedText);
            if(dateString == null){
                return null;
            }

            return new SimpleDateFormat().parse(dateString);
        }catch (Exception ex){

            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 验证时间
     * @param current
     */
    private void validateTime(Date current){
        Date lastest = getLastest();

        //当前时间 - 最后一次记录的时间 >= 阀值
        long diff = lastest.getTime() - current.getTime();
        if(diff >= TIME_THRESHOLD){
            logger.warn("服务器时间无效,可能被人为篡改");
            throw new UserFriendlyException("系统未授权");
        }
    }

    /**
     * 获取文件路径
     * @return
     */
    private String getFile(){
        return System.getenv("WINDIR") + "\\system32\\" + SERVER_TIME_FILE_NAME;
    }

    /**
     * 获取解密的时间
     * @param dateString
     * @return
     * @throws Exception
     */
    private String getEncryptedText(String dateString) throws Exception{
        return this.encoder.encode(dateString, RSA_PRIVATE_KEY);
    }

    /**
     * 获取加密的时间
     * @param encryptedText
     * @return
     * @throws Exception
     */
    private String getDecryptedText(String encryptedText) throws Exception{
       return this.decoder.decode(encryptedText, RSA_PUBLIC_KEY);
    }
}
