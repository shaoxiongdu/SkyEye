package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl;

import cn.hutool.json.JSONObject;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;

import java.util.List;

/**
 * 爬取接口
 */
public interface HotDataCrawler {

    /**
     * 爬取数据
     * @return
     */
    List<HotSpot> crawlHotSpotData();

    /**
     * json对象转HotSpot
     *
     * @param jsonObject
     * @return
     */
    default HotSpot parseHotSpot(JSONObject jsonObject) {
        return new HotSpot();
    }

    /**
     * 获取当前平台
     *
     * @return
     */
    public Platform getPlatform();

}
