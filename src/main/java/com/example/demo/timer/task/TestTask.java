package com.example.demo.timer.task;

import cn.hutool.cron.task.Task;
import org.springframework.stereotype.Component;

/**
 * 测试task
 * @author Administrator
 */

@Component
public class TestTask implements Task {

    @Override
    public void execute() {
        System.out.println("--------------------------123-------------------------------");
    }
}
