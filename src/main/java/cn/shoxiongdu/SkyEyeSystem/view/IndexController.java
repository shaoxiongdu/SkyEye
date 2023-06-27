package cn.shoxiongdu.SkyEyeSystem.view;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.HotSpotService;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.PlatformService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {
    
    HotSpotService hotSpotService;
    
    PlatformService platformService;
    
    @GetMapping("/")
    public String home(Model model) {
        
        List<Platform> platformList = platformService.list();
        
        List<IndexHotSpotItemVO> hotspotList = new ArrayList<>();
        platformList.forEach(platform -> {
            
            List<HotSpot> lastTenMinutesHotspot = hotSpotService.findLastTenByPlatformId(platform.getId());
            
            if (!lastTenMinutesHotspot.isEmpty()) {
                hotspotList.add(new IndexHotSpotItemVO(platform, lastTenMinutesHotspot));
            }
        });
        model.addAttribute("list", hotspotList);
        return "index";
    }
    
    @Data
    @AllArgsConstructor
    public static class IndexHotSpotItemVO {
        
        private Platform platform;
        
        private List<HotSpot> hotSpotList;
    }
    
}
