package com.prisonapp.business.service.dw_supervise;

import com.prisonapp.business.dao.dw_supervise.SuperviseDao;
import com.prisonapp.business.entity.dw_supervise.*;
import com.prisonapp.business.entity.dw_user.UserModel;
import com.prisonapp.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuperviseService {
    @Autowired
    private SuperviseDao superviseDao ;

    public List<GetApplyLeaveListModel> getApplyLeaveList(String statusCode, int count, int requestCount, String userId){
        return superviseDao.getApplyLeaveList(statusCode,count,requestCount,userId);
    }

    public List<GetApplyLeaveListModel> getAllApplyLeaveList( int count, int requestCount, String userId){
        return superviseDao.getAllApplyLeaveList(count,requestCount,userId);
    }
    //外出申请列表的总数
    public List<SuperviseModel> getTotalApplyLeaveList(String userId){
        return superviseDao.getTotalApplyLeaveList(userId);
    }

    //外出申请列表的第三级
    public List<ApplyRecordModel> applyRecord(String code){
        return superviseDao.applyRecord(code);
    }

    public int submitApplyLeave(String city,    String cityCode,    String district,String districtCode,
                                String province,String provinceCode,String reason,  String reasonAudioUrl ,
                                long   endDate, long startDate,     String code,    String userId,
                                String personName,String sponsorAlarm,String address,String pendingReview,
                                String messageContent){
        //return superviseDao.submitApplyLeave(city,cityCode,district,districtCode,province,provinceCode,reason,reasonAudioUrl,endDate,startDate,code,userId,personName);
        return superviseDao.submitApplyLeave(city,cityCode,district,districtCode,province,provinceCode,reason,reasonAudioUrl,endDate,startDate,code,userId,personName,sponsorAlarm,address,pendingReview,messageContent);
    }

    public TEnum getReview(){
        return superviseDao.getReview();
    }

    public List<TPersoninformation> getPersonname(String userId){
        return superviseDao.getPersonname(userId);
    }


    public List<GetSuperviseTaskModel> getSuperviseTask(String userId){
        return superviseDao.getSuperviseTask(userId);
    }

    public List<TPersoninformation> faceRecognize(String userId){
        return superviseDao.faceRecognize(userId);
    }

    public int  insertFaceRecognize(String userId,int type,int result ,String upLoadFaceUrl){
        return superviseDao.insertFaceRecognize(userId,type,result,upLoadFaceUrl);
    }

    public List<FaceRecognizeModel> getFaceRecognize(String userId,int  type){
        return superviseDao.getFaceRecognize(userId,type);
    }

    public  int autoLocation(float latitude,float longitude,int locationType,String address,String userId,Date date,boolean fScope){

        return  superviseDao.autoLocation(latitude, longitude,locationType,address,userId,date,fScope);
    }

    public int uploadLocationError(String errorCode, String errorMsg,String userId,Date date){
        return  superviseDao.uploadLocationError(errorCode, errorMsg,userId,date);
    }

    public int uploadBattery(float percent,String userId,Date date){
        return  superviseDao.uploadBattery(percent,userId,date);
    }

    public int batteryAlarm(String userId,String content){
        return  superviseDao.batteryAlarm( userId,content);
    }

    public TRemindersettings getLocationConfig( ){
        return  superviseDao.getLocationConfig();
    }

//    public  GetSuperviseConfigModel getBatteryConfigTimestamp(String userId){
//        return  superviseDao.getBatteryConfigTimestamp( userId);
//    }
    public  TEnclosure getPolygon(String userId){
        return  superviseDao.getPolygon( userId);
    }

//    public  int updateFscope(String userId,boolean fscope){
//        return  superviseDao.updateFscope( userId,fscope);
//    }
    public int insertFscope(String userId,String content){
        return  superviseDao.insertFscope( userId,content);
    }

    public TPersoninformation RelatedId(String accountName){
        return  superviseDao.RelatedId( accountName);
    }


}
