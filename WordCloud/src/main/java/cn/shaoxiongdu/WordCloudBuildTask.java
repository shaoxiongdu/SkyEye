package cn.shaoxiongdu;

import cn.hutool.db.nosql.redis.RedisDS;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class WordCloudBuildTask {
    
    private static final String FILE_PATH;
    
    private static final Jedis JEDIS = RedisDS.create().getJedis();
    
    private static final Map<String, String> WORD_COUNT_REDIS_KEY_MAP = new HashMap<>(6);
    
    static {
        
        // 文件存放路径
        FILE_PATH = System.getProperty("user.dir") + "/file-workspace/word-could";
        
        // 词频统计的redisKey和文件名映射
        JEDIS.keys("hotspot:statistics:wordCount*").forEach(key -> {
            String[] split = key.split(":");
            WORD_COUNT_REDIS_KEY_MAP.put(key, split[split.length - 1]);
        });
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
