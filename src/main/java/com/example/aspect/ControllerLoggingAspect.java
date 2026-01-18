package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.MDC;

import java.util.UUID;

@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger log =
            LoggerFactory.getLogger(ControllerLoggingAspect.class);
    private static final String CORRELATION_ID = "CID";

    @Pointcut("execution(* com.example.controller..*(..))")
    public void controllerLayer() {}

    @Pointcut("execution(* com.example.client..*(..))")
    public void clientLayer() {}

    @Before("controllerLayer() || clientLayer()")
    public void logControllerMethods(JoinPoint joinPoint) {

        // 1️⃣ Get CID from MDC (set earlier by filter). CID var should be same as mentioned in yaml file
        String cid = MDC.get(CORRELATION_ID);

        // 2️⃣ If missing, generate one (fallback safety)
        if (cid == null) {
            cid = UUID.randomUUID().toString();
            MDC.put(CORRELATION_ID, cid);
        }

        // 3️⃣ Extract class & method
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        // 4️⃣ Log with MDC context
        log.info(" Entering {}.{}", className, methodName);
    }
}
