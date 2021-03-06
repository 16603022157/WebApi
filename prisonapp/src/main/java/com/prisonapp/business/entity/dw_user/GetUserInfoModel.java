package com.prisonapp.business.entity.dw_user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class GetUserInfoModel {
    private String code;
    private String account;
    private int gender;
    private String name;
    private String headUrl;
    private String startDate;
    private String endDate;
    private String inChargePerson;
    private String inChargeContract;
    @JsonIgnore
    private String sponsoralarm;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        String a = String.valueOf(startDate.getTime());
        this.startDate = a;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        String a = String.valueOf(endDate.getTime());
        this.endDate = a;
    }

    public String getInChargePerson() {
        return inChargePerson;
    }

    public void setInChargePerson(String inChargePerson) {
        this.inChargePerson = inChargePerson;
    }

    public String getInChargeContract() {
        return inChargeContract;
    }

    public void setInChargeContract(String inChargeContract) {
        this.inChargeContract = inChargeContract;
    }

    public String getSponsoralarm() {
        return sponsoralarm;
    }

    public void setSponsoralarm(String sponsoralarm) {
        this.sponsoralarm = sponsoralarm;
    }
}
