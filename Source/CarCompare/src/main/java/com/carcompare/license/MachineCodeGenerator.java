package com.carcompare.license;

import com.carcompare.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

/**
 * 机器码生成器
 */
class MachineCodeGenerator {
    static final Logger logger = LoggerFactory.getLogger(MachineCodeGenerator.class);

    static final String PREFIX = "COMPUTER";

    static final String DISK_DRIVE = "C";

    /**
     * 生成机器码
     * @return
     */
    public static String generate(){
        try
        {
            String cpu = getCPUNumber();
            String disk = getDiskNumber(DISK_DRIVE);
            String mac = getMac();

            String code = PREFIX
                    .concat(".").concat(cpu)
                    .concat(".").concat(disk)
                    .concat(".").concat(mac);

            //logger.info(String.format("机器码：%s", code));

            return AppUtil.MD5(code).toUpperCase();
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * 获取CPU序列号
     * @return
     */
    private static String getCPUNumber(){
        try {
            Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            String serial = sc.next();
            return serial.toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取指定硬盘卷标号
     * @return
     */
    private static String getDiskNumber(String drive) {
//        try{
//            FileStore store = Files.getFileStore(Paths.get(drive + ":"));
//            Object volume = store.getAttribute("volume:vsn");
//            if(volume == null){
//                logger.error("读取硬盘标识失败");
//                return "";
//            }
//            return volume.toString();
//        }catch (Exception ex){
//            logger.error(String.format("读取硬盘标识失败:%s", ex.getMessage()));
//        }

        //由于.NET 和 JAVA对于部分服务器获取的硬盘ID不一致，所以暂时不使用

        return "";
    }

    /**
     * 获取MAC地址
     */
    private static String getMac() {
        String macAddress = "";

        try{
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            Formatter formatter = new Formatter();
            for (int i = 0; i < mac.length; i++) {
                macAddress = formatter.format(Locale.getDefault(), "%02X%s", mac[i], (i < mac.length - 1) ? "-" : "").toString();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return macAddress.toUpperCase();
    }
}
