package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShaoShuPaiCrawlerTest {
    
    @Autowired
    ShaoShuPaiCrawler shaoShuPaiCrawler;
    
    @Test
    void test() {
        List<HotSpot> hotSpots = shaoShuPaiCrawler.crawlHotSpotData();
        System.out.println(hotSpots);
    }
    
}