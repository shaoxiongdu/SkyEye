package cn.shoxiongdu.SkyEyeSystem.response.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RespENUM {

    SUCCESS(200, "操作成功"),

    CLIENT_ERROR_AUTH_NOT_LOGIN(401, "未登陆"),
    CLIENT_ERROR_AUTH_LOGIN(402, "登陆失败"),
    CLIENT_ERROR_AUTH_OVERDUE(403, "登陆过期"),
    CLIENT_ERROR_PARAM(405, "参数校验失败"),

    SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
