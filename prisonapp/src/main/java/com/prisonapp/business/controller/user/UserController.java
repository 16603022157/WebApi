package com.prisonapp.business.controller.user;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.admin.TokenModel;
import com.prisonapp.business.entity.user.User;
import com.prisonapp.business.service.user.UserService;
import com.prisonapp.token.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/app/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    private ResultSet result = new ResultSet();
    private TokenModel tokenModel=new TokenModel();
    @ApiOperation(value = "登录")
    @PostMapping("/login")//
    public ResultSet login(@RequestParam(required = false) String account, @RequestParam(required = false)  String password, HttpServletResponse response){
      String token="aslancanclsfnacasnsnsdlahksdg";

        tokenModel.setToken(token);
        tokenModel.setrExpiresTime(token);
        tokenModel.setRefreshToken(token);
        tokenModel.settExpiresTime(token);
       result.resultCode=0;
       result.resultMsg="";
       result.data=tokenModel;
       return result;
    }
}