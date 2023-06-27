package cn.shoxiongdu.SkyEyeSystem.config;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
public class ThymeleafConfig {
    
    /**
     * 为 Thymeleaf 注入全局变量，以便在页面中调用 Sa-Token 的方法
     */
    @Autowired
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        viewResolver.addStaticVariable("stpUtil", StpUtil.stpLogic);
    }
}
