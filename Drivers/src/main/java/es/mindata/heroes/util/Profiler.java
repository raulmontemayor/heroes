package es.mindata.heroes.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class Profiler {


    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void monitor() {}

    @Around("monitor()")
    public Object profile(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        Object output = null;
        try {
            output = pjp.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        long elapsedTime = System.currentTimeMillis() - start;
        log.debug(pjp.getTarget()+"."+pjp.getSignature()+": Execution time: " + elapsedTime + " ms. ("+ elapsedTime/60000 + " minutes)");

        return output;
    }
}
