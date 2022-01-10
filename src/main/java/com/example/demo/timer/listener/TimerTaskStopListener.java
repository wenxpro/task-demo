package com.example.demo.timer.listener;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.cron.CronUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;



/**
 * @author wenx
 */
@Slf4j
@Component
public class TimerTaskStopListener implements ApplicationListener<ContextClosedEvent> {


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        //结束时停止
//        if(ObjectUtil.isNotEmpty(CronUtil.getScheduler())
//                && CronUtil.getScheduler().isStarted()){
//            CronUtil.stop();
//            log.info("task has stopped");
//        }
    }

}
