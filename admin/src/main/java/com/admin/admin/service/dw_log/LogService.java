package com.admin.admin.service.dw_log;

import com.admin.admin.dao.master.dw_log.LogDao;

import com.admin.model.Appstatistics.AppStatistics;
import com.admin.model.log.LogModel;
import com.admin.model.log.LogParamModel;
import com.admin.model.log.LogReturnModel;
import com.admin.model.log.LogSearchModel;
import com.admin.page.PageBean;
import com.common.common.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogService {
    @Autowired
    private LogDao logDao;
    private ResponseResult result = new ResponseResult();

//    public int insertLog(LogInformation logInformation){
//        return logDao.insertLog(logInformation);
//    }

    public PageBean<LogReturnModel> listLog(LogParamModel logParamModel) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(logParamModel.getPageIndex(), logParamModel.getPageSize());

        List<LogReturnModel> allItems = logDao.listLog(logParamModel);
        PageInfo<LogReturnModel> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<LogReturnModel> pageData = new PageBean<>(logParamModel.getPageIndex(), logParamModel.getPageSize(), countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);

        return pageData;
    }

    public AppStatistics getNumber(String Areacode, String Days){
        return  logDao.getNumber(Areacode,Days);
    }

    public List<Map<String,String>>  Removalrate(String Areacode, String Starttime, String Endtime){
        return logDao.Removalrate(Areacode,Starttime,Endtime);
    }

    public int gettotelnumber(String Areacode){
        return logDao.gettotelnumber(Areacode);
    }

    public List<AppStatistics> Solarrate(String Code,String StartTime,String EndTime,int level){
        return logDao.Solarrate(Code,StartTime,EndTime,level);
    }

    public List<LogModel> listAllLog(LogSearchModel logSearchModel){
        return logDao.listAllLog(logSearchModel);
    }

    //删除日志
    public int deleteLog(int id){
        return logDao.deleteLog(id);
    }

}

