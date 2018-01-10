package org.gra4j.gazelleExample.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.gra4j.gazelle.core.Recovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/19.
 */
@Aspect
@Component
public class RecoveryAspect {

    @Autowired
    private Recovery recovery;

    @Pointcut("execution(* org.gra4j.gazelleExample.service..*.*(..))")
    public void point () {}

    @After("point()")
    public void after () {
        recovery.recover();
    }
}
