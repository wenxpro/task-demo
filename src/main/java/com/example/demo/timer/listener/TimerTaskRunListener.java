package com.example.demo.timer.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.cron.CronUtil;
import com.example.demo.timer.domain.TaskProp;
import com.example.demo.timer.service.TimerExeService;
import com.example.demo.timer.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * timer listener
 * @author wenx
 */
@Slf4j
@Component
public class TimerTaskRunListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {

    @Resource
    TaskProp taskConfig;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        List<TaskProp.TaskDetail> tasks = taskConfig.getList();
        if(CollectionUtil.isEmpty(tasks)){
            log.info("task empty");
            return;
        }

        TimerExeService timerExeService = SpringUtils.getBean(TimerExeService.class);
        log.info("tasks:{}",tasks);
        for (TaskProp.TaskDetail task : tasks) {
            try {
                timerExeService.startTimer(String.valueOf(task.getId()), task.getCron(), task.getClassName());
            }catch (Throwable e){
                log.error("error : {}",e.getMessage());
            }
        }
        // 设置秒级别的启用
        CronUtil.setMatchSecond(true);

        // 启动定时器执行器
        CronUtil.start();
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
