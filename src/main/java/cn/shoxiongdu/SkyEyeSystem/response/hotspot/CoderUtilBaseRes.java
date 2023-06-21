package cn.shoxiongdu.SkyEyeSystem.response.hotspot;

import lombok.Data;

@Data
public class CoderUtilBaseRes {
    private String msg;
    private Boolean success;
    private int code;
    private String data;
}
