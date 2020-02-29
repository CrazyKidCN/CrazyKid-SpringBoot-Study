package com.crazykid.springboot.study.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * actuator学习：自定义事件端点
 * @author meijinyu
 * @date 2020/2/29 21:33
 */
@Endpoint(id = "datetime") // 则访问路径是 上下文/actuator/datetime
public class DateTimeEndPoint {

    private String format = "yyyy-MM-dd HH:mm:ss";

    @ReadOperation // 显示监控指标
    public Map<String, Object> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "crazykid");
        info.put("age", "19");
        info.put("datetime", new SimpleDateFormat(format).format(new Date()));

        return info;
    }

    @WriteOperation // 动态修改监控指标
    public void setFormat(String format) {
        this.format = format;
    }
}
