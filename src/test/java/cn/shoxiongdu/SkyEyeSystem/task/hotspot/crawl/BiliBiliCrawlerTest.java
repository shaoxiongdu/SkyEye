package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl.BiliBiliCrawler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BiliBiliCrawlerTest {

    @Autowired
    BiliBiliCrawler biliBiliCrawler;

    @Test
    void test() {
        List<HotSpot> hotSpots = biliBiliCrawler.crawlHotSpotData();
        System.out.println(hotSpots);
    }

}
