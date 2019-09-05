package org.csu.tank.exception;


import org.csu.tank.base.Response;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author louyy
 * @date Created on 2019/8/29
 */
@RestControllerAdvice
public class GeneralExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(value = ApiException.class)
    public Response handleApiException(ApiException e) {
        return new Response(e.getCode(), null, e.getMsg());
    }


    @ExceptionHandler(value = Exception.class)
    public Response handleUnknownException(Exception e) {
        return Response.fail(e.getMessage());
    }


}
