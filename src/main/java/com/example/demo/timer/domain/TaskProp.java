package com.example.demo.timer.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * task 配置
 * @author wenx
 */
@Data
@ConfigurationProperties(prefix = "task")
@Component
public class TaskProp {

    private List<TaskDetail> list;

    @Data
    public static class TaskDetail {

        private int id;
        private String cron;
        private String className;
        private boolean started;
    }

}
