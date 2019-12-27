package com.admin.admin.controller.dw_userrole;

import com.admin.admin.entity.dw_menu.Menu;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.admin.service.dw_userrole.UserRoleService;
import com.admin.model.userrole.UserRoleModel;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;

import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Api(value="用户权限管理Controller",tags={"用户权限管理"})
@RestController
@RequestMapping("/UserRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;


    private ResponseResult result = new ResponseResult();


    @ApiOperation("新增用户权限")
    @PostMapping("/AddUserRole")
    public ResponseResult saveUserRole(@RequestBody UserRole userRole, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.saveUserRole(userRole));
    }

    @ApiOperation("修改用户权限")
    @PostMapping("/UpdateUserRole")
    public ResponseResult updateUserRole(@RequestBody UserRole userRole, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userRoleService.updateUserRole(userRole));
    }

    @ApiOperation("删除用户权限")
    @GetMapping("/DelUserRole")
    public ResponseResult deleteUserRole(@RequestParam boolean flag, @RequestParam int UserRoleId, HttpServletResponse response) {
        if (flag == true) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'flag'输入错误");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userRoleService.deleteUserRole(flag, UserRoleId));
    }


    @ApiOperation("菜单")
    @GetMapping("/GetList")
    public ResponseResult<Menu> listMenu(@RequestParam int UserId, HttpServletResponse response) {
        if (userRoleService.listMenu(UserId).isEmpty()) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("该用户没有任何权限,请联系管理员");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userRoleService.listMenu(UserId));
    }

    @ApiOperation("用户权限列表")
    @GetMapping("/GetRoleList")
    public ResponseResult<UserRoleModel> listUserRole(@RequestParam int id, int PageSize, int PageIndex, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.listUserRole(id, PageSize, PageIndex));
    }
}
