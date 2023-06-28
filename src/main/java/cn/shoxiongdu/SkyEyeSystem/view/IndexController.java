package cn.shoxiongdu.SkyEyeSystem.view;

import cn.shoxiongdu.SkyEyeSystem.service.hotspot.HotSpotService;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.PlatformService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    HotSpotService hotSpotService;

    PlatformService platformService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("hotspotListVo", hotSpotService.listForIndex());
        return "index";
    }

}
