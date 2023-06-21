package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.coderutil;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.response.hotspot.CoderUtilBaseRes;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.HotDataCrawler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class AbstractCoderUtilCrawler implements HotDataCrawler {

    private static final String ACCESS_KEY = "06d855455c89c1d8f0aa14482394cc68";
    private static final String SECRET_KEY = "95eca0c70f1e35f3236e15e248bda267";

    private static final int DEFAULT_SIZE = 10;

    public static int size = DEFAULT_SIZE;

    /**
     * 公用的爬取数据
     * @return
     */
    public List<HotSpot> getCoderUtilData() {

        // 发起请求
        HttpRequest request = HttpRequest.get(getUrl() + "?size=" + size)

                .header("access-key", ACCESS_KEY)
                .header("secret-key", SECRET_KEY);

        HttpResponse response = request.execute();

        if (response.getStatus() != 200) {
            log.error(getPlatform().getName() + " req error: " + JSONUtil.toJsonStr(response));
            return Collections.emptyList();
        }

        // 处理响应
        String respStr = new String(response.bodyBytes());
        CoderUtilBaseRes coderUtilBaseRes = JSONUtil.toBean(respStr, CoderUtilBaseRes.class);

        List<HotSpot> hotSpotList = new ArrayList<>();
        if (!coderUtilBaseRes.getSuccess()) {
            return hotSpotList;
        }

        JSONArray hotSpotArray = JSONUtil.parseArray(coderUtilBaseRes.getData());

        // 转为hotSpot对象
        hotSpotArray.forEach(respItem -> {
            HotSpot hotSpot = parseHotSpot((JSONObject) respItem);
            hotSpotList.add(hotSpot);
        });

        // 返回
        return hotSpotList;
    }

    /**
     * 获取当前平台的爬取地址
     * @return
     */
    public abstract String getUrl();
}
