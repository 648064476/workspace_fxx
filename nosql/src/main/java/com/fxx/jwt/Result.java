package com.fxx.jwt;

import java.io.Serializable;

/**
 * @description: 返回结果集
 * @author: godShan
 * @create: 2018-07-09 17:15
 **/
public class Result<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
    private String code;
    private boolean success;
    private T data;

    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public String getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
