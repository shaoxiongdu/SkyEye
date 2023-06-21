package cn.shoxiongdu.SkyEyeSystem.entity.hot;

import cn.shoxiongdu.SkyEyeSystem.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("hot_platform")
public class Platform extends BaseEntity {

    /**
     * 平台名称
     */
    private String name;

    /**
     * 图标
     */
    private String iconUrl;

    /**
     * 平台描述
     */
    private String description;

    /**
     * 官网
     */
    private String website;

    /**
     * 口号
     */
    private String slogan;

    /**
     * 创始人
     */
    private String founder;

}
