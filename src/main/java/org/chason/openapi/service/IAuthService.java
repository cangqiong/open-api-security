package org.chason.openapi.service;

/**
 * 验证服务接口类
 *
 * @author chason
 * @date 2018-03-10
 */
public interface IAuthService {


    /**
     * 验证第三方调用是否用权限访问内部接口
     *
     * @param requestMethod
     * @param requestUri
     * @param queryString
     * @return
     */
    boolean validate(String requestMethod, String requestUri, String queryString);

    /**
     * 获取加密签名
     *
     * @param queryString
     * @return
     */
    String getSign(String queryString);
}
