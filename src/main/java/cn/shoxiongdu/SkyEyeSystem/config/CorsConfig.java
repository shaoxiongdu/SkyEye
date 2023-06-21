package cn.shoxiongdu.SkyEyeSystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Slf4j
public class CorsConfig {

    @Value("${cors.enable:false}")
    private boolean enableCors;

    @Bean
    public CorsFilter corsFilter() {
        if (!enableCors) {
            log.info("cors config not enable");
            return new CorsFilter(new UrlBasedCorsConfigurationSource());
        }
        log.info("cors config enable");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
