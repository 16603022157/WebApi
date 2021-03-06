package com.admin.admin.controller.dw_guaran;

import com.admin.admin.entity.dw_guarant.GuaranteeInformation;
import com.admin.admin.service.dw_guaran.GuaranService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(value="保外人员担保信息controller",tags={"保外人员担保信息管理"})
@RestController
@RequestMapping("/Guarant")
public class GuarantController {
    @Autowired
    private GuaranService guaranService;



    @UserLoginToken
    @ApiOperation(value = "新增担保信息")
    @PostMapping("/addGuarant")
    public ResponseResult insertGuarant(@RequestBody GuaranteeInformation guaranteeinformation, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        if(guaranService.getGuaByPersonId(guaranteeinformation.getPersonid())>=1){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("该监居人员以被人担保!");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.insertGuarant(guaranteeinformation));

    }
    @UserLoginToken
    @ApiOperation(value = "修改担保信息")
    @PostMapping("/updateGuara")
    public ResponseResult updateGuara(@RequestBody GuaranteeInformation guaranteeinformation, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.updateGuara(guaranteeinformation));
    }

    @UserLoginToken
    @ApiOperation(value = "删除担保信息")
    @GetMapping("/deleteGuara")
    public ResponseResult deleteGuara(@RequestParam boolean flag,
                                      @RequestParam int GuaId, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData( guaranService.deleteGuara(flag, GuaId));
    }

    @UserLoginToken
    @ApiOperation(value = "查询担保信息")
    @GetMapping("/getGuara")
    public ResponseResult getGuara(@RequestParam String id, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( guaranService.getGuara(id));
    }

}
