package org.chason.openapi.bean;


import org.chason.openapi.constant.SystemConstantEnum;

import java.io.Serializable;

/**
 * 基本响应类
 *
 * @author chason
 * @date 2018-03-10
 */
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 9166790951642352943L;

    private String code;

    private String msg;

    private T data;

    public ResultBean() {
        this.code = SystemConstantEnum.SUCCESS.getCode();
        this.msg = SystemConstantEnum.SUCCESS.getMsg();
    }

    public ResultBean(T data) {
        this.code = SystemConstantEnum.SUCCESS.getCode();
        this.msg = SystemConstantEnum.SUCCESS.getMsg();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
