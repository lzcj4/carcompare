package com.carcompare.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class ChineseUtil {
    public static char getFirstPinyin(char chChar){
        //小于128 为字母
        if(chChar >128){
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(chChar);
            if(pinyinArray != null && pinyinArray.length >0)
            {
                return  pinyinArray[0].charAt(0);
            }
        }
        return  chChar;
    }
}
