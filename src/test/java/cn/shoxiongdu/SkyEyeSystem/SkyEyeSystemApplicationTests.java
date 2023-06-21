package cn.shoxiongdu.SkyEyeSystem;

import cn.shoxiongdu.SkyEyeSystem.redis.RedisHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkyEyeSystemApplicationTests {

    @Autowired
    RedisHelper redisHelper;

    @Test
    void contextLoads() {
        System.out.println(redisHelper.getKeyPrefix());
    }

}
