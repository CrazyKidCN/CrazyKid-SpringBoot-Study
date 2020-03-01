package com.crazykid.springboot.study.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义异步线程池的配置
 * @author meijinyu
 * @date 2020/3/1 18:33
 */
@Slf4j
@Configuration
public class AsyncPoolConfig implements AsyncConfigurer { // 需要实现该类
    @Bean // 千万别忘了放进spring容器，否则没办法被识别
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 线程池核心线程数量 (默认值1,因此导致线程不能被重用的最主要原因) 同时允许n个客户端同时操作这个线程池
        executor.setMaxPoolSize(20); // 线程池维护的线程的最大数量，当核心线程都被用完且缓冲队列都满了之后才会申请超过核心线程数量的线程
        executor.setQueueCapacity(20); // 缓冲队列任务个数
        executor.setKeepAliveSeconds(60); // 超出核心现成数之外的空闲最大存活时间，超出该时间会被杀掉
        executor.setThreadNamePrefix("crazykidAsync_"); // 线程名前缀

        executor.setWaitForTasksToCompleteOnShutdown(true); // 是否等待所有线程执行完毕后才关闭线程池。默认false，如果关掉程序，会导致未执行完的线程也退出，从而可能出BUG
        executor.setAwaitTerminationSeconds(60); // 等待的时常，默认0(不等待)，程序shutdown之后等待的时间，跟上面的配置一起使用

        // 拒绝策略
        executor.setRejectedExecutionHandler(
                // AbortPolicy: 如果线程池运行的任务满了，而且队列满了，直接抛出异常，abort(废弃)掉
                // DiscardOldestPolicy: 当线程池的数量等于最大线程数,而且队列满，抛弃线程池中最后一个要执行的任务，并执行新传递进来的任务
                // DiscardPolicy: 当线程池的数量等于最大线程数, 不做任何动作，直接丢弃
                new ThreadPoolExecutor.AbortPolicy()
        );

        executor.initialize();
        return executor;
    }

    /**
     * 定义异步任务异常处理类
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

    class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        /**
         *
         * @param throwable 异步处理任务抛出的异常对象
         * @param method 异常处理任务方法
         * @param objects 数组，异常处理服务需要传递进来的参数
         */
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            log.info("AsyncError: {}, Method: {}, Param: {}",
                    throwable.getMessage(),
                    method.getName(),
                    JSON.toJSONString(objects));
            throwable.printStackTrace();

            // 一些逻辑 例如 发送邮件或者短信
        }
    }
}
