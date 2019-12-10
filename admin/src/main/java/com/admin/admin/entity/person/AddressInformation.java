package com.admin.admin.entity.person;

import java.util.Date;

public class AddressInformation {
    private int id;
    private String latitude;
    private String longitude;
    private String address;
    private String person_id;
    private Date timesta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getPerson_id() {
        return person_id;
    }

    public AddressInformation setPerson_id(String person_id) {
        this.person_id = person_id;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Date getTimesta() {
        return timesta;
    }

    public void setTimesta(Date timesta) {
        this.timesta = timesta;
    }
}
