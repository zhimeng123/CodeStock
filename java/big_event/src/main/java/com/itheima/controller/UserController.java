package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            String md5String = Md5Util.getMD5String(password);
            userService.register(username, md5String);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 用户登录
        User loginUser = userService.findByUserName(username);
        if(loginUser==null){
            return Result.error("用户名不存在");
        }
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims= new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());

            String token = JwtUtil.genToken(claims);
            // 返回JWT令牌
            return Result.success(token);
        }else{
            return Result.error("密码错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
        // 根据用户名查询用户
        // Map<String, Object> map = JwtUtil.parseToken(token);
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User u = userService.findByUserName(username);
        return Result.success(u);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){  // @RequestBody 将前端返回的值以User格式保存
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String OldPassword = params.get("old_pwd");
        String NewPassword = params.get("new_pwd");
        String RePassword = params.get("re_pwd");

        if(!StringUtils.hasLength(OldPassword) || !StringUtils.hasLength(NewPassword) || !StringUtils.hasLength(RePassword)){
            return Result.error("缺少必要参数");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User u = userService.findByUserName(username);
        if(!Md5Util.getMD5String(OldPassword).equals(u.getPassword())){
            return Result.error("旧密码错误");
        }

        if(!NewPassword.equals(RePassword)){
            return Result.error("两个新密码不同");
        }

        userService.updatePwd(NewPassword);
        return Result.success();
    }
}
