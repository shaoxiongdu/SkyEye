package cn.shoxiongdu.SkyEyeSystem.request.hotspot;

import cn.shoxiongdu.SkyEyeSystem.request.base.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HotSpotListReq extends PageRequest {

    private Long startTimestamp;

    private Long endTimestamp;

    private int platform_id;
}
