package org.csu.tank.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.csu.tank.domain.Account;

import org.csu.tank.exception.ApiException;
import org.csu.tank.exception.ExceptionEnum;
import org.csu.tank.service.AccountService;
import org.csu.tank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author louyy
 * @date Created on 2019/8/29
 */
@Component
public class TokenValidationInterceptor implements HandlerInterceptor {
    private static String[] EXClUDE_PATH = {"/login", "/register"};

    @Value("${jwt.token.check}")
    private Boolean checkToken;

    @Resource
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (!checkToken) {
                return true;
            }
            String tokenId = request.getHeader("token");
            for (String path : EXClUDE_PATH) {
                if (request.getRequestURI().contains(path)) {
                    return true;
                }
            }
            if (tokenId == null || "".equals(tokenId)) {
                throw new ApiException(ExceptionEnum.TOKEN_CHECK_FAIL_EXCEPTION);
            } else {
                JSONObject jsonObject = JwtUtil.parseObject(tokenId);
                String username = (String) jsonObject.get("sub");
                Account account = accountService.getAccountByUsername(username);
                if (account == null) {
                    throw new ApiException(ExceptionEnum.USER_NOT_EXIST);
                }
                return true;
            }
        } catch (Exception e) {
          throw new ApiException(ExceptionEnum.TOKEN_CHECK_FAIL_EXCEPTION);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
