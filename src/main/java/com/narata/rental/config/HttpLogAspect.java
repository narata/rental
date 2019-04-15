package com.narata.rental.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author narata
 * @since 2019/04/11
 */

@Slf4j
@Aspect
@Configuration
public class HttpLogAspect {
    /**
     * 定义切点
     */
    @Pointcut("execution(* com.narata.rental.controller.*Controller.*(..))")
    public void executeService() {
    }

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        log.info("-------------------------------------------------------------------------------------------");
        log.info("request url: #{}", request.getRequestURL());
        log.info("request from host #{}, addr #{}, port #{}",
                request.getRemoteHost(), request.getRemoteAddr(), request.getRemotePort());
        String params = new ObjectMapper().writeValueAsString(request.getParameterMap());
        log.info("request params #{}", params);
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        log.info("response #{}", result.toString());
        log.info("-------------------------------------------------------------------------------------------");
        return result;
    }

    @AfterThrowing(pointcut = "executeService()", throwing = "error")
    public void doAfterThrowing(JoinPoint jp, Throwable error) {
        log.error("-------------------------------------------------------------------------------------------");
        log.error("Method Signature: {}", jp.getSignature());
        log.error("Exception: {}", error.toString());
        log.error("-------------------------------------------------------------------------------------------");
    }
}
