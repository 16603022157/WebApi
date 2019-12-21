package com.adminapp.business.service.dw_supervise;

import com.adminapp.business.dao.dw_supervise.SuperfineDado;
import com.adminapp.business.entity.dw_supervise.Personinformation;
import com.adminapp.business.entity.dw_supervise.ReportSettingsInformation;
import com.adminapp.business.entity.dw_supervise.SinginInformation;
import com.adminapp.model.dw_supervise.PrisonSettingModel;
import com.adminapp.model.dw_supervise.ReportLocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperviseService {

    @Autowired

    private SuperfineDado superfineDado;

    //获取所有监居人员列表
    public List<Personinformation> listPersonInformation(String UserId){
        return superfineDado.listPersonInformation(UserId);
    }

    //获取监居设置列表
    public List<PrisonSettingModel> getPrisonSetting(String personid){
        return superfineDado.getPrisonSetting(personid);
    }

    //获取位置信息列表
    public List<ReportLocationModel> listLocation(String personid){
        return superfineDado.listLocation(personid);
    }

    //获取上报设置列表
    public List<ReportSettingsInformation> listReportSetting(){
        return superfineDado.listReportSetting();
    }

    //获取人脸或声纹签到数据
    public List<SinginInformation> listSingin(String personid,int type){
        return superfineDado.listSingin(personid, type);
    }
}