package cn.shoxiongdu.SkyEyeSystem.entity.hot;

import cn.shoxiongdu.SkyEyeSystem.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * 热点类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HotSpot extends BaseEntity {

    /**
     * 平台id
     */
    private long platformId = -1;

    /**
     * 排序
     */
    @TableField("`rank`")
    private int rank = -1;

    /**
     * 关键字
     */
    private String keyword = "";

    /**
     * 链接
     */
    private String url = "";

    /**
     * 封面
     */
    private String image = "null";

    /**
     * 热度
     */
    private int hotValue = 0;
}
