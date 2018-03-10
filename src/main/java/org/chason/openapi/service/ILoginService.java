package org.chason.openapi.service;

/**
 * 登录服务接口类
 *
 * @author chason
 * @date 2018-03-10
 */
public interface ILoginService {

    /**
     * 通过登录获取Token
     *
     * @param userId
     * @param pass
     * @return
     */
    String getToken(String userId, String pass);
}
