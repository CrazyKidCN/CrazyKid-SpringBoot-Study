package com.crazykid.springboot.study.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.el.lang.EvaluationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务学习
 * 异步处理服务
 * 自定义线程池配置 见 {@link com.crazykid.springboot.study.config.AsyncPoolConfig}
 * @author meijinyu
 * @date 2020/3/1 18:02
 */
@Slf4j
@Service
public class AsyncService {

    @Async("getAsyncExecutor") // 标识为异步任务,指定线程池名称
    public void asyncProcess() throws InterruptedException {
        log.info("async process task, current thread name -> {}", Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 具有返回值的方法
     */
    @Async("getAsyncExecutor")
    public Future<Integer> asyncProcessHasReturn() throws InterruptedException {
        log.info("async process task, current thread name -> {}", Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        return new AsyncResult<>(100);
    }
}
