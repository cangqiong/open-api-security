package org.chason.openapi.service;

import org.chason.openapi.util.TokenUtil;
import org.springframework.stereotype.Service;

/**
 * 登录服务类
 *
 * @author chason
 * @date 2018-03-10
 */
@Service
public class LoginService implements ILoginService {

    @Override
    public String getToken(String userId, String pass) {
        String token = null;

        // 暂时写死
        if ("test".equals(userId) && ("test").equals(pass)) {
            token = TokenUtil.getTokenStr(userId);
            TokenUtil.put(token, userId);
        }
        return token;
    }

}
