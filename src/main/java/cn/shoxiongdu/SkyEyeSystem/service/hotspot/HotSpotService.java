package cn.shoxiongdu.SkyEyeSystem.service.hotspot;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.request.hotspot.HotSpotListReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface HotSpotService extends IService<HotSpot> {
    
    Page<HotSpot> listHotSpot(HotSpotListReq req);
    
    void wordCloud(Long platformId, HttpServletResponse resp);
    
    /**
     * 通过平台id获取最近十分钟的热点
     *
     * @param platformId 平台id
     * @return 热点列表
     */
    List<HotSpot> findLastTenMinutesByPlatformId(Long platformId);
}
