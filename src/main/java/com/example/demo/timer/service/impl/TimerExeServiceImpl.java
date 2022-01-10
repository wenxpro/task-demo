package com.example.demo.timer.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import com.example.demo.timer.service.TimerExeService;
import com.example.demo.timer.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * hutool方式的定时任务执行
 * @author Administrator
 */
@Service
@Slf4j
public class TimerExeServiceImpl implements TimerExeService {

    @Override
    public void startTimer(String taskId, String cron, String className) throws ClassNotFoundException {

        if (ObjectUtil.hasEmpty(taskId, cron, className)) {
            log.error("请检查定时器的id，定时器cron表达式，定时任务是否为空！taskId：{}",taskId);
            return;
        }

        // 预加载类看是否存在此定时任务类
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("定时任务执行类不存在！taskId：{}",taskId);
            return;
        }

        // 定义hutool的任务
        Task task = (Task) SpringUtils.getBean(Class.forName(className));

        // 开始执行任务
        CronUtil.schedule(taskId, cron, task);
    }

    @Override
    public void stopTimer(String taskId) {
        CronUtil.remove(taskId);
    }

}
