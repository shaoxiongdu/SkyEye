package cn.shoxiongdu.SkyEyeSystem.task.hotspot.statistics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WordCountRedisTest {

    @Autowired
    WordCountRedis wordCountRedis;

    @Test
    void test() {
        wordCountRedis.wordFrequency("上述代码首先创建一个 Jedis 实例，并使用 incrBy 方法来增加指定键的值。incrBy 方法接受两个参数，第一个参数是要增加值的键（key），第二个参数是要增加的具体值。该方法返回增加后的新值。", 2L);
    }
}
