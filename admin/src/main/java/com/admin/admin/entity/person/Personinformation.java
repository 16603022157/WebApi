package com.admin.admin.entity.person;

import org.apache.tomcat.jni.Time;

import java.util.Date;
import java.util.Timer;

public class Personinformation {
    private String personid;
    private String personname;
    private String gender;
    private int age;
    private Date birthdate;
    private String card;
    private String Workunit;
    private Date Bailoutbegindate;
    private Date Bailoutenddate;

    public String getPersonid() {
        return personid;
    }

    public Personinformation setPersonid(String personid) {
        this.personid = personid;
        return this;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getSponsoralarm() {
        return sponsoralarm;
    }

    public void setSponsoralarm(String sponsoralarm) {
        this.sponsoralarm = sponsoralarm;
    }

    public String getWechatnumber() {
        return wechatnumber;
    }

    public void setWechatnumber(String wechatnumber) {
        this.wechatnumber = wechatnumber;
    }

    public String getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber;
    }

    public String getFounderid() {
        return founderid;
    }

    public Personinformation setFounderid(String founderid) {
        this.founderid = founderid;
        return this;
    }

    public Time getFoundertime() {
        return foundertime;
    }

    public Personinformation setFoundertime(Time foundertime) {
        this.foundertime = foundertime;
        return this;
    }

    public String getModifierid() {
        return modifierid;
    }

    public Personinformation setModifierid(String modifierid) {
        this.modifierid = modifierid;
        return this;
    }

    public Time getModifiertime() {
        return modifiertime;
    }

    public Personinformation setModifiertime(Time modifiertime) {
        this.modifiertime = modifiertime;
        return this;
    }

    public String getSuspectstatus() {
        return suspectstatus;
    }

    public Personinformation setSuspectstatus(String suspectstatus) {
        this.suspectstatus = suspectstatus;
        return this;
    }

    public String getMarriage() {
        return marriage;
    }

    public Personinformation setMarriage(String marriage) {
        this.marriage = marriage;
        return this;
    }

    private String Sponsor;
    private String sponsoralarm;
    private String Contact;
    private String Address;
    private String wechatnumber;
    private String qqnumber;
    private boolean status;
    private String founderid;
    private Time foundertime;
    private String modifierid;
    private Time modifiertime;
    private String suspectstatus;
    private String marriage;


    public String getPerson_name() {
        return personname;
    }

    public void setPerson_name(String person_name) {
        this.personname = person_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public void setWorkunit(String workunit) {
        Workunit = workunit;
    }

    public Date getBailoutbegindate() {
        return Bailoutbegindate;
    }

    public void setBailoutbegindate(Date bailoutbegindate) {
        Bailoutbegindate = bailoutbegindate;
    }

    public Date getBailoutenddate() {
        return Bailoutenddate;
    }

    public void setBailoutenddate(Date bailoutenddate) {
        Bailoutenddate = bailoutenddate;
    }

    public String getSponsor() {
        return Sponsor;
    }

    public void setSponsor(String sponsor) {
        Sponsor = sponsor;
    }


    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
