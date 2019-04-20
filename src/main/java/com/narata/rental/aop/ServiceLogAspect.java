package com.narata.rental.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author narata
 * @since 2019/04/12
 */

@Slf4j
@Aspect
@Configuration
public class ServiceLogAspect {
    /**
     * 定义切点
     */
    @Pointcut("execution(* com.narata.rental.service.*Service.*(..))")
    public void executeService() {
    }

    @AfterThrowing(pointcut = "executeService()", throwing = "error")
    public void doAfterThrowing(JoinPoint jp, Throwable error) {
        log.error("-------------------------------------------------------------------------------------------");
        log.error("Method Signature: {}", jp.getSignature());
        log.error("Exception: {}", error.toString());
        log.error("-------------------------------------------------------------------------------------------");
    }
}
