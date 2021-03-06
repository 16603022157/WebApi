package com.admin.admin.entity.dw_user;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private int id;
    private String accountname;
    private String password;
    private int permissionid;
    private int createid;
    private String createname;
    private String areacode;
    private String phone;
    private String createtime;
    private boolean status;



    private String permissionname;
    private long usersystem;
    private String officephone;
    private String police;
    private String areaname;
    private String department;
    private String personid;
    private String mailbox;
    private String bmmc;
    private int manager;
    public int getManager() {
        return manager;
    }

    public User setManager(int manager) {
        this.manager = manager;
        return this;
    }


    public String getCreatetime() {
        return createtime;
    }

    public User setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getPoliceCode() {
        return PoliceCode;
    }

    public User setPoliceCode(String policeCode) {
        PoliceCode = policeCode;
        return this;
    }

    private String sex;
    private String station;
    private String bz;
    private String departmentnum;
    private String PoliceCode;

    public long getUsersystem() {
        return usersystem;
    }

    public User setUsersystem(long usersystem) {
        this.usersystem = usersystem;
        return this;
    }

    public String getOfficephone() {
        return officephone;
    }

    public User setOfficephone(String officephone) {
        this.officephone = officephone;
        return this;
    }

    public String getPolice() {
        return police;
    }

    public User setPolice(String police) {
        this.police = police;
        return this;
    }

    public String getAreaname() {
        return areaname;
    }

    public User setAreaname(String areaname) {
        this.areaname = areaname;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public User setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getPersonid() {
        return personid;
    }

    public User setPersonid(String personid) {
        this.personid = personid;
        return this;
    }

    public String getMailbox() {
        return mailbox;
    }

    public User setMailbox(String mailbox) {
        this.mailbox = mailbox;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getStation() {
        return station;
    }

    public User setStation(String station) {
        this.station = station;
        return this;
    }

    public String getBz() {
        return bz;
    }

    public User setBz(String bz) {
        this.bz = bz;
        return this;
    }



    public String getRoleName() {
        return RoleName;
    }

    public User setRoleName(String roleName) {
        RoleName = roleName;
        return this;
    }

    private String RoleName;



    private String aliasname;

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }

    public String getAliasname() {
        return aliasname;
    }

    public void setAliasname(String aliasname) {
        this.aliasname = aliasname;
    }

    public int getCreateid() {
        return createid;
    }

    public void setCreateid(int createid) {
        this.createid = createid;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCteatetime() {
        return createtime;
    }

    public void setCteatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDepartmentnum() {
        return departmentnum;
    }

    public void setDepartmentnum(String departmentnum) {
        this.departmentnum = departmentnum;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }
}
