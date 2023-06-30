package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.HotDataCrawler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @date: 2023/6/28: 10:15
 * @author: email@shaoxiongdu.cn
 * @description: 36K
 */
@AllArgsConstructor
@Component
@Slf4j
public class K36Crawler implements HotDataCrawler {
    
    public static final Long PLATFORM_ID = 12L;
    
    private static final String URL = "https://duomoyu.com/channels/36kr_24h";
    
    private PlatformMapper platformMapper;
    
    @Override
    public List<HotSpot> crawlHotSpotData() {
        
        HttpResponse response = HttpRequest.get(URL).execute();
        
        if (response.getStatus() != 200) {
            log.error("36k req error: " + JSONUtil.toJsonStr(response));
            return Collections.emptyList();
        }
        
        List<HotSpot> hotSpotList = new ArrayList<>();
        Jsoup.parse(response.body()).getElementsByClass("channel_card").stream().limit(10).forEach(aElement -> {
            System.out.println(aElement);
            HotSpot hotSpot = HotSpot.builder().keyword(aElement.text()).url(aElement.attr("href")).image("").build();
            hotSpotList.add(hotSpot);
        });
        return hotSpotList;
    }
    
    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(PLATFORM_ID);
    }
}
