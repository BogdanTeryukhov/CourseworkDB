package com.example.courseworkdatabases.aop;

import com.example.courseworkdatabases.annotations.TransactionTimeManagement;
import io.micrometer.common.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Log4j2
@Component
public class TransactionTimeAspect {
    private static final String ENTER = ">> {}";
    private static final String EXIT = "<< {}";
    private static final String TIME = "Time spent: {} nanoseconds";

    @Around("@annotation(logging) && execution(* com.example.courseworkdatabases..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint, TransactionTimeManagement logging) throws Throwable {
        String val = fetchAnnotationValue(joinPoint, logging);

        log.info(ENTER, val);
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        log.info(TIME, System.nanoTime() - start);
        log.info(EXIT, val);
        return result;
    }

    private String fetchAnnotationValue(ProceedingJoinPoint joinPoint, TransactionTimeManagement logging) {
        return Optional.ofNullable(logging)
                .map(TransactionTimeManagement::name)
                .filter(StringUtils::isNotBlank)
                .orElse(joinPoint.getSignature().getName());
    }
}
