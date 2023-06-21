package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl.ZhiHuCrawler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: ShaoxiongDu <email@shaoxiongdu.cn>
 * @date: 2023/6/21 : 09:07
 * @describe:
 */
@SpringBootTest
public class ZhiHuCrawlerTest {
    
    @Autowired
    ZhiHuCrawler zhiHuCrawler;
    
    @Test
    void test() {
        List<HotSpot> hotSpots = zhiHuCrawler.crawlHotSpotData();
        System.out.println(hotSpots);
    }
    
}
