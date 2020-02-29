package com.crazykid.springboot.study.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置注入的方式：方式二
 * 创建配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "crazykid.springboot")
public class SpringBootConfig {
    String version;
    String name;
}
