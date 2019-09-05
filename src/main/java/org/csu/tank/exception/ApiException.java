package org.csu.tank.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @author louyy
 * @date Created on 2019/9/1
 */
@Getter
@Setter
public class ApiException extends RuntimeException {

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public ApiException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }
}
