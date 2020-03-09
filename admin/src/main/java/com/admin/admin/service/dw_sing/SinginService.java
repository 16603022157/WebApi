package com.admin.admin.service.dw_sing;

import com.admin.admin.dao.dw_sing.SinginDao;
import com.admin.admin.entity.dw_sing.SinginInformation;
import com.admin.admin.entity.dw_user.User;
import com.admin.model.search.SearchModel;
import com.admin.model.singin.SinginModel;
import com.admin.page.PageBean;
import com.admin.tool.CacheUtils;
import com.admin.tool.JudgementRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinginService {

    @Autowired
    private SinginDao singinDao;

    /*
    列表
     */
    public  List<SinginModel> ListSingin(SearchModel searchModel){
        String limit="";
        int type= JudgementRole.Distinguishroles();
        if (type==1){
            limit= CacheUtils.get("accountname").toString();
        }else{
            limit=CacheUtils.get("PoliceCode").toString();
        }

        return singinDao.ListSingin(searchModel,type,limit);
    }

    /*
    查看详情
     */
    public SinginInformation getSingin(int personId){

        return singinDao.getSingin(personId);
    }

    /*
    音视频
     */
    public  List<SinginModel> ListAudio(SearchModel searchModel){
        String limit="";
        int type= JudgementRole.Distinguishroles();
        if (type==1){
            limit= CacheUtils.get("accountname").toString();
        }else{
            limit=CacheUtils.get("PoliceCode").toString();
        }
        return singinDao.ListAudio(searchModel,type,limit);
    }
}
