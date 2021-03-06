package com.admin.admin.service.dw_task;

import com.admin.admin.dao.master.dw_task.TaskDao;
import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_personmessage.TPersonmessage;
import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.entity.dw_summons.TSummons;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.tool.CacheUtils;
import com.common.common.apppush.Demo;
import com.common.common.authenticator.CalendarAdjust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Tasking {

    @Autowired
    private TaskDao taskDao;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 取保监居到期提醒
     */
    public void ReminderBail() throws Exception {

        TMessage message = new TMessage();
        List<Personinformation> list = GetPerson();
        for (Personinformation item : list) {
            long days = CalendarAdjust.getDays(CalendarAdjust.GetYear(new Date()), CalendarAdjust.GetYear(simpleDateFormat.parse(item.getBailoutenddate())));
            // System.out.println(CalendarAdjust.getDays(CalendarAdjust.GetYear(new Date()), CalendarAdjust.GetYear(item.getBailoutenddate())));
            System.out.println(item.getBailoutenddate());
            if (days <= 15) {
                System.out.println(item.getPersonid());
                if (item.getSuspectstatus().equals("取保候审")) {
                    System.out.println(item.getPersonid());
                    message.setModular(1);
                    message.setContent("取保候审人员" + item.getPersonname() + "15天后取保候审到期,请提前告知!");
                    message.setPersonid(item.getPersonid());
                    message.setModularname("取保候审到期提醒");
                    message.setDetailtype(1);
                    message.setReadmessage(false);
                    message.setMessagetime(CalendarAdjust.timeStamp2Date(CalendarAdjust.getNotificationTime()));
                    taskDao.SaveMessage(message);
                } else if (item.getSuspectstatus().equals("监视居住")) {
                    message.setModular(1);
                    message.setContent("监居居住人员" + item.getPersonname() + "15天后监视居住到期,请提前告知!");
                    message.setPersonid(item.getPersonid());
                    message.setModularname("监视居住到期提醒");
                    message.setReadmessage(false);
                    message.setDetailtype(2);
                    message.setMessagetime(CalendarAdjust.timeStamp2Date(CalendarAdjust.getNotificationTime()));
                    taskDao.SaveMessage(message);
                }


            }
        }
    }

    /**
     * 获取取保监居人员
     *
     * @return
     */
    public List<Personinformation> GetPerson() {
        return taskDao.GetPerson();
    }


    /**
     * 生成传讯记录
     */
    public void GeneratedRecord() throws Exception {

        TSummons summons = new TSummons();
        List<Personinformation> list = GetPerson();
        for (Personinformation item : list) {
            //填写传讯信息
            summons.setSummontime(simpleDateFormat.format(new Date()));
            summons.setPersonid(item.getPersonid());
            summons.setPersonname(item.getPersonname());
            TSummons tSummons = taskDao.GetSummons(item.getPersonid());
            //判断监居时间是否为null
            int month = 0;
            int year = 0;

            if (tSummons != null) {
                month = CalendarAdjust.getMonth(tSummons.getSummonsendtime());
                year = CalendarAdjust.getYears(tSummons.getSummonsendtime());
                summons.setSummonsbegintime(CalendarAdjust.getFirstDayOfMonth1(year, month));
                summons.setSummonsendtime(CalendarAdjust.getLastDayOfMonth1(year, month));
            } else {
                if (CalendarAdjust.getMonthDiff(simpleDateFormat.parse(item.getBailoutbegindate()), new Date()) == 1) {
                    month = CalendarAdjust.getMonth(CalendarAdjust.GetYear(new Date()));
                    year = CalendarAdjust.getYears(CalendarAdjust.GetYear(new Date()));
                    summons.setSummonsbegintime(CalendarAdjust.getFirstDayOfMonth1(year, month));
                    summons.setSummonsendtime(CalendarAdjust.getLastDayOfMonth1(year, month));
                }
            }


            TSummons tSummons1 = taskDao.GetNumber(item.getPersonid());
            if (tSummons1 == null) {

                taskDao.SaveSummons(summons);
            } else if (tSummons1 != null && (new Date().getTime() > CalendarAdjust.dateFormat.parse(tSummons1.getSummonsendtime()).getTime())) {
                taskDao.SaveSummons(summons);
            }
        }
    }


    /**
     * 生成消息
     *
     * @throws Exception
     */

    public void GetMessage() throws Exception {
        Remindersettings remindersettings = taskDao.GetConfigure("3");

        String[] strArr = null;

        if (remindersettings.getSettingday().indexOf(",") != -1) {
            strArr = remindersettings.getSettingday().split(",");
        }
        TPersonmessage model = new TPersonmessage();
        List<Personinformation> list = GetPerson();
        TMessage message = new TMessage();
        for (Personinformation item : list) {
            TSummons tSummons = taskDao.GetNumber(item.getPersonid());
            //填写人员端app消息内容
            model.setModular(5);
            model.setContent("您应于本月30日前， 前往"+ CacheUtils.get("department").toString()+"配合传讯取证!");
            model.setPersonid(item.getPersonid());
            model.setModularname("传讯提醒");
            model.setReadmessage(false);
            model.setDetailtype(1);
            model.setDetailtypename("传讯取证通知");
            //填写管理端app消息内容
            message.setModular(4);
            if (item.getSuspectstatus().equals("取保候审")) {
                message.setContent("取保候审人员" + item.getPersonname() + "下个月应进行传讯取证,请提前告知!");
            } else if (item.getSuspectstatus().equals("监视居住")) {
                message.setContent("监视居住人员" + item.getPersonname() + "下个月应进行传讯取证,请提前告知!");
            }
            message.setPersonid(item.getPersonid());
            message.setModularname("待办提醒");
            message.setDetailtypename("传讯取证通知");
            message.setDetailtype(1);
            message.setReadmessage(false);
            if (tSummons != null && (new Date().getTime() <= CalendarAdjust.dateFormat.parse(tSummons.getSummonsendtime()).getTime())) {
                if (taskDao.GetMessagenum(4,item.getPersonid(), CalendarAdjust.GetYear(new Date())) <= 0) {
                    if (strArr == null || strArr.length == 0) {
                        message.setMessagetime(
                                CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(remindersettings.getSettingday()))));
                        model.setMessagetime(CalendarAdjust.dateFormat.format(CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(remindersettings.getSettingday())))));

                        taskDao.insertpermessage(model);
                        taskDao.SaveMessage(message);
                    } else {
                        for (int i = 0; i < strArr.length; i++) {
                            message.setMessagetime(
                                    CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(strArr[i]))));
                            model.setMessagetime(CalendarAdjust.dateFormat.format(CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(strArr[i])))));

                            taskDao.insertpermessage(model);
                            taskDao.SaveMessage(message);
                        }
                    }

                } else {
                    System.out.println("已有消息存在");
                }


            }

        }
    }


    public void ProduceMessage() throws Exception{
        List<Personinformation> list = taskDao.GetPerson();
        Remindersettings remindersettings = taskDao.GetConfigure("3");
        String[] strArr = null;
        if (remindersettings!=null){
            if (remindersettings.getSettingday().indexOf(",") != -1) {
                strArr = remindersettings.getSettingday().split(",");
            }
        }
        for (Personinformation item:list) {
            TSummons tSummons = taskDao.GetSummons(item.getPersonid());
            TMessage message = new TMessage();
            message.setModular(4);
            if (tSummons != null) {
                if (item.getSuspectstatus().equals("取保候审")) {
                    message.setContent("取保候审人员" + item.getPersonname() + "应于" + tSummons.getSummonsbegintime() + "至"
                            + tSummons.getSummonsendtime() + "进行传讯取证,请提前告知!");
                } else if (item.getSuspectstatus().equals("监视居住")) {
                    message.setContent("监视居住人员" + item.getPersonname() + "应于" + tSummons.getSummonsbegintime() + "至"
                            + tSummons.getSummonsendtime() + "进行传讯取证,请提前告知!");
                }
            }
            message.setPersonid(item.getPersonid());
            message.setModularname("待办提醒");
            message.setDetailtypename("传讯取证通知");
            message.setDetailtype(1);
            message.setReadmessage(false);
            TPersonmessage tPersonmessage = new TPersonmessage();
            tPersonmessage.setModular(5);
            tPersonmessage.setContent("您应于" + tSummons.getSummonsbegintime() + "至"
                    + tSummons.getSummonsendtime() + "前往" + item.getPolicestation() + "配合传讯取证!");
            tPersonmessage.setPersonid(item.getPersonid());
            tPersonmessage.setModularname("传讯提醒");
            tPersonmessage.setReadmessage(false);
            tPersonmessage.setDetailtype(1);
            tPersonmessage.setDetailtypename("传讯取证通知");
            if (tSummons != null) {
                if (new Date().getTime() >= CalendarAdjust.dateFormat.parse(tSummons.getSummonsbegintime()).getTime() &&
                        new Date().getTime() < CalendarAdjust.dateFormat.parse(tSummons.getSummonsendtime()).getTime()) {
                    if (taskDao.GetMessagenum(4, item.getPersonid(), CalendarAdjust.GetYear(new Date())) <strArr.length) {
                        if (tSummons.getReporttime()==null) {
                            if (strArr == null || strArr.length == 0) {
                                message.setMessagetime(CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(
                                        Integer.parseInt(remindersettings.getSettingday()))));
                                tPersonmessage.setMessagetime(CalendarAdjust.dateFormat.format(CalendarAdjust.timeStamp2Date(
                                        CalendarAdjust.perThridMouthTime(Integer.parseInt(remindersettings.getSettingday())))));
                                taskDao.insertpermessage(tPersonmessage);
                                taskDao.SaveMessage(message);
                            }else {
                                for (int i = 0; i < strArr.length; i++) {
                                    if (CalendarAdjust.GetYear(new Date()).equals(CalendarAdjust.GetYear(
                                            CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(strArr[i])))))) {

                                        message.setMessagetime(
                                                CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(strArr[i]))));
                                        tPersonmessage.setMessagetime(CalendarAdjust.dateFormat.format(CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(strArr[i])))));
                                    }
                                    taskDao.insertpermessage(tPersonmessage);
                                    taskDao.SaveMessage(message);
                                }

                            }
                        }

                        }
                    }

                } else {

                }
            }

    }


    //生成传讯记录
    public void Summons() throws Exception{
        List<Personinformation> list = taskDao.GetPerson();
        for (Personinformation item:list){
            TSummons summons = new TSummons();
            if (item.getPersonid()!=null && item.getPersonid()!=""){
                summons.setSummontime(simpleDateFormat.format(new Date()));
                summons.setPersonid(item.getPersonid());
                summons.setPersonname(item.getPersonname());
                TSummons tSummons = taskDao.GetSummons(item.getPersonid());

                if (tSummons!=null){
                    summons.setSummonsbegintime(CalendarAdjust.getFirstDayOfMonth1(
                            CalendarAdjust.getYears(tSummons.getSummonsendtime()),CalendarAdjust.getMonth(tSummons.getSummonsendtime())));
                    summons.setSummonsendtime(CalendarAdjust.getLastDayOfMonth1(
                            CalendarAdjust.getYears(tSummons.getSummonsendtime()),CalendarAdjust.getMonth(tSummons.getSummonsendtime())));

                     if (new Date().getTime() == CalendarAdjust.dateFormat.parse(tSummons.getSummonsbegintime()).getTime()){
                        taskDao.SaveSummons(summons);
                    }
                }else if(CalendarAdjust.getMonthDiff(simpleDateFormat.parse(item.getFoundertime()), new Date()) == 1){
                    summons.setSummonsbegintime(CalendarAdjust.getFirstDayOfMonth1(
                            CalendarAdjust.getYears(CalendarAdjust.GetYear(new Date())), CalendarAdjust.getMonth(CalendarAdjust.GetYear(new Date()))));
                    summons.setSummonsendtime(CalendarAdjust.getLastDayOfMonth1(
                            CalendarAdjust.getYears(CalendarAdjust.GetYear(new Date())), CalendarAdjust.getMonth(CalendarAdjust.GetYear(new Date()))));
                    taskDao.SaveSummons(summons);

                }

            }

        }
    }

    /**
     * 修改严重程度
     */
    public void Statisticalsummons() {
        Violationfens violationfens = taskDao.GetViolationfens(2);

        List<Personinformation> list = taskDao.GetPerson();
        String severity = "";
        for (Personinformation item : list) {
            int num = taskDao.StatisticalSummons(item.getPersonid(), CalendarAdjust.GetYear(new Date()));
            if (num < violationfens.getSlightfens()) {
                severity = "正常";
            } else if (num >= violationfens.getSlightfens() && num < violationfens.getSeriousfens()) {
                severity = "轻微";
            } else if (num >= violationfens.getSeriousfens()) {
                severity = "严重";
            }
            taskDao.UpdateDegree(item.getPersonid(), severity);
        }

    }

    public List<TMessage> GetMessageList(int type) throws Exception {
        List<TMessage> messageList = new ArrayList<TMessage>();
        if (type == 4) {
            messageList = taskDao.GetMessageList(4, CalendarAdjust.GetYear(new Date()));

        }  else if (type == 6) {
            messageList = taskDao.GetMessageList(6, CalendarAdjust.GetYear(new Date()));
        } else if (type == 7) {
            messageList = taskDao.GetMessageList(7, CalendarAdjust.GetYear(new Date()));
        }
        for (TMessage item : messageList) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(item.getMessagetime());   //设置当前时间
            cal.add(Calendar.DATE, 1);  //在当前时间基础上加一天
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Demo demo = new Demo("5dd349b4570df37b6700045e", "4hpqbdi0wpikb7bkwamq4uwnpvkjhebz");
            demo.sendAndroidCustomizedcast(item.getPersonid(), "ReleaseBailCode", "AtMqss89NJcaerkruc7N0Bgif58Zyy00PkqfWUt5j1xz",
                    item.getModularname(), item.getContent(), cal.getTime());
            System.out.println(item.getMessagetime());
        }

        return messageList;
    }


}
