package com.cyl.example.base.auth;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cyl.example.dao.UserInfoMapper;
import com.cyl.example.dto.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationFilter implements Filter {
    private UserInfoMapper userInfoMapper;

    @Autowired
    public AuthenticationFilter(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("request filter start......");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String accessToken = request.getHeader("Authorization");
        if (Strings.isBlank(accessToken)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "get account from header error");
            return;
        }

        long startTime = System.currentTimeMillis();
        try {
            //将头信息中Authorization转换为userId和password
            UserContext userContext = JSON.parseObject(accessToken, UserContext.class);
            log.info("获取的用户信息为{}", JSONObject.toJSONString(userContext));
            int i = userInfoMapper.countByUsernameAndPassword(
                    userContext
                            .getUserName(), userContext.getPassword()
            );
            if (i == 0) {
                log.info("request valid fail ......");
                return;
            }
            log.info("request valid success ......");
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("TOKEN异常 token:{}", accessToken, e);
        }
    }
}
