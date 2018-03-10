package org.chason.openapi.util;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类
 *
 * @author chason
 * @date 2018-03-10
 */
public class StringUtil {

    private StringUtil() {
    }

    public static String getUtf8Str(String originStr) {

        if (originStr == null) return null;
        try {
            String utf8QueryString = new String(originStr.getBytes("UTF-8"), "UTF-8");
            return utf8QueryString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return originStr;
        }
    }

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    public static String getTokenStr(String str) {
        return EncryptUtil.md5(str);
    }

}
