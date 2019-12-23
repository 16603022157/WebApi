package com.adminapp.business.controller.dw_supervise;

import com.adminapp.business.entity.dw_supervise.Personinformation;
import com.adminapp.business.entity.dw_supervise.ReportSettingsInformation;
import com.adminapp.business.entity.dw_supervise.SinginInformation;
import com.adminapp.business.entity.dw_supervise.SummonsInformation;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.config.CacheUtils;
import com.adminapp.model.dw_supervise.*;
import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/app/admin/supervise")
public class SuperviseController {

    @Autowired
    private SuperviseService superviseService;

    private ResultSet rs=new ResultSet();

    @ApiOperation(value = "获取保外人员列表")
    @GetMapping("/getAgainstRule")
    public ResultSet getAgainstRule(@ApiParam(name = "type",value = "类别") @RequestParam(required = true)int type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,
                                    @ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = false)String key) {
        List<Personinformation> newPerson = new ArrayList<>();  //符合条件所有监居人员列表
        List<Personinformation> newRecentPerson = new ArrayList<>();  //符合条件的最近新增人员列表
        List<Personinformation> newBailoutPerson = new ArrayList<>(); //符合条件的取保候审人员列表
        List<Personinformation> newBailoutWatchPerson = new ArrayList<>(); //符合条件的监视居住人员列表
        String userId = CacheUtils.get("UserId").toString();        //获取工作人员id
        List<Personinformation> personinformation = superviseService.listPersonInformation(userId);  //获取该工作人员负责的所有监居人员列表
        SuperviseReturnModel superviseReturnModel = new SuperviseReturnModel();
        int totalCount =0;

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        long time1 = cal.getTimeInMillis();
        cal.add(Calendar.MONTH, -1);  //在当前时间基础上减一个月
        Date lastDate = cal.getTime();    //取得上一个月日期
        List<Personinformation> recentPerson = new ArrayList<>();
        for (Personinformation item : personinformation) {
            Date bailoutbegindate = item.getExecStartDate();
            if (lastDate.getTime() < bailoutbegindate.getTime()) {    //用监居开始时间与一月前时间比较，大于则为新增人员
                recentPerson.add(item);
            }
        }long time2 = cal.getTimeInMillis();
        long days = (time2 - time1) / (1000 * 60 * 60 * 24);

        List<Personinformation> bailoutPerson = new ArrayList<>();
        for (Personinformation item : personinformation) {
            String suspectStatus = item.getState();
            if (suspectStatus.equals("取保候审")) {
                bailoutPerson.add(item);
            }
        }

        List<Personinformation> bailoutWatchPerson = new ArrayList<>();
        for (Personinformation item : personinformation) {
            String suspectStatus = item.getState();
            if (suspectStatus.equals("监视居住")) {
                bailoutWatchPerson.add(item);
            }
        }

        int faceSingin = 0;      //人脸签到违规次数
        int voiceSingin = 0;     //声纹签到违规次数
        int locationViolateCount = 0;   //位置违规次数
        int faceSinginTimes = 0;    //上报设置中人脸签到的次数
        int voiceSinginTimes = 0;   //上报设置中声纹签到的次数
        List<ReportSettingsInformation> reportSettingsInformations = superviseService.listReportSetting();  //获取上报设置列表
        for (int a = 0; a < reportSettingsInformations.size(); a++) {
            ReportSettingsInformation reportSettingsInformation = reportSettingsInformations.get(a);
            if (reportSettingsInformation.getType() == 2) {
                faceSinginTimes = reportSettingsInformation.getReportcount();   //获取上报设置中人脸签到的次数
            }
            if (reportSettingsInformation.getType() == 3) {
                voiceSinginTimes = reportSettingsInformation.getReportcount();  //获取上报设置中声纹签到的次数
            }
        }
        for (int i = 0; i < personinformation.size(); i++) {
            Personinformation personinformation1 = personinformation.get(i);
            String personid = personinformation1.getCode();
            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(personid);     //获取位置信息列表

            for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
                ReportLocationModel reportLocationModel = reportLocationModels.get(j);
                boolean Fscope = reportLocationModel.isFscope();
                if (!reportLocationModel.isFscope()) {     //判断定位位置是否在范围内
                    locationViolateCount += 1;
                }
            }
            List<SinginInformation> singinFaceInformations = superviseService.listSingin(personid, 0);  //获取人脸签到次数
            faceSingin = faceSinginTimes - singinFaceInformations.size();
            if (faceSingin < 0) {
                faceSingin = 0;
            }
            List<SinginInformation> singinVoiceInformation = superviseService.listSingin(personid, 1);  //获取声纹签到次数
            voiceSingin = voiceSinginTimes - singinVoiceInformation.size();
            if (voiceSingin < 0) {
                voiceSingin = 0;
            }
        }
        if (key == null) {
            //superviseReturnModel.setTotalCount(totalCount);
            if (type == 0) {       //所有人员列表
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = personinformation.get(h);
                    newPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(personinformation.size());
                superviseReturnModel.setList(newPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if (type == 1) {       //最近新增人员

                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = recentPerson.get(h);
                    newRecentPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(recentPerson.size());
                superviseReturnModel.setList(newRecentPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if (type == 2) {    //取保候审人员

                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = bailoutPerson.get(h);
                    newBailoutPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(bailoutPerson.size());
                superviseReturnModel.setList(newBailoutPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if (type == 3) {     //监视居住人员

                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = bailoutWatchPerson.get(h);
                    newBailoutWatchPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(bailoutWatchPerson.size());
                superviseReturnModel.setList(newBailoutWatchPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            return rs;
        }
        else{   //key关键字不为空
            List<Personinformation> keyPerson=new ArrayList<>();
            List<Personinformation> newKeyPerson=new ArrayList<>();
            if(type==0){
                for (Personinformation item:personinformation ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newPerson.add(personinformation1);
                }
            }
            if(type==1){
                for (Personinformation item:recentPerson ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newKeyPerson.add(personinformation1);
                }
            }
            if(type==2){
                for (Personinformation item:bailoutPerson ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newKeyPerson.add(personinformation1);
                }
            }
            if(type==3){
                for (Personinformation item:bailoutWatchPerson ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newKeyPerson.add(personinformation1);
                }
            }

            superviseReturnModel.setList(keyPerson);
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = superviseReturnModel;
            return rs;
        }
    }

    @ApiOperation(value = "获取保外人员传讯记录")
    @GetMapping("/getCiteRecords")
    public ResultSet getCiteRecords(@RequestParam(required = false)String key,@RequestParam(required = false)String startDate,@RequestParam(required = false)String endDate,
                                    @RequestParam(required = true)int count,@RequestParam(required = true)int requestCount){
        CiteRecordReturnModel citeRecordReturnModel=new CiteRecordReturnModel();
        List<SummonsInformation> summonsInformations=new ArrayList<>();
        if(key==null){
            summonsInformations=superviseService.listCiteRecord();
        }
        else{
            summonsInformations=superviseService.listKeyCiteRecord(key);
        }
        List<SummonsInformation> newSummonsInformations=new ArrayList<>();    //经过时间筛选之后的传讯记录
        if(startDate!=null&&endDate==null){    //开始日期不为空
            for (SummonsInformation item:summonsInformations) {
                if(Long.parseLong(startDate)<(item.getSummontime()).getTime()/1000){
                    newSummonsInformations.add(item);
                }
            }
        }
        if(startDate==null&&endDate!=null){   //结束日期不为空
            for (SummonsInformation item:summonsInformations) {
                if(Long.parseLong(endDate)>(item.getSummontime()).getTime()/1000){
                    newSummonsInformations.add(item);
                }
            }
        }
        if(startDate!=null&&endDate!=null){    //都不为空
            for (SummonsInformation item:summonsInformations) {
                if(Long.parseLong(startDate)<(item.getSummontime()).getTime()/1000&&Long.parseLong(endDate)>(item.getSummontime()).getTime()/1000){
                    newSummonsInformations.add(item);
                }
            }
        }
        if(startDate==null&&endDate==null){  //都为空
            for (SummonsInformation item:summonsInformations) {
                newSummonsInformations.add(item);
            }
        }
        List<CiteRecordsModel> citeRecordsModel=new ArrayList<>();
        for (SummonsInformation item:newSummonsInformations
        ) {
            CiteRecordsModel citeRecordsModel1=new CiteRecordsModel();
            citeRecordsModel1.setName(item.getPersonname());
            long arrivedTime=(item.getSummontime()).getTime()/1000;
            citeRecordsModel1.setArrivedTime(Long.toString(arrivedTime));
            PersonAllInformationModel personinformation=superviseService.getPersonInformation(item.getPersonid());
            citeRecordsModel1.setIdCardNo(personinformation.getCard());
            citeRecordsModel1.setRecordPerson(personinformation.getSponsor());
            citeRecordsModel1.setArrivedUnit(personinformation.getPolicestation());
            citeRecordsModel.add(citeRecordsModel1);
        }

        List<CiteRecordsModel> summonsInformations1=new ArrayList<>();  //取count到count+requestCount之间的记录

        if(citeRecordsModel.size()>count&&citeRecordsModel.size()<count+requestCount){
            for(int i=count;i<citeRecordsModel.size();i++){
                CiteRecordsModel summonsInformation=citeRecordsModel.get(i);
                summonsInformations1.add(summonsInformation);
            }
        }
        if(citeRecordsModel.size()>count+requestCount){
            for(int i=count;i<count+requestCount;i++){
                CiteRecordsModel summonsInformation=citeRecordsModel.get(i);
                summonsInformations1.add(summonsInformation);
            }
        }
        citeRecordReturnModel.setTotalCount(newSummonsInformations.size());
        citeRecordReturnModel.setList(summonsInformations1);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=citeRecordReturnModel;
        return rs;
    }

    @ApiOperation(value = "提交传讯记录")
    @PostMapping("/submitCiteRecord")
    public ResultSet postCiteRecord(@ApiParam(name = "code",value = "保外人员编码")@RequestParam(required = true)String code){
        PersonAllInformationModel personinformation=superviseService.getPersonInformation(code);
        if(personinformation!=null){
            String personName=personinformation.getPersonname();
            Date summonsTime=new Date();
            try {
                CiteRecordSubmitModel citeRecordSubmitModel=new CiteRecordSubmitModel();
                citeRecordSubmitModel.setPersonid(code);
                citeRecordSubmitModel.setPersonname(personName);
                citeRecordSubmitModel.setSummonsTime(summonsTime);
                int summonsId = superviseService.insertCiteRecord(citeRecordSubmitModel);
                CiteRecordSubmitReturnModel citeRecordSubmitReturnModel=new CiteRecordSubmitReturnModel();
                citeRecordSubmitReturnModel.setCode(String.valueOf(citeRecordSubmitModel.getId()));
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = citeRecordSubmitReturnModel;
            }catch (Exception e){
                rs.resultCode=1;
                rs.resultMsg=e.getMessage();
                rs.data=null;
            }
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
        return rs;
    }
}
