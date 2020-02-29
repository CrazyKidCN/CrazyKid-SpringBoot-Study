package com.crazykid.springboot.study.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 定时任务学习
 * 需要在 SpringBoot 启动类 加上 @EnableScheduling 注解，开启定时任务！！
 * @author meijinyu
 * @date 2020/2/29 14:53
 */
@Slf4j
@Component
public class BootSchedule {

    // 输出时间格式化
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * 上一次开始执行[时间点]之后3000毫秒再执行
     */
    @Scheduled(fixedRate = 3000)
    public void schedule() {
        log.info("schedule -> {}", LocalDateTime.now().format(fmt));
    }

    /**
     * 上一次执行[完毕]时间点之后3s再执行
     */
    @Scheduled(fixedDelay = 3000)
    public void schedule2() {
        log.info("schedule2 -> {}", LocalDateTime.now().format(fmt));
    }

    /**
     * 第一次延迟2秒之后执行，之后按照每3秒执行一次
     */
    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void schedule3() {
        log.info("schedule3 -> {}", LocalDateTime.now().format(fmt));
    }

    /**
     * 定时任务表达式方式(可以搜索引擎查询)
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void schedule4() {
        log.info("schedule4 -> {}", LocalDateTime.now().format(fmt));
    }
}
