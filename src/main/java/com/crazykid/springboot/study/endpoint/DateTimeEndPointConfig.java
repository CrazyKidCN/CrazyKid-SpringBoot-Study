package com.crazykid.springboot.study.endpoint;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 自定义端点配置类
 * @author meijinyu
 * @date 2020/2/29 21:38
 */
@Configuration
public class DateTimeEndPointConfig {
    @Bean
    @ConditionalOnMissingBean // 当这个bean缺少的时候才会注入这个bean
    @ConditionalOnEnabledEndpoint // 当这个监控端点被开启的时候才会将自定义的EndPoint注入到应用程序中
    public DateTimeEndPoint dateTimeEndPoint() {
        return new DateTimeEndPoint();
    }
}
