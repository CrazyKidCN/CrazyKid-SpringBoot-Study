package com.crazykid.springboot.study.controller;

import com.crazykid.springboot.study.config.SpringBootConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/springboot")
public class Controller {
    @Value("${crazykid.springboot.version}")
    private String version;
    @Value("${crazykid.springboot.name}")
    private String name;

    @Autowired
    private SpringBootConfig springBootConfig;

    /**
     * 使用 @Value 进行数据注入
     * localhost:8080/springboot/firstConfInject
     */
    @GetMapping("/firstConfInject")
    public void firstConfInject() {
        log.info("version: {}  name: {}", version, name);
    }

    /**
     * 创建配置类并使用注解 @ConfigurationProperties 来注入
     * localhost:8080/springboot/secondConfInject
     */
    @GetMapping("/secondConfInject")
    public void secondConfInject() {
        log.info("version: {}  name: {}", springBootConfig.getVersion(), springBootConfig.getName());
    }
}
