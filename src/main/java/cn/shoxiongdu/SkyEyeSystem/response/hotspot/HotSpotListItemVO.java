package cn.shoxiongdu.SkyEyeSystem.response.hotspot;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
public class HotSpotListItemVO {
    private Platform platform;
    private List<HotspotItem> hotSpotList;

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    public static class HotspotItem extends HotSpot {
        private String dateFormatAgo = "";
    }
}



