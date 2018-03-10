package org.chason.openapi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Token工具类
 *
 * @author chason
 * @date 2018-03-10
 */
public class TokenUtil {

    private static Map<String, String> tokenMap = new HashMap<>();
    private static Map<String, String> uidMap = new HashMap<>();

    private static String split = ",";

    private static long maxTokenAliveTime = 10 * 60 * 1000;

    public static String getTokenStr(String uiid) {
        if (uidMap.get(uiid) == null || !isTokenValid(tokenMap.get(uidMap.get(uiid)))) {
            String token = EncryptUtil.md5(uiid + System.currentTimeMillis());
            uidMap.put(uiid, token);
            return token;
        }

        return uidMap.get(uiid);
    }

    public static void put(String token, String userId) {
        long now = System.currentTimeMillis();
        tokenMap.put(token, userId + split + now);
    }

    public static String getUiid(String token) {

        String uiidStr = tokenMap.get(token);
        if (uiidStr == null) return null;
        if ((isTokenValid(uiidStr))) {
            return uiidStr.split(",")[0];
        } else {
            tokenMap.remove(token);
            return null;
        }
    }

    public static boolean isTokenValid(String uiidStr) {
        long tokenSaveTime = Long.parseLong(uiidStr.split(",")[1]);
        return (System.currentTimeMillis() - tokenSaveTime) < maxTokenAliveTime;
    }
}
