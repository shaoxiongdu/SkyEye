package cn.shoxiongdu.SkyEyeSystem.service.user;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.shoxiongdu.SkyEyeSystem.entity.user.User;
import cn.shoxiongdu.SkyEyeSystem.request.user.EnrollRequest;
import cn.shoxiongdu.SkyEyeSystem.request.user.LoginRequest;
import cn.shoxiongdu.SkyEyeSystem.response.base.Resp;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    Resp<SaTokenInfo> login(LoginRequest requestBody, HttpServletRequest request);

    Resp<Boolean> enroll(EnrollRequest request);
}
