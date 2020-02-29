package com.crazykid.springboot.study.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "crazykid.springboot")
public class SpringBootConfig {
    String version;
    String name;
}
