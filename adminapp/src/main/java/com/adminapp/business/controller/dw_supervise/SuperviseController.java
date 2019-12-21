package com.adminapp.business.controller.dw_supervise;

import com.adminapp.business.entity.dw_supervise.Personinformation;
import com.adminapp.business.entity.dw_supervise.ReportSettingsInformation;
import com.adminapp.business.entity.dw_supervise.SinginInformation;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.config.CacheUtils;
import com.adminapp.model.dw_supervise.PrisonSettingModel;
import com.adminapp.model.dw_supervise.ReportLocationModel;
import com.adminapp.model.dw_supervise.SuperviseReturnModel;
import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/app/admin/supervise")
public class SuperviseController {

    @Autowired
    private SuperviseService superviseService;

    private ResultSet rs=new ResultSet();

    @ApiOperation(value = "获取违规记录统计")
    @GetMapping("/getAgainstRule")
    public ResultSet getAgainstRule(@ApiParam(name = "type",value = "类别") @RequestParam(required = true)int type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,
                                    @ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = true)String key){
        int faceSingin=0;      //人脸签到违规次数
        int voiceSingin=0;     //声纹签到违规次数
        int locationViolateCount=0;   //位置违规次数
        int faceSinginTimes=0;    //上报设置中人脸签到的次数
        int voiceSinginTimes=0;   //上报设置中声纹签到的次数
        String userId= CacheUtils.get("UserId").toString();        //获取工作人员id
        List<Personinformation> personinformation=superviseService.listPersonInformation(userId);  //获取该工作人员负责的所有监居人员列表
        int totalCount=personinformation.size();
        SuperviseReturnModel superviseReturnModel=new SuperviseReturnModel();
        superviseReturnModel.setTotalCount(totalCount);
        List<ReportSettingsInformation> reportSettingsInformations=superviseService.listReportSetting();  //获取上报设置列表
        for(int a=0;a<reportSettingsInformations.size();a++){
            ReportSettingsInformation reportSettingsInformation=reportSettingsInformations.get(a);
            if(reportSettingsInformation.getType()==2){
                faceSinginTimes=reportSettingsInformation.getReportcount();   //获取上报设置中人脸签到的次数
            }
            if(reportSettingsInformation.getType()==3){
                voiceSinginTimes=reportSettingsInformation.getReportcount();  //获取上报设置中声纹签到的次数
            }
        }
        for(int i=0;i<personinformation.size();i++){
            Personinformation personinformation1=personinformation.get(i);
            String personid=personinformation1.getCode();
            List<ReportLocationModel> reportLocationModels=superviseService.listLocation(personid);     //获取位置信息列表

            for(int j=0;j<reportLocationModels.size();j++){         //计算位置定位违规次数
                ReportLocationModel reportLocationModel=reportLocationModels.get(j);
                boolean Fscope=reportLocationModel.isFscope();
                if(!reportLocationModel.isFscope()){     //判断定位位置是否在范围内
                    locationViolateCount+=1;
                }
            }
            List<SinginInformation> singinFaceInformations=superviseService.listSingin(personid,0);  //获取人脸签到次数
            faceSingin=faceSinginTimes-singinFaceInformations.size();
            if(faceSingin<0){
                faceSingin=0;
            }
            List<SinginInformation> singinVoiceInformation=superviseService.listSingin(personid,1);  //获取声纹签到次数
            voiceSingin=voiceSinginTimes-singinVoiceInformation.size();
            if(voiceSingin<0){
                voiceSingin=0;
            }
        }
        if(type==0){       //所有人员列表
            superviseReturnModel.setList(personinformation);
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=superviseReturnModel;
        }
        if(type==1){       //最近新增人员
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);   //设置当前时间
            long time1=cal.getTimeInMillis();
            cal.add(Calendar.MONTH, -1);  //在当前时间基础上减一个月
            long time2=cal.getTimeInMillis();
            long days=(time2-time1)/(1000*60*60*24);
            Date lastDate=cal.getTime();    //取得上一个月日期
            List<Personinformation> recentPerson=new ArrayList<>();
            for(Personinformation item: personinformation){
                Date bailoutbegindate=item.getExecStartDate();
                if(lastDate.getTime()<bailoutbegindate.getTime()){    //用监居开始时间与一月前时间比较，大于则为新增人员
                    recentPerson.add(item);
                }
            }
            for(int h=0;h<recentPerson.size();h++){

            }
            superviseReturnModel.setList(recentPerson);
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=superviseReturnModel;
        }
        if(type==2){
            List<Personinformation> bailoutPerson=new ArrayList<>();
            for(Personinformation item: personinformation){
                String suspectStatus=item.getState();
                if(suspectStatus.equals("取保候审")){
                    bailoutPerson.add(item);
                }
            }
            superviseReturnModel.setList(bailoutPerson);
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=superviseReturnModel;
        }
        if(type==3){
            List<Personinformation> bailoutWatchPerson=new ArrayList<>();
            for(Personinformation item: personinformation){
                String suspectStatus=item.getState();
                if(suspectStatus.equals("监视居住")){
                    bailoutWatchPerson.add(item);
                }
            }
            superviseReturnModel.setList(bailoutWatchPerson);
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=superviseReturnModel;
        }
        return rs;
    }
}
