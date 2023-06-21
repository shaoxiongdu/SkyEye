package cn.shoxiongdu.SkyEyeSystem.mapper.user;

import cn.shoxiongdu.SkyEyeSystem.entity.user.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    // 可以在此处添加PermissionMapper特定的方法
}