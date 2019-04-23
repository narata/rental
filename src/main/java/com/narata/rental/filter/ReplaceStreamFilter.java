package com.narata.rental.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narata.rental.config.RequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author narata
 * @since 2019/04/18
 */

@Slf4j
public class ReplaceStreamFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Replace Stream Filter初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = new RequestWrapper((HttpServletRequest) request);
        log.info("param: {}", new ObjectMapper().writeValueAsString(requestWrapper.getParameterMap()));
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        log.info("Replace Stream Filter销毁...");
    }
}
