package cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import cn.shoxiongdu.SkyEyeSystem.task.hotspot.crawl.HotDataCrawler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: ShaoxiongDu <email@shaoxiongdu.cn>
 * @date: 2023/6/21 : 09:03
 * @describe:
 */
@Component
@AllArgsConstructor
@Slf4j
public class ZhiHuCrawler implements HotDataCrawler {
    
    private static final Long PLATFORM_ID = 3L;
    
    private static final String URL = "https://www.zhihu.com/hot";
    
    private PlatformMapper platformMapper;
    
    @Override
    public List<HotSpot> crawlHotSpotData() {
        
        HttpResponse response = HttpRequest.get(URL).header("cookie",
                        "_xsrf=XCt6wddDIXXabnO57E6JWvfC7tAq4XFZ; _zap=6de604b0-1513-4b94-ab9a-6c386db8978c; d_c0=AJBZ1iih5xaPTrJywbPJq8HhA9ly8vRMsSQ=|1686281516; __snaker__id=8yo2uJ2BPbNVZ9Ge; YD00517437729195%3AWM_TID=Nmz%2Bf%2BSk8otARUBARRKR1I7h8jBg7khr; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1686882745,1686883993,1687226878,1687309316; captcha_session_v2=2|1:0|10:1687310239|18:captcha_session_v2|88:USs0dElWZ3hwc1VjU0d1SnRyaDUrWVoxRHdGejNhdFFBNy83VXh2MCtZWGwwU0VIZjNlUmY4STBRQXVMRDhvZw==|ebecb89a6832e9c56d11e37aff710c86f66740bfa6bb95c489b7c19971589bd3; YD00517437729195%3AWM_NI=9gDN7sc%2Fqe93swcxG9IcYG5b5eoJqdzf5WwowzfLTImONNkjzUgXsspfyepmpHb6dN0utI1C%2BMxmiM%2BwcEpmWh%2FxPEUF4lBsKhIoDKy2ibTNdMGBNGBpx2c7OTPBqyJfS3k%3D; YD00517437729195%3AWM_NIKE=9ca17ae2e6ffcda170e2e6eed3dc54879b8dd3d57bb0b88fb6d54e929f9bb0d1749491ab88d6339c88ff8ee62af0fea7c3b92afbb98cafb679a1f1bf86bc39b1a9ad99c6338bbd97a8b86af7bba1abf470babafbb8f665b5a6aaadd743af9182afea62ba8ba9b9f279b4bab786f367b498aed7e746f4abbcaab449e993b88eec47e9a9ada6f36795b8e199d34f979afc8dc17989b88790c741bbeea783d746a89389a3cd4d82b0b8d3e23d8d8ca0a3f4738aba9dd4ea37e2a3; gdxidpyhxdE=xaxCKr4Cl4CR0%2FUhMy9Q03iqsng4Mjl7EzNHpom%5CWxywDZsHsgU6mcCTqmjr6MlyfwXd96wrPMOwzlJAQ9E6P%2BNIIDKlzBTxnPykkLkbIpKaBOS0obZc6llavKxjlbk%2Fm%5COwC1N3si%2FZkeKMCyZdAYxJMWkSSWU%5C3MWJ72zcrjR07yqG%3A1687311143432; captcha_ticket_v2=2|1:0|10:1687310250|17:captcha_ticket_v2|704:eyJ2YWxpZGF0ZSI6IkNOMzFfRlB3V0RIZlBKOUpjWmFQWFlPOFNIMXdDQldZNmZxeGFZOC0yR1o0QzFmR3hVN0Rtd1pXSXBDblplMGNubm80VTBVVUxFRHJ2d3ZNNC53Wmd3eExxS1BsenZtODQtWi1lQy1kazFGckFmdHQ2LVhsbGV2VUZrcHZuMTVzLVM4RmppQkVRYTl6QmZFWkplVnhwUERnUW9oai1mTVVSaFZ3bFgwd24tcVdIdm1ydkFqTC1lcGt3NnJ0eS1DTm16LVAwMnh6VzluYUpOWTJaenkyRDZOd2I3OUZWTy41Z1AtdTVVVTFRQURkUmY1ZlhVTW9jUFFoTnZBNHZNNHNiNk5rUndETW51RFZLNllXNVFWeDhEYmExRVJuemR5NzFRWE1STWtmVVZYdERVVFM0ajgxZWFnOGJmaGtuQTk5blE5aWlVQ0thYnZJdm54a2Mudjd6TkpXcUU2NGVpaFNoVWdCNWlHeC42MDRDcVVGLnJiLlcueFVfZ2txRnlVdGQ0dFAxTVZ2S2VYSXBTcWVFSE4tckVzdllMSmF4ZDdCTGgteWJRQjZSSkdOT1hjejZzQ3kuWWdRLkpQWXFEdy5idURkX3dWVVJ1WUx0LlFVak1xbENlLXJWWE5Bem9zRzRBenpWVGpHWk5uXy1za0x5YUFRRWRPTVR1OVpsSG5GMyJ9|4c96757c9a52927c5fe4e9910107d8f42f99d18d22943cb3957284a079179214; z_c0=2|1:0|10:1687310273|4:z_c0|92:Mi4xd0toNUJBQUFBQUFBa0ZuV0tLSG5GaVlBQUFCZ0FsVk53WjFfWlFDR0dUcER6ME5ybERxX1RZalBnanBybWN0azZ3|9af909518a8ed48ace3decfd6acd94fac37e71db3fff0af850d9457b992702e6; q_c1=6e2c58dada3e434db111d2615441a20c|1687310273000|1687310273000; tst=h; SESSIONID=AYHAeXJMMkgyaVQ0FAoH4nINF45kGG37oW99fZ7CXt1; KLBRSID=fb3eda1aa35a9ed9f88f346a7a3ebe83|1687310572|1687308451; JOID=UlwRAkwu359NqNgdGS_cAsKg5fELa4bSCsqEV1FksPgxz4lIKcaKDiir3BgaYGqZZCDj2YCR0UFKy8H0Kx_R0Bo=; osd=Ul8cA0Iu3JJMptgeFC7SAsGt5P8LaIvTBMqHWlBqsPs8zodIKsuLACio0RkUYGmUZS7j2o2Q30FJxsD6Kxzc0RQ=; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1687310577")
                .execute();
        
        if (response.getStatus() != 200) {
            log.error("ZhiHu req error: " + JSONUtil.toJsonStr(response));
            return Collections.emptyList();
        }
        
        List<HotSpot> hotSpotList = new ArrayList<>();
        
        Pattern hotValuePattern = Pattern.compile("\\d+");
        
        Jsoup.parse(response.body()).body().getElementsByClass("HotItem").forEach(hotItem -> {
            
            HotSpot hotSpot = new HotSpot();
            hotSpot.setRank(Integer.parseInt(hotItem.select(".HotItem-rank").text()));
            
            // 只保留10条热搜记录
            if (hotSpot.getRank() <= 10) {
                hotSpot.setUrl(hotItem.select("a").attr("href"));
                hotSpot.setKeyword(hotItem.select("a").attr("title"));
                
                Matcher matcher = hotValuePattern.matcher(hotItem.select(".HotItem-metrics").text());
                if (matcher.find()) {
                    hotSpot.setHotValue(Integer.parseInt(matcher.group()));
                }
                
                hotSpot.setImage(hotItem.select("img").attr("src"));
                hotSpotList.add(hotSpot);
            }
            
        });
        
        return hotSpotList;
    }
    
    @Override
    public Platform getPlatform() {
        return platformMapper.selectById(PLATFORM_ID);
    }
}
