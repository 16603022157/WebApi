package com.adminapp.business.dao.dw_supervise;

import com.adminapp.business.entity.dw_supervise.*;
import com.adminapp.business.entity.dw_user.UserModel;
import com.adminapp.model.dw_supervise.*;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface SuperfineDado {

    List<Personinformation> listPersonInformation(@Param("UserId")String UserId);

    List<PrisonSettingModel> getPrisonSetting(@Param("personid") String personid);

    List<ReportLocationModel> listLocation(@Param("personid")String personid);

    List<ReportSettingsInformation> listReportSetting();

    List<SinginInformation> listSingin(@Param("personid") String personid,@Param("type") int type);

    List<SummonsInformation> listCiteRecord();

    List<SummonsInformation> listKeyCiteRecord(@Param("key") String key);

    PersonAllInformationModel getPersonInformation(@Param("personid")String personid);

    PersonAllInformationModel getPersonInformationFromName(@Param("personName")String personName);

    int insertCiteRecord(@Param("date")CiteRecordSubmitModel citeRecordSubmitModel);

    List<LeaveListModel> getLeaveList();

    List<AuditorRecordModel> getAuditorList(@Param("leaveOrder") String leaveOrder);

    List<LeaveListModel> listLeaveType(@Param("statusCode")String statusCode);

    List<LeaveListModel> getKeyLeaveList(@Param("key")String key);

    List<LeaveListModel> listKeyLeaveType(@Param("key")String key,@Param("statusCode")String statusCode);

    LeaveListModel getLeaveInformation(@Param("leaveOrder")String leaveOrder);

    LeaveInformation getAllLeaveInformation(@Param("leaveOrder")String leaveOrder);

    int updateLeaveInformation(@Param("leaveOrder")String leaveOrder,@Param("statusCode")String statusCode,@Param("status")String status);

    int insertAuditorInformation(@Param("leaveOrder")String leaveOrder, @Param("userId")String userId, @Param("accountName")String accountName, @Param("auditorDateTime")Date auditorDateTime,
                                 @Param("leavingMessage")String leavingMessage,@Param("statusCode")String statusCode,@Param("status")String status);

    List<ReminderSettingsInformation> listSummonSetting();

    int getViolationSlightFens(@Param("Code")String Code);

    int getViolationSeriousFens(@Param("Code")String Code);

    List<SinginInformation> listPersonSingin(@Param("personId")String personId,@Param("type")int type);

    List<SummonsInformation> getSummonsInformation(@Param("personId")String personId);

    String getAreaFence(@Param("personId")String personId);

    String getAreaCode(@Param("personId")String personId);

    List<LocationRecordModel> listLocationRecord(@Param("personId")String personId);

    List<LocationInformation> listViolateLocationRecord(@Param("personId")String personId);

    List<SinginInformation> listAllSinginInformation();

    PrisonSettingInformation getPrisonValidSetting(@Param("personId")String personId,@Param("validName")String validName);

    int updatePrisonSetting(@Param("personId")String personId,@Param("settingName")String settingName,@Param("settingCheck")boolean settingCheck,
                            @Param("settingTime")Date settingTime);

    int insertPrisonSetting(@Param("personId")String personId,@Param("settingName")String settingName,@Param("settingCheck")boolean settingCheck,
                            @Param("settingTime")Date settingTime,@Param("settingCode")int settingCode);

    List<PrisonSettingModel> getPrisonValidWay(@Param("personId")String personId);

    LeaveListModel getApplyLeave(@Param("code")String code);

    SuperviseCaseInformation getPersonCaseInformation(@Param("personId")String personId);

    SuperviseBailPersonInformation getBailPersonInformation(@Param("personId")String personId);

    SuperviseBailMoneyInformation getBailMoneyInformation(@Param("personId")String personId);

    String getViolateName(@Param("typeCode")String typeCode,@Param("enumCode")String enumCode);

    List<SummonsInformation> listNotCiteRecord();

    UserModel getUserInformation(@Param("userId")String userId);

    int insertPersonMessage(@Param("modular")int modular,@Param("content")String content,@Param("modularName")String modularName,@Param("personId")String personId,
                            @Param("detailType")int detailType,@Param("detailName")String detailName,@Param("leaveOrder")String leaveOrder);

    int updateHasVoice(@Param("code")String code);

    String getAccessToken();

    String getUserId();

    int updateAccessToken(@Param("accessToken")String accessToken,@Param("userId")String userId);

    int insertAccessToken(@Param("accessToken")String accessToken,@Param("userId")String userId);
}
