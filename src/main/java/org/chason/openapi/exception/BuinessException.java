package org.chason.openapi.exception;

/**
 * 业务异常
 *
 * @author chason
 * @date 2018-03-10
 */
public class BuinessException extends RuntimeException {

    private static final long serialVersionUID = 8285119261274210710L;

    private String code;

    private String msg;

    public BuinessException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;

    }

    public BuinessException() {
        super();
    }

    public BuinessException(String message) {
        super(message);
    }

    public BuinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuinessException(Throwable cause) {
        super(cause);
    }

    public BuinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
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
