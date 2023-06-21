package cn.shoxiongdu.SkyEyeSystem.mapper;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.user.User;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.coderutil.impl.WeiboCrawler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class hotSpotMapperTest {

    @Autowired
    WeiboCrawler weiboCrawler;

    @Test
    void test() {
        List<HotSpot> coderUtilData =
                weiboCrawler.getCoderUtilData();
        System.out.println();
    }

    User makeUser(){
      User user = new User();
      user.setEmail("email@shaoxiongdu.cn");
      user.setPhone("15603430511");
      user.setPassword("123456");
      return user;
    }

}
