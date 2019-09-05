package org.csu.tank.base;

import java.util.Objects;

/**
 * 所有前后端分类的请求，返回这个包装类
 */
public class Response extends BaseObject {
    /**
     * 自定义响应码
     */
    private Integer code;
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 正常情况下，对没有响应数据的情况一个解释
     * 出错情况下，前端显示文案
     */
    private String msg;

    private static final Integer OK = 200;
    private static final Integer BAD_REQUEST = 400;
    private static final Integer NOT_FOUND = 404;
    private static final Integer UNAUTHORIZED = 401;
    private static final Integer INTERNAL_SERVER_ERROR = 500;

    @java.beans.ConstructorProperties({"code", "data", "msg"})
    public Response(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Response() {
    }

    public boolean isSuccess() {
        return Objects.equals(OK, code);
    }

    public static Response success(){
        return new Response(OK, null, "OK");
    }
    public static Response success(Object data){
        return new Response(OK, data, "OK");
    }

    public static Response success(Object data, String msg){
        return new Response(OK, data, msg);
    }

    public static Response fail(String msg) {
        return new Response(BAD_REQUEST, null, msg);
    }

    public static Response fail(Object data, String msg){
        return new Response(BAD_REQUEST, data, msg);
    }
    public static Response failForNotFound(Object data, String msg){
        return new Response(NOT_FOUND, data, msg);
    }
    public static Response fail(Integer code, Object data, String msg){
        return new Response(code, data, msg);
    }

    public static Response failForUnauthorized(String msg) {
        return new Response(UNAUTHORIZED, null, msg);
    }
    public Integer getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
