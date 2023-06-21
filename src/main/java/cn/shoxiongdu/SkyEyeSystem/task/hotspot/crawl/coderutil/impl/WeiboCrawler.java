package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.coderutil.impl;

import cn.hutool.json.JSONObject;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.HotDataCrawler;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.coderutil.AbstractCoderUtilCrawler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class WeiboCrawler extends AbstractCoderUtilCrawler implements HotDataCrawler {

    public static final Long PLATFORM_ID = 2L;

    PlatformMapper platformMapper;

    @Override
    public String getUrl() {
        return "https://www.coderutil.com/api/resou/v1/weibo";
    }

    @Override
    public List<HotSpot> crawlHotSpotData() {
        return getCoderUtilData();
    }


    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(PLATFORM_ID);
    }

    @Override
    public HotSpot parseHotSpot(JSONObject jsonObject) {
        HotSpot hotSpot = new HotSpot();
        hotSpot.setRank(jsonObject.getInt("rank"));
        hotSpot.setKeyword(jsonObject.get("keyword").toString());
        hotSpot.setUrl(jsonObject.get("url").toString());
        hotSpot.setHotValue( Objects.isNull(jsonObject.getInt("hotValue")) ? 0 : jsonObject.getInt("hotValue"));
        return hotSpot;
    }

}
