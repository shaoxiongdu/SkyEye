package cn.shoxiongdu.SkyEyeSystem.task.hotspot.statistics;

import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Component
public class WordCountRedis {

    public static final String WORD_COUNT_KEY = "hotspot:statistics:wordCount:all";
    public static final String WORD_COUNT_BY_PLATFORM_KEY = "hotspot:statistics:wordCount:platform";

    private static final Jedis JEDIS = RedisDS.create().getJedis();

    private static final Map<Long, Platform> platformCacheMap = new HashMap<>();

    @Autowired
    private PlatformMapper platformMapper;

    /**
     * 统计单词出现的次数  wordFrequencyStatistics
     *
     * @param key
     * @return
     */
    public void wordFrequency(String key, Long platformId) {

        // 分词统计
        Map<String, Long> wordCountMap = wordCount(key);

        // 获取平台信息
        Platform platform = getPlatformByIdFromCache(platformId);

        // 存入redis
        save2Redis(wordCountMap, platform);
    }

    /**
     * 从缓存中获取平台信息
     *
     * @param platformId 平台id
     * @return 平台
     */
    private Platform getPlatformByIdFromCache(Long platformId) {

        // 初始化缓存
        if (platformCacheMap.isEmpty()) {
            platformMapper.selectList(null)
                    .forEach(platform -> platformCacheMap.put(platform.getId(), platform));
        }

        return platformCacheMap.get(platformId);
    }

    public Map<String, Long> wordCount(String key) {

        TokenizerEngine engine = TokenizerUtil.createEngine();

        Map<String, Long> wordCount = new HashMap<>();

        Result result = engine.parse(key);
        while (result.hasNext()) {
            String word = result.next().getText();

            if (wordIsFilter(word)) {
                continue;
            }

            wordCount.compute(word, (k, value) -> value == null ? 1 : value + 1);
        }
        return wordCount;
    }

    /**
     * 词频数据存入redis
     *
     * @param wordCount 词频数据
     * @param platform  所属平台
     */
    public void save2Redis(Map<String, Long> wordCount, Platform platform) {
        wordCount.forEach((k, v) -> {

            // 全平台统计
            JEDIS.hincrBy(WORD_COUNT_KEY, k, v);

            // 分平台统计
            JEDIS.hincrBy(WORD_COUNT_BY_PLATFORM_KEY + ":" + platform.getName(), k, v);
        });
    }


    /**
     * 判断词是否需要过滤的工具方法
     * 过滤条件:
     * - null
     * - 空字符串
     * - 单个字符
     * - 数字
     *
     * @param word 要判断的词
     * @return 如果词需要过滤，则返回true；否则返回false
     */
    private boolean wordIsFilter(String word) {
        return StringUtils.isBlank(word) || word.length() < 2 || NumberUtils.isCreatable(word);
    }

}
