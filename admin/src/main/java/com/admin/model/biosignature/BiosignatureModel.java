package com.admin.model.biosignature;


import java.util.Date;

public class BiosignatureModel {

  private String person_id;
  private long type;
  private String filepath;
  private Date createtime;


  public String getPersonId() {
    return person_id;
  }

  public void setPersonId(String person_id) {
    this.person_id = person_id;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }


  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }


//  public Boolean getStatus() {
//    return status;
//  }
//
//  public void setStatus(Boolean status) {
//    this.status = status;
//  }

}
