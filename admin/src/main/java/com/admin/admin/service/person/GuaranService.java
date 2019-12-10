package com.admin.admin.service.person;

import com.admin.admin.dao.person.GuarantDao;
import com.admin.admin.entity.person.GuaranteeInformation;
import com.admin.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuaranService {
    @Autowired
    private GuarantDao guarantDao;

    //新增
    public int insertGuarant(GuaranteeInformation guaranteeinformation){
        return guarantDao.insertGuarant(guaranteeinformation);
    }
    //修改
    public int updateGuara(GuaranteeInformation guaranteeinformation){
        return guarantDao.updateGuara(guaranteeinformation);
    }
    //删除
    public int deleteGuara(ParamterModel paramterModel){return guarantDao.deleteGuara(paramterModel);}

    //获取
    public GuaranteeInformation getGuara(int id){return guarantDao.getGuara(id); }
}