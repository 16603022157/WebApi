package com.admin.admin.controller.dw_log;

import com.admin.admin.service.dw_address.AddressService;
import com.admin.admin.service.dw_log.LogService;
import com.admin.model.Appstatistics.AppStatistics;
import com.admin.model.log.LogParamModel;
import com.admin.page.PageBean;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.mustachejava.Code;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Api(value = "操作日志controller", tags = {"查看操作日志"})
@RestController
@RequestMapping("/Log")
public class LogController {
    @Autowired
    private LogService logService;

    @Autowired
    private AddressService addressService;
    private ResponseResult result = new ResponseResult();


    //    @ApiOperation(value = "增加日志")
////    @PostMapping("/addLog")
////    public ResponseResult insertLog(@RequestBody LogInformation logInformation, HttpServletResponse response) {
////        result.setCode(ResultCode.SUCCESS.getCode());
////        result.setMessage(ResultCode.SUCCESS.getMessage());
////        return result.setData(logService.insertLog(logInformation));
////    }
    @UserLoginToken
    @ApiOperation(value = "查询日志信息")
    @PostMapping("/getLog")
    public ResponseResult listLog(@RequestBody LogParamModel logParamModel, HttpServletResponse response) {
        PageBean pageBean = logService.listLog(logParamModel);
        if (pageBean.getItems().size() <= 0) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageBean);


    }

    @UserLoginToken
    @ApiOperation(value = "统计取保监居APP使用人数")
    @GetMapping("/Statistics")
    public ResponseResult getNumber(@RequestParam String code, @RequestParam int level, String Days) {
        List<AppStatistics> numberList = new ArrayList<AppStatistics>();
        List<Map<String, String>> addlist = new ArrayList<>();
        List<Map<String, String>> policelist = new ArrayList<>();
        List<Map<String, String>> Stationlist = new ArrayList<>();
        if (level == 1) {
            addlist = addressService.getAddress(code, level);
            for (Map<String, String> item : addlist) {
                AppStatistics model = new AppStatistics();
                for (String Key : item.keySet()) {
                    String Areacode = item.get("code");
                    Areacode = Areacode.substring(0, 6);
                    model = logService.getNumber(Areacode, Days);
                    model.setCode(item.get("code"));
                    model.setName(item.get("name"));
                }
                numberList.add(model);
            }
        } else if (level == 2) {
            addlist = addressService.getAddress(code, 1);
            for (Map<String, String> item : addlist) {
                for (String key : item.keySet()) {
                    policelist = addressService.getAddress(item.get("code").substring(0, 6), 2);
                    for (Map<String, String> str : policelist) {
                        Stationlist.add(str);
                    }
                }
            }
            for (Map<String, String> item : Stationlist) {
                AppStatistics model = new AppStatistics();
                for (String Key : item.keySet()) {
                    String Areacode = item.get("code");
                    Areacode = Areacode.substring(0, 8);
                    model = logService.getNumber(Areacode, Days);
                    model.setCode(item.get("code"));
                    model.setName(item.get("name"));
                }
                numberList.add(model);
            }
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(numberList);
    }

}
