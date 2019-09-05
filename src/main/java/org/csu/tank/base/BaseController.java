package org.csu.tank.base;

/**
 * 所有的Controller基于此
 */
public abstract class BaseController {
    protected Response success(){
        return Response.success();
    }
    protected Response success(Object data) {
        return Response.success(data);
    }
    protected Response success(Object data, String msg) {
        return Response.success(data, msg);
    }
    protected Response fail(String msg) {
        return Response.fail(msg);
    }
    protected Response failForNotFound(Object data, String msg) {
        return Response.failForNotFound( data,msg);
    }
}
