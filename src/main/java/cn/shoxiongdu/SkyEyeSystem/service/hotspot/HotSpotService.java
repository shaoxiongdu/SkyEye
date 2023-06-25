package cn.shoxiongdu.SkyEyeSystem.service.hotspot;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.request.hotspot.HotSpotListReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;

public interface HotSpotService extends IService<HotSpot> {

    Page<HotSpot> listHotSpot(HotSpotListReq req);
    
    void wordCloud(Long platformId, HttpServletResponse resp);
    
}
