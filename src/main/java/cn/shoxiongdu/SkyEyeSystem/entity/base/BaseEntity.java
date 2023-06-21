package cn.shoxiongdu.SkyEyeSystem.entity.base;

import cn.shoxiongdu.SkyEyeSystem.config.JsonSerializerConfig;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: email@shaoxingdu.cn
 * <p>
 * subClass annotation:
 * <ul>
 *     <li>@EqualsAndHashCode(callSuper = true)</li>
 *     <li>@Data</li>
 *     <li>@ToString(callSuper = true)</li>
 * </ul>
 */
@Data
public class BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = JsonSerializerConfig.class)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    @JsonSerialize(using = JsonSerializerConfig.class)
    private LocalDateTime updateTime;

    @TableLogic
    @TableField
    @JsonIgnore
    private Integer deleted = 0;
}
