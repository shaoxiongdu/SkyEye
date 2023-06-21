package cn.shoxiongdu.SkyEyeSystem.service.hotspot.impl;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.HotSpotMapper;
import cn.shoxiongdu.SkyEyeSystem.request.hotspot.HotSpotListReq;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.HotSpotService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class HotSpotServiceImpl extends ServiceImpl<HotSpotMapper, HotSpot> implements HotSpotService {

    HotSpotMapper hotSpotMapper;

    @Override
    public Page<HotSpot> listHotSpot(HotSpotListReq req) {

        Page<HotSpot> page = new Page<>(req.getPageNumber(), req.getPageSize());

        return hotSpotMapper.selectPage(page,
                new LambdaQueryWrapper<HotSpot>()
                        .ge(req.getStartTimestamp() != 0, HotSpot::getCreateTime, LocalDateTime.ofInstant(Instant.ofEpochMilli(req.getStartTimestamp()), ZoneId.systemDefault()))
                        .le(req.getEndTimestamp() != 0, HotSpot::getCreateTime, LocalDateTime.ofInstant(Instant.ofEpochMilli(req.getEndTimestamp()), ZoneId.systemDefault()))
                        .eq(req.getType() != 0, HotSpot::getPlatformId, req.getType()));
    }
}
