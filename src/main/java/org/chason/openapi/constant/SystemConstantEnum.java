package org.chason.openapi.constant;

/**
 * 代码常量
 *
 * @author chason
 * @date 2018-03-10
 */
public enum SystemConstantEnum {

    SUCCESS("200", "响应成功"),
    USER_NOT_FOUND("400", "用户不存在"),
    NOT_LOGIN("401", "请登陆之后进行操作"),
    REQUEEST_INVALID("402", "请求已经失效"),
    TOKEN_INVALID("403", "Token已经失效"),
    SIGN_NOT_CORRECT("404", "签名不正确"),
    PASS_NOT_CORRECT("405", "密码不正确"),
    API_REFUSE_ACCESS("406", "你没有访问API的权限"),
    SYSTEM_ERROR("999", "系统错误");

    private String code;
    private String msg;

    SystemConstantEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getCodeByMsg(String msg) {
        for (SystemConstantEnum codeConstantEnum : SystemConstantEnum.values()) {
            if (codeConstantEnum.getMsg().equals(msg)) {
                return codeConstantEnum.getCode();
            }
        }
        return "-1";
    }

    public static String getMsgByCode(String code) {
        for (SystemConstantEnum systemConstantEnum : SystemConstantEnum.values()) {
            if (systemConstantEnum.getCode() == code) {
                return systemConstantEnum.getMsg();
            }
        }
        return "无法找到该代码对应的消息";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
