package cn.shoxiongdu.SkyEyeSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;

@SpringBootApplication
@MapperScan("cn.shoxiongdu.SkyEyeSystem.mapper")
@EnableScheduling
public class SkyEyeSystemApplication {

        private static final String SWAGGER_URI = "swagger-ui/index.html";

        public static void main(String[] args) {
            ApplicationContext application = SpringApplication.run(SkyEyeSystemApplication.class, args);
            printMetaInfo(application);
        }

        private static void printMetaInfo(ApplicationContext application) {

            ServerProperties serverProperties = application.getBean(ServerProperties.class);

            Integer port = serverProperties.getPort();
            port = Objects.isNull(port) ? 8080 : port;


            String contextPath = serverProperties.getServlet().getContextPath();
            contextPath = Objects.isNull(contextPath) ? "" : contextPath;

            String swaggerURL = String.format("http://localhost:%d%s/%s", port, contextPath, SWAGGER_URI);
            System.out.printf("\n\t Local Server DEV: %s\n", swaggerURL);
        }

}
