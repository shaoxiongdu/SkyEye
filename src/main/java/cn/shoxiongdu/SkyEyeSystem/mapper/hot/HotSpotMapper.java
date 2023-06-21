package cn.shoxiongdu.SkyEyeSystem.mapper.hot;

import cn.shoxiongdu.SkyEyeSystem.entity.hot.HotSpot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotSpotMapper extends BaseMapper<HotSpot> {
    // 可以在此处添加RoleMapper特定的方法

    @Delete("delete from hot_spot where keyword=#{keyword}")
    public void deleteByKeyword(String keyword);
}