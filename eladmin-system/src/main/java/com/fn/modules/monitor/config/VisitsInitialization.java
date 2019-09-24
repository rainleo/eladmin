package com.fn.modules.monitor.config;

import com.fn.modules.monitor.service.VisitsService;
import com.fn.modules.monitor.service.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化站点统计
 * @author leo
 */
@Component
public class VisitsInitialization implements ApplicationRunner {

    @Autowired
    private VisitsService visitsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("--------------- 初始化站点统计，如果存在今日统计则跳过 ---------------");
        visitsService.save();
        System.out.println("--------------- 初始化站点统计完成 ---------------");
    }
}