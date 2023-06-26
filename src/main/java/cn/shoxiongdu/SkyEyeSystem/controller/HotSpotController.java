package cn.shoxiongdu.SkyEyeSystem.controller;

import cn.hutool.db.nosql.redis.RedisDS;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.request.hotspot.HotSpotListReq;
import cn.shoxiongdu.SkyEyeSystem.response.base.Resp;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.HotSpotService;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.PlatformService;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.statistics.WordCountRedis;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/hotspot")
@Tag(name = "热点管理")
public class HotSpotController {

    @Autowired
    PlatformService platformService;
    @Autowired
    HotSpotService hotSpotService;
    Jedis jedis = RedisDS.create().getJedis();

    @PostMapping("/platform")
    @Operation(summary = "添加平台")
    public Resp<Boolean> addPlatform(@RequestBody Platform platform) {
        return Resp.success(platformService.save(platform));
    }

    @GetMapping("/platform")
    @Operation(summary = "平台列表")
    public Resp<List<Platform>> listPlatform() {
        return Resp.success(platformService.list());
    }

    @GetMapping("/wordCount")
    @Operation(summary = "词频统计")
    public Resp<Map<String, Map<String, String>>> wordCount() {

        Map<String, Map<String, String>> result = new HashMap<>();

        // 全平台
        result.put("全平台", jedis.hgetAll(WordCountRedis.WORD_COUNT_KEY));

        // 分平台
        jedis.keys(WordCountRedis.WORD_COUNT_BY_PLATFORM_KEY + "*")
                .forEach(key -> {
                    String platformName = key.split(":")[key.split(":").length - 1];
                    result.put(platformName, jedis.hgetAll(key));
                });
        return Resp.success(result);
    }

    @PostMapping("/")
    @Operation(summary = "查询热点")
    public Resp<Page<HotSpot>> listHotSpot(@RequestBody HotSpotListReq req) {
        return Resp.success(hotSpotService.listHotSpot(req));
    }
    
    @GetMapping("/word-cloud/{platform_id}")
    @Operation(summary = "词云图")
    public void wordCloud(@PathVariable Long platform_id, HttpServletResponse resp) {
        hotSpotService.wordCloud(platform_id, resp);
    }

}
