package project.model;

import java.sql.Date;

public class VO {
    // restaurant field
    int resNo;
    String resName;
    String resAddr;
    String areaNo;
    String resUrl;
    String resTel;
    String resBestMenu;
    int menuPrice;

    // review field
    float revGpa;
    String revContents;
    Date revVisitDate;

    public int getResNo() {
        return resNo;
    }

    public void setResNo(int resNo) {
        this.resNo = resNo;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResAddr() {
        return resAddr;
    }

    public void setResAddr(String resAddr) {
        this.resAddr = resAddr;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getResTel() {
        return resTel;
    }

    public void setResTel(String resTel) {
        this.resTel = resTel;
    }

    public String getResBestMenu() {
        return resBestMenu;
    }

    public void setResBestMenu(String resBestMenu) {
        this.resBestMenu = resBestMenu;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public float getRevGpa() {
        return revGpa;
    }

    public void setRevGpa(float revGpa) {
        this.revGpa = revGpa;
    }

    public String getRevContents() {
        return revContents;
    }

    public void setRevContents(String revContents) {
        this.revContents = revContents;
    }

    public Date getRevVisitDate() {
        return revVisitDate;
    }

    public void setRevVisitDate(Date revVisitDate) {
        this.revVisitDate = revVisitDate;
    }


}