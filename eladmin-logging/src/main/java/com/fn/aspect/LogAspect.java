package com.fn.aspect;

import com.fn.domain.Log;
import com.fn.service.LogService;
import com.fn.utils.RequestHolder;
import com.fn.utils.SecurityUtils;
import com.fn.utils.StringUtils;
import com.fn.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import com.fn.domain.Log;
import com.fn.exception.BadRequestException;
import com.fn.service.LogService;
import com.fn.utils.RequestHolder;
import com.fn.utils.SecurityUtils;
import com.fn.utils.StringUtils;
import com.fn.utils.ThrowableUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author leo
 * @date 2019-09-24
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogService logService;

    private long currentTime = 0L;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.fn.aop.log.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        currentTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        Log log = new Log("INFO",System.currentTimeMillis() - currentTime);
        logService.save(getUsername(), StringUtils.getIP(RequestHolder.getHttpServletRequest()),joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR",System.currentTimeMillis() - currentTime);
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e).getBytes());
        logService.save(getUsername(), StringUtils.getIP(RequestHolder.getHttpServletRequest()), (ProceedingJoinPoint)joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getUsername();
        }catch (Exception e){
            return "";
        }
    }
}
