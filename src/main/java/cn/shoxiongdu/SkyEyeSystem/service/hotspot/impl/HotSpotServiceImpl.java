package cn.shoxiongdu.SkyEyeSystem.service.hotspot.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import cn.shoxiongdu.SkyEyeSystem.entity.hot.Platform;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.HotSpotMapper;
import cn.shoxiongdu.SkyEyeSystem.mapper.hot.PlatformMapper;
import cn.shoxiongdu.SkyEyeSystem.request.hotspot.HotSpotListReq;
import cn.shoxiongdu.SkyEyeSystem.response.hotspot.HotSpotListItemVO;
import cn.shoxiongdu.SkyEyeSystem.service.hotspot.HotSpotService;
import cn.shoxiongdu.SkyEyeSystem.utils.ResponseUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotSpotServiceImpl extends ServiceImpl<HotSpotMapper, HotSpot> implements HotSpotService {

    HotSpotMapper hotSpotMapper;
    PlatformMapper platformMapper;

    @Override
    public Page<HotSpot> listHotSpot(HotSpotListReq req) {

        Page<HotSpot> page = new Page<>(req.getPageNumber(), req.getPageSize());

        return hotSpotMapper.selectPage(page,
                new LambdaQueryWrapper<HotSpot>()
                        .ge(req.getStartTimestamp() != 0, HotSpot::getCreateTime, LocalDateTime.ofInstant(Instant.ofEpochMilli(req.getStartTimestamp()), ZoneId.systemDefault()))
                        .le(req.getEndTimestamp() != 0, HotSpot::getCreateTime, LocalDateTime.ofInstant(Instant.ofEpochMilli(req.getEndTimestamp()), ZoneId.systemDefault()))
                        .eq(req.getPlatform_id() != 0, HotSpot::getPlatformId, req.getPlatform_id())
                        .orderByDesc(HotSpot::getCreateTime));
    }

    @Override
    public void wordCloud(Long platformId, HttpServletResponse resp) {
        Platform platform = platformMapper.selectById(platformId);
        String wordCloudFilePath =
                System.getProperty("user.dir") + "/file-workspace/word-could/" + platform.getName() + ".png";

        ResponseUtils.writeFile(new File(wordCloudFilePath), resp, platform.getId() + ".png");
    }

    @Override
    public List<HotSpot> findLastTenByPlatformId(Long platformId) {

        List<HotSpot> hotSpotList = page(new Page<>(1, 15),
                new LambdaQueryWrapper<HotSpot>().eq(HotSpot::getPlatformId, platformId)
                        .orderByDesc(HotSpot::getCreateTime)).getRecords();

        return hotSpotList.stream().sorted(Comparator.comparingInt(HotSpot::getRank)).collect(Collectors.toList());
    }

    @Override
    public List<HotSpotListItemVO> listForIndex() {

        List<HotSpotListItemVO> hotspotListVO = new ArrayList<>();

        platformMapper.selectList(null).forEach(platform -> {

            List<HotSpot> lastTenMinutesHotspot = findLastTenByPlatformId(platform.getId());

            if (!lastTenMinutesHotspot.isEmpty()) {

                List<HotSpotListItemVO.HotspotItem> hotspotList = lastTenMinutesHotspot.stream().map(hotSpot -> {

                    long between = ChronoUnit.MINUTES.between(hotSpot.getCreateTime(), LocalDateTime.now());
                    HotSpotListItemVO.HotspotItem hotspotItem = new HotSpotListItemVO.HotspotItem(between > 0 ? between + "分钟前" : "刚刚");
                    BeanUtil.copyProperties(hotSpot, hotspotItem);
                    return hotspotItem;

                }).toList();

                hotspotListVO.add(new HotSpotListItemVO(platform, hotspotList));
            }
        });
        return hotspotListVO;
    }
}
