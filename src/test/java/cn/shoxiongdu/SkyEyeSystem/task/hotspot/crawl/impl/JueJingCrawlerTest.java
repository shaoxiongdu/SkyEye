package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JueJingCrawlerTest {
    
    @Autowired
    JueJingCrawler jueJingCrawler;
    
    @Test
    void test() {
        jueJingCrawler.crawlHotSpotData();
    }
    
}