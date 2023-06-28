package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.HotDataCrawler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @date: 2023/6/28: 10:15
 * @author: email@shaoxiongdu.cn
 * @description: 少数派
 */
@AllArgsConstructor
@Component
@Slf4j
public class ShaoShuPaiCrawler implements HotDataCrawler {
    
    public static final Long PLATFORM_ID = 9L;
    
    private static final String URL = "https://sspai.com/api/v1/article/tag/page/get?limit=10&offset=0&tag=%E7%83%AD%E9%97%A8%E6%96%87%E7%AB%A0&released=false";
    
    private PlatformMapper platformMapper;
    
    @Override
    public List<HotSpot> crawlHotSpotData() {
        
        HttpResponse response = HttpRequest.get(URL).execute();
        
        if (response.getStatus() != 200) {
            log.error("少数派 req error: " + JSONUtil.toJsonStr(response));
            return Collections.emptyList();
        }
        JSONObject respObj = JSONUtil.parseObj(response.body());
        if (respObj.getInt("error") != 0) {
            log.error("少数派 req error: " + response.body());
            return Collections.emptyList();
        }
        
        List<HotSpot> hotSpotList = new ArrayList<>();
        respObj.getJSONArray("data").forEach(dataItemObj -> {
            JSONObject dataItemJsonObj = (JSONObject) dataItemObj;
            HotSpot hotspot = HotSpot.builder().image("https://cdn.sspai.com/" + dataItemJsonObj.getStr("banner"))
                    .keyword(dataItemJsonObj.getStr("title"))
                    .url(String.format("https://sspai.com/post/%s", dataItemJsonObj.getInt("id"))).build();
            hotSpotList.add(hotspot);
        });
        return hotSpotList;
    }
    
    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(PLATFORM_ID);
    }
}
