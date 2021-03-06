package com.prisonapp.business.controller.dw_system;


import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_system.GetUpdateInfoModel;
import com.prisonapp.business.entity.dw_system.GetUpdateRecordsModel;
import com.prisonapp.business.entity.dw_system.ResultGetUpdateRecordsModel;
import com.prisonapp.business.service.dw_supervise.SuperviseService;
import com.prisonapp.business.service.dw_system.SystemService;
import com.prisonapp.token.tation.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="app更新信息",tags={"应用级别信息"})
@RestController
@RequestMapping("/app/system")
public class SystemController {
    @Autowired
    private SystemService systemService;

    private ResultGetUpdateRecordsModel resultGetUpdateRecordsModel= new ResultGetUpdateRecordsModel();

    @ApiOperation(value = "获取保外人员 App 的更新信息")
    @GetMapping("/getUpdateInfo")
    public ResultSet getUpdateInfo(){
        ResultSet result = new ResultSet();
       GetUpdateInfoModel getUpdateInfoModels = systemService.getUpdateInfo();
        result.resultCode=0;
        result.resultMsg="";
        result.data=getUpdateInfoModels;
        return result;
    }

    @ApiOperation(value = "获取保外人员 App 的更新记录")
    @GetMapping("/getUpdateRecords")
    public ResultSet getUpdateRecords(int count,int requestCount){
        ResultSet result = new ResultSet();
        List<GetUpdateRecordsModel> getUpdateRecordsModels = systemService.getUpdateRecords(count, requestCount);
        int a = systemService.gettotalUpdateRecords().size();
        resultGetUpdateRecordsModel.setTotalCount(a);
        resultGetUpdateRecordsModel.setList(getUpdateRecordsModels);
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultGetUpdateRecordsModel;
        return result;
    }
}
