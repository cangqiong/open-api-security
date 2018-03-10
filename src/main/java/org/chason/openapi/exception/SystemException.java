package org.chason.openapi.exception;

/**
 * 系统异常类
 *
 * @author chason
 * @date 2018-03-10
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 5678142353787803120L;

    //异常对应的返回码
    private String code;
    //异常对应的描述信息
    private String message;

    public SystemException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
