package org.gra4j.gazelleExample.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Administrator on 2017/12/8.
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
public class AopConfiguration {
}
