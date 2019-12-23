package com.admin.admin.controller.dw_Report;

import com.admin.admin.entity.dw_report.Reportsettings;
import com.admin.admin.service.dw_Report.ReportService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("信息上报设置")
@RestController
@RequestMapping("/Report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    private ResponseResult result = new ResponseResult();

    @ApiOperation("保存或者修改上报设置")
    @PostMapping("/SaveOrUpdateReport")
    public ResponseResult SaveOrUpdateReport(@RequestBody Reportsettings reportsettings){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reportService.SaveOrUpdateReport(reportsettings));
    }

    @ApiOperation("删除上报设置")
    @PostMapping("/DeleteReport")
    public ResponseResult DeleteReport( @RequestParam  int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reportService.deleteReport(id));
    }
    @ApiOperation("查看上报设置")
    @PostMapping("getReport")
    public ResponseResult getReport(){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reportService.getReport());
    }

}