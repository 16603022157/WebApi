package com.admin.admin.entity.dw_violation;


import java.util.Date;

public class Violationfens {

  private int id;
  private String violationcode;

  public Date getCreatetime() {
    return createtime;
  }

  public Violationfens setCreatetime(Date createtime) {
    this.createtime = createtime;
    return this;
  }

  private String violationname;
  private int slightfens;
  private int seriousfens;
  private String createperson;
  private Date createtime;
  private Date Modificationtime;
  private String updatemonth;
  private boolean enabled;

  public boolean isEnabled() {
    return enabled;
  }

  public Violationfens setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  private String accountname;
  private boolean status;

  public Date getModificationtime() {
    return Modificationtime;
  }

  public Violationfens setModificationtime(Date modificationtime) {
    Modificationtime = modificationtime;
    return this;
  }



  public String getUpdatemonth() {
    return updatemonth;
  }

  public Violationfens setUpdatemonth(String updatemonth) {
    this.updatemonth = updatemonth;
    return this;
  }



  public String getViolationcode() {
    return violationcode;
  }


  public Violationfens setViolationcode(String violationcode) {
    this.violationcode = violationcode;
    return this;
  }

  public int getSlightfens() {
    return slightfens;
  }

  public Violationfens setSlightfens(int slightfens) {
    this.slightfens = slightfens;
    return this;
  }

  public int getSeriousfens() {
    return seriousfens;
  }

  public Violationfens setSeriousfens(int seriousfens) {
    this.seriousfens = seriousfens;
    return this;
  }

  public String getCreateperson() {
    return createperson;
  }

  public Violationfens setCreateperson(String createperson) {
    this.createperson = createperson;
    return this;
  }

  public String getAccountname() {
    return accountname;
  }

  public Violationfens setAccountname(String accountname) {
    this.accountname = accountname;
    return this;
  }
  public String getViolationname() {
    return violationname;
  }

  public Violationfens setViolationname(String violationname) {
    this.violationname = violationname;
    return this;
  }
  public boolean isStatus() {
    return status;
  }

  public Violationfens setStatus(boolean status) {
    this.status = status;
    return this;
  }


  public int getId() {
    return id;
  }

  public Violationfens setId(int id) {
    this.id = id;
    return this;
  }










}
