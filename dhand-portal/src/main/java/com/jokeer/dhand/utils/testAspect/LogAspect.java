package com.jokeer.dhand.utils.testAspect;


import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.jokeer.dhand.utils.testAspect.Log)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public void doAround(){
        //具体Advice实现
    }


}
