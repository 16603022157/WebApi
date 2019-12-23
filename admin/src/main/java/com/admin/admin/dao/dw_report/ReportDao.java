package com.admin.admin.dao.dw_report;

import com.admin.admin.entity.dw_report.Reportsettings;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDao {

    /*
    新增上报设置
     */
    int SaveReport(Reportsettings reportsettings);
    /*
    修改上报设置
     */
    int UpdateReport(Reportsettings reportsettings);
    /*
    作废
     */
    int deleteReport(int id);

    /*
    查看设置
     */

    Reportsettings getReport();
}
