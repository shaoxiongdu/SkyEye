package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.HotSpotMapper;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.statistics.WordCountRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CrawlerTask {

    // 所有平台
    @Autowired
    private List<HotDataCrawler> platformCrawlerList;

    @Autowired
    private HotSpotMapper hotSpotMapper;

    @Autowired
    private WordCountRedis wordCountRedis;

    @Scheduled(cron = "0 */10 9-23 * * *") // 每天的 9 点到 23 点之间，每隔十分钟执行一次任务。
    public void crawl() {
        log.info("开始爬取热点数据 ");
        // 循环所有平台
        platformCrawlerList.forEach(crawler -> {

            Platform platform = crawler.getPlatform();

            // 爬取数据
            List<HotSpot> hotSpots = crawler.crawlHotSpotData();
            log.info(platform.getName() + " -> 热点数据: " + hotSpots.size() + "条");

            // 处理数据
            hotSpots.forEach(hotSpot -> {

                // 设置平台id
                hotSpot.setPlatformId(platform.getId());

                // 删除旧数据
                hotSpotMapper.deleteByKeyword(hotSpot.getKeyword());

                // 插入
                hotSpotMapper.insert(hotSpot);

                // 词频统计
                wordCountRedis.wordFrequency(hotSpot.getKeyword(), hotSpot.getPlatformId());
            });
        });
        log.info("爬取热点数据完成");

    }

}
