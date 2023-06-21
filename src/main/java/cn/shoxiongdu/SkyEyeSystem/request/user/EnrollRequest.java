package cn.shoxiongdu.SkyEyeSystem.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnrollRequest {

    @Size(min = 7, max = 16, message = "用户名长度必须在7~16之间")
    private String username = "";

    @Size(min = 7, max = 16, message = "密码长度必须在7~16之间")
    private String password = "";

    @Email(message = "邮箱格式不正确")
    private String email = "";

    @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确")
    private String phone = "";
    private String nickName = "";
}
