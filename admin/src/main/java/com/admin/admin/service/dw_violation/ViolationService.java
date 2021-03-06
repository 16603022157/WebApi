package com.admin.admin.service.dw_violation;

import com.admin.admin.dao.master.dw_violation.ViolationDao;
import com.admin.admin.entity.dw_violation.Violationfens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolationService {

    @Autowired
    private ViolationDao violationDao;

    /*
    新增或者修改
     */
    public int SaveViolation(Violationfens violationfens){
        return   violationDao.SaveViolation(violationfens);


    }

    /*
    删除
     */
    public int deleteViolation(int id,boolean flag){
        return violationDao.deleteViolation(id,flag);
    }

    /*
    查看
     */
//    public Violationfens getViolation(int id){
//        return violationDao.selectViolation(id);
//    }
    /*
    列表
     */

    public List<Violationfens> enabledViolationList(){
        return violationDao.enabledViolationList();
    }

    public List<Violationfens> ListViolation(){


        return violationDao.ListViolation();
    }

    public Violationfens GetByCriteria(String Code){
        return violationDao.GetByCriteria(Code);
    }

}
