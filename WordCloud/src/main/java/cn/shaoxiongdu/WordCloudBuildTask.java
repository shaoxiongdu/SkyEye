package cn.shaoxiongdu;

import cn.hutool.db.nosql.redis.RedisDS;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WordCloudBuildTask {
    
    private static final String FILE_PATH;
    
    private static final Jedis JEDIS = RedisDS.create().getJedis();
    
    private static final Map<String, String> WORD_COUNT_REDIS_KEY_MAP = new HashMap<>(6);
    
    static {
        
        // 文件存放路径
        FILE_PATH = System.getProperty("user.dir") + "/file-workspace/word-could";
        
        WORD_COUNT_REDIS_KEY_MAP.put("hotspot:statistics:wordCount:all","全平台");
        WORD_COUNT_REDIS_KEY_MAP.put("hotspot:statistics:wordCount:platform:BiliBili","BiliBili");
        WORD_COUNT_REDIS_KEY_MAP.put("hotspot:statistics:wordCount:platform:CSDN","CSDN");
        WORD_COUNT_REDIS_KEY_MAP.put("hotspot:statistics:wordCount:platform:今日头条","今日头条");
        WORD_COUNT_REDIS_KEY_MAP.put("hotspot:statistics:wordCount:platform:微博","微博");
        WORD_COUNT_REDIS_KEY_MAP.put("hotspot:statistics:wordCount:platform:百度","百度");
        WORD_COUNT_REDIS_KEY_MAP.put("hotspot:statistics:wordCount:platform:知乎","知乎");
    }
    
    public static void main(String[] args) {
        
        new Thread(() -> {

            while (true) {
                WORD_COUNT_REDIS_KEY_MAP.forEach((key, value) -> {
                    Map<String, String> wordCountMap = JEDIS.hgetAll(key);
                    WordCloud.build(wordCountMap, FILE_PATH, value + ".png");
                });
                try {
                    Thread.sleep(10 * 60 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            

        }).start();
    }
}
