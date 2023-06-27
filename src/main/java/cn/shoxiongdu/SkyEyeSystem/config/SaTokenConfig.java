package cn.shoxiongdu.SkyEyeSystem.config;

import cn.dev33.satoken.thymeleaf.dialect.SaTokenDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenConfig {
    //
    
    /**
     * Sa-Token 标签方言 (Thymeleaf版)
     */
    @Bean
    public SaTokenDialect getSaTokenDialect() {
        return new SaTokenDialect();
    }
}