package org.chason.openapi.util;

import org.springframework.util.DigestUtils;

/**
 * @author chason
 * @date 2018-03-10
 */
public class EncryptUtil {

    public static String md5Sign(String publicKey, String originStr) {
        return DigestUtils.md5DigestAsHex((publicKey + originStr).getBytes());
    }

    public static String md5Sign(String originStr) {
        return DigestUtils.md5DigestAsHex((originStr).getBytes());
    }

    public static String md5(String originStr) {
        return DigestUtils.md5DigestAsHex((originStr).getBytes());
    }
}
