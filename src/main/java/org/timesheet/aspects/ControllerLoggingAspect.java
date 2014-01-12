/**
 * Project Name:spring-turtorial
 * File Name:ControllerLoggingAspect.java
 * Package Name:org.timesheet.aspects
 * Date:2014-1-11����10:25:05
 * Copyright (c) 2014, https://github.com/seiyaa All Rights Reserved.
 *
 */
package org.timesheet.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ControllerLoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }

    @Pointcut("execution(* *(..))")
    public void methodPointCut() {
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RequestMapping *)")
    public void requestMapping() {
    }

    @Before("controller() && methodPointCut() && requestMapping()")
    public void aroundControllerMethod(JoinPoint joinPoint) {
        System.out.println("Invoked:" + niceName(joinPoint));
    }

    @AfterReturning("controller() && methodPointCut() && requestMapping()")
    public void afterControllerMethod(JoinPoint joinPoint) {
        System.out.println("Finished:" + niceName(joinPoint));
    }

    private String niceName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass()
                + "#" + joinPoint.getSignature().getName()
                + "\n\targs:" + Arrays.toString(joinPoint.getArgs());
    }
}
