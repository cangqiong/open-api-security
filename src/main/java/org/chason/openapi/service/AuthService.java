package org.chason.openapi.service;

import org.chason.openapi.constant.SystemConstantEnum;
import org.chason.openapi.entity.ApiBlankList;
import org.chason.openapi.entity.UserApi;
import org.chason.openapi.exception.BuinessException;
import org.chason.openapi.mapper.ApiBlankListMapper;
import org.chason.openapi.mapper.UserApiMapper;
import org.chason.openapi.util.EncryptUtil;
import org.chason.openapi.util.TokenUtil;
import org.chason.openapi.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

import static org.chason.openapi.constant.QueryStringConstant.*;

/**
 * 授权服务实现类
 *
 * @author chason
 * @date 2018-03-10
 */
@Service
public class AuthService implements IAuthService {

    @Autowired
    private ApiBlankListMapper apiBlankListMapper;

    @Autowired
    private UserApiMapper userApiMapper;

    private long maxDiffTime = 10 * 60 * 1000;

    @Override
    public boolean validate(String requestMethod, String requestUri, String queryString) {

        if (queryString == null) {
            throw new BuinessException(SystemConstantEnum.REQUEEST_INVALID.getCode(), SystemConstantEnum.REQUEEST_INVALID.getMsg());
        }

        // 将请求字符串转换成Map
        Map<String, Object> queryStrMap = UrlUtil.getUrlParams(queryString);

        // 验证是否包含必要的附加签名与Token
        if (!queryStrMap.containsKey(APPKEY) || !queryStrMap.containsKey(TOKEN) || !queryStrMap.containsKey(TIMESTAMP)) {
            throw new BuinessException(SystemConstantEnum.REQUEEST_INVALID.getCode(), SystemConstantEnum.REQUEEST_INVALID.getMsg());
        }
        String token = queryStrMap.get(TOKEN).toString();
        String timeStamp = queryStrMap.get(TIMESTAMP).toString();
        String sign = queryStrMap.get(APPKEY).toString();

        // 验证该请求是否应失效
        long requestTime = Long.parseLong(timeStamp);

        long diffTime = System.currentTimeMillis() - requestTime;

        if (diffTime < 0 || diffTime > maxDiffTime) {
            throw new BuinessException(SystemConstantEnum.REQUEEST_INVALID.getCode(), SystemConstantEnum.REQUEEST_INVALID.getMsg());
        }


        // 验证Token是否存在且有效

        if (TokenUtil.getUiid(token) == null) {
            throw new BuinessException(SystemConstantEnum.TOKEN_INVALID.getCode(), SystemConstantEnum.TOKEN_INVALID.getMsg());
        }

        // 判断该接口是否开放
        ApiBlankList apiBlankList = new ApiBlankList();
        String apiPath = requestMethod + " " + requestUri;
        apiBlankList.setApi(apiPath);
        List<ApiBlankList> apiBlankListList = apiBlankListMapper.select(apiBlankList);

        if (CollectionUtils.isEmpty(apiBlankListList)) {
            throw new BuinessException(SystemConstantEnum.API_REFUSE_ACCESS.getCode(), SystemConstantEnum.API_REFUSE_ACCESS.getMsg());
        }

        // 判断该用户是否有权限访问该接口
        String uiid = TokenUtil.getUiid(token);
        UserApi userApi = new UserApi();
        userApi.setApiPath(apiPath);
        userApi.setUiid(uiid);
        List<UserApi> userApis = userApiMapper.select(userApi);
        if (CollectionUtils.isEmpty(userApis)) {
            throw new BuinessException(SystemConstantEnum.API_REFUSE_ACCESS.getCode(), SystemConstantEnum.API_REFUSE_ACCESS.getMsg());
        }

        // 验证签名是否正确
        String signStr = getSign(queryString);

        if (!sign.equals(signStr)) {
            throw new BuinessException(SystemConstantEnum.SIGN_NOT_CORRECT.getCode(), SystemConstantEnum.SIGN_NOT_CORRECT.getMsg());
        }

        return true;
    }

    public String getSign(String queryString) {

        // 1.将请求字符串转换成Map,并按安字典升序排序
        Map<String, Object> queryStrMap = UrlUtil.getUrlParams(queryString);
        // 2.获取除appkey与Token除外的URL按字典升序的字符串
        queryStrMap.remove(TOKEN);
        queryStrMap.remove(APPKEY);
        String orderStr = UrlUtil.getUrlParamsByMap(queryStrMap);

        // 2.生成签名字符串
        return EncryptUtil.md5Sign(orderStr);
    }

}
