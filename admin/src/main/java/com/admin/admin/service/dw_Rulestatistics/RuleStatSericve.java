package com.admin.admin.service.dw_Rulestatistics;

import com.admin.admin.dao.dw_Rulestatistics.RuleStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleStatSericve {
    @Autowired
    private RuleStatistics ruleStatistics;

    public int getRuuleList(String Code,int level,String StartTime,String EndTime,String Severity){
        return ruleStatistics.RuleStatisList(Code,level,StartTime,EndTime,Severity);
    }

    //获取未脱离管控区域的人数
    public List<Map<String,String>> getNotout(String Code,String StartTime,String EndTime){
        return ruleStatistics.getNotout(Code,StartTime,EndTime);
    }


}
