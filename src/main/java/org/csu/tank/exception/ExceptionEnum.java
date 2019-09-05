package org.csu.tank.exception;

/**
 * @author louyy
 * @
 * @date Created on 2019/9/1
 */
public enum ExceptionEnum {
    TOKEN_CHECK_FAIL_EXCEPTION(10001,"Token认证失败,请重新登录"),
    USER_NOT_EXIST(10002,"用户不存在");



    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
