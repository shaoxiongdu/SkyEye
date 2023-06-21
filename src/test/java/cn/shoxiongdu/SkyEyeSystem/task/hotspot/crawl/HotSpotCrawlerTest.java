package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HotSpotCrawlerTest {

    @Autowired
    CrawlerTask crawlerTask;

    @Test
    void test() {

        crawlerTask.crawl();


    }





}
