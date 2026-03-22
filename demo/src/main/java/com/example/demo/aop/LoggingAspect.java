package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.demo.controllers..*(..)) || execution(* com.example.demo.services..*(..))")
    public void logBefore(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("START METHOD: " + method);
    }

    @After("execution(* com.example.demo.controllers..*(..)) || execution(* com.example.demo.services..*(..))")
    public void logAfter(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("END METHOD: " + method);
    }

    @AfterReturning(
            pointcut = "execution(* com.example.demo.controllers..*(..)) || execution(* com.example.demo.services..*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("METHOD SUCCESS: " + method);
        System.out.println("RETURN VALUE: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.demo.controllers..*(..)) || execution(* com.example.demo.services..*(..))",
            throwing = "exception"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("ERROR IN METHOD: " + method);
        System.out.println("EXCEPTION: " + exception.getMessage());
    }

    @Around("execution(* com.example.demo.services..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        String method = joinPoint.getSignature().toShortString();

        System.out.println("EXECUTION TIME for " + method + ": " + (end - start) + " ms");

        return result;
    }
}