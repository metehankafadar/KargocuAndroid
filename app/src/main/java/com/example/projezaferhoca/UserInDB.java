package com.example.projezaferhoca;

public class UserInDB {


private String username,password ,name ,surname,lat,lon,status,role,mail,telno,tcno;

private String id;


    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public UserInDB(String username, String password, String name, String surname, String lat, String mail, String lon, String status, String role,String telno,String tcno, String id) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.status = status;
        this.role = role;
        this.mail= mail;
        this.tcno=tcno;
        this.telno=telno;

}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
