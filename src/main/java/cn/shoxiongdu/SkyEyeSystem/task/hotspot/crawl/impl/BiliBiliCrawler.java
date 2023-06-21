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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class BiliBiliCrawler implements HotDataCrawler {

    public static final Long PLATFORM_ID = 7L;

    private static final String SEARCH_URL_PREFIX = "https://search.bilibili.com/all?keyword=";

    private static final String API_URL = "https://app.bilibili.com/x/v2/search/trending/ranking";

    private static final Integer DEFAULT_PAGE_SIZE = 10;

    PlatformMapper platformMapper;

    @Override
    public List<HotSpot> crawlHotSpotData() {

        HttpResponse response = HttpRequest.get(API_URL + "?limit=" + DEFAULT_PAGE_SIZE)
                .execute();

        if (response.getStatus() != 200) {
            log.error("BiliBili req error: " + JSONUtil.toJsonStr(response));
            return Collections.emptyList();
        }

        JSONObject respJsonObj = JSONUtil.parseObj(new String(response.bodyBytes()));


        return respJsonObj.getJSONObject("data").getJSONArray("list")
                .stream().map(item -> {
                    JSONObject jsonObj = (JSONObject) item;
                    HotSpot hotSpot = new HotSpot();
                    hotSpot.setRank(jsonObj.getInt("position"));
                    hotSpot.setKeyword(jsonObj.getStr("keyword"));
                    hotSpot.setImage(Objects.isNull(jsonObj.getStr("icon")) ? "null" : jsonObj.getStr("icon"));
                    hotSpot.setHotValue(jsonObj.getInt("hot_id"));
                    hotSpot.setUrl(SEARCH_URL_PREFIX + hotSpot.getKeyword());
                    return hotSpot;
                }).collect(Collectors.toList());
    }

    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(PLATFORM_ID);
    }
}
