package cn.shoxiongdu.SkyEyeSystem.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resp<T> {
    private int code;
    private String message;
    private T data;
    private boolean success;

    public static <T> Resp<T> success(T data) {
        return new Resp<>(RespENUM.SUCCESS.getCode(), RespENUM.SUCCESS.getMessage(), data, true);
    }

    public static <T> Resp<T> error(int code, String message) {
        return new Resp<>(code, message, null, false);
    }

    public static <T> Resp<T> error(RespENUM respENUM) {
        return new Resp<>(respENUM.getCode(), respENUM.getMessage(), null, false);
    }

    public static <T> Resp<T> error(Throwable t) {
        return new Resp<>(RespENUM.SERVER_ERROR.getCode(), t.getMessage(), null, false);
    }

}
