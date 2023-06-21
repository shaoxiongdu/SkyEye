package cn.shoxiongdu.SkyEyeSystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@AllArgsConstructor
public class SwaggerConfig {

     private Environment environment;
     private ServerProperties serverProperties;

    @Bean
    OpenAPI openAPI(){

        String appName = environment.getProperty("spring.application.name","");

        Integer port = serverProperties.getPort();
        port = Objects.isNull(port) ? 8080 : port;

        String contextPath = serverProperties.getServlet().getContextPath();
        contextPath = Objects.isNull(contextPath) ? "" : contextPath;

        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .description(String.format("%s API", appName))
                        .version("1.0.0"));
    }
}
