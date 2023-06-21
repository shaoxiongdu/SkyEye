package cn.shoxiongdu.SkyEyeSystem.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.shoxiongdu.SkyEyeSystem.entity.user.User;
import cn.shoxiongdu.SkyEyeSystem.request.user.EnrollRequest;
import cn.shoxiongdu.SkyEyeSystem.request.user.LoginRequest;
import cn.shoxiongdu.SkyEyeSystem.response.base.Resp;
import cn.shoxiongdu.SkyEyeSystem.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "用户管理")
public class UserController {

    UserService userService;

    @GetMapping("/")
    @Operation(summary = "用户列表")
    @SaCheckLogin
    public Resp<List<User>> getUserList() {
        return Resp.success(userService.list());
    }

    @PostMapping("/login")
    @Operation(summary = "登陆")
    public Resp<SaTokenInfo> login(@RequestBody LoginRequest requestBody, HttpServletRequest request) {
        return userService.login(requestBody, request);
    }

    @PostMapping("/enroll")
    @Operation(summary = "注册")
    public Resp<Boolean> enroll(@Valid @RequestBody EnrollRequest request) {
        return userService.enroll(request);
    }


    @Operation(summary = "检查登陆状态")
    @GetMapping("/login/isLogin")
    public Resp<Boolean> isLogin() {
        return Resp.success(StpUtil.isLogin());
    }

    @GetMapping("/tokenInfo")
    @Operation(summary = "查询token信息")
    public Resp<SaTokenInfo> tokenInfo() {
        return Resp.success(StpUtil.getTokenInfo());
    }

    @PostMapping("/logout")
    @Operation(summary = "退出登陆")
    public Resp<Boolean> logout() {
        StpUtil.logout();
        return Resp.success(true);
    }

    @GetMapping("/permissions")
    @SaCheckLogin
    @Operation(summary = "权限列表")
    public Resp<List<String>> permissions() {
        return Resp.success(StpUtil.getPermissionList());
    }

    @GetMapping("/info")
    @SaCheckLogin
    @Operation(summary = "用户信息")
    public Resp<User> info() {
        return Resp.success(userService.getById(StpUtil.getLoginId(0L)));
    }

}
