package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.HotDataCrawler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @date: 2023/6/28: 10:15
 * @author: email@shaoxiongdu.cn
 * @description: 腾讯新闻
 */
@AllArgsConstructor
@Component
@Slf4j
public class QQNewCrawler implements HotDataCrawler {
    
    public static final Long PLATFORM_ID = 11L;
    
    private static final String URL = "\n" + "https://r.inews.qq.com/web_feed/getPCList";
    
    private PlatformMapper platformMapper;
    
    @Override
    public List<HotSpot> crawlHotSpotData() {
        
        String requestBody = "{\"qimei36\":\"0_84ad5789e5677\",\"forward\":\"2\",\"base_req\":{\"from\":\"pc\"},\"flush_num\":1,\"channel_id\":\"news_news_tech\",\"device_id\":\"0_84ad5789e5677\",\"is_local_chlid\":\"\"}";
        
        HttpResponse response = HttpRequest.post(URL).body(requestBody).execute();
        
        if (response.getStatus() != 200) {
            log.error("腾讯新闻 req error: " + JSONUtil.toJsonStr(response));
            return Collections.emptyList();
        }
        
        List<HotSpot> hotSpotList = new ArrayList<>();
        JSONArray jsonArray = JSONUtil.parseObj(response.body()).getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = (JSONObject) jsonArray.get(i);
            if (Strings.isBlank(jsonObj.getStr("update_time"))) {
                continue;
            }
            String imgUrl = jsonObj.getJSONObject("pic_info").getStr("big_img");
            
            HotSpot hotSpot = HotSpot.builder().keyword(jsonObj.getStr("title"))
                    .url(jsonObj.getJSONObject("link_info").getStr("url"))
                    .image(imgUrl.substring(2, imgUrl.length() - 2)).rank(i).build();
            hotSpotList.add(hotSpot);
        }
        return hotSpotList;
    }
    
    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(PLATFORM_ID);
    }
}
