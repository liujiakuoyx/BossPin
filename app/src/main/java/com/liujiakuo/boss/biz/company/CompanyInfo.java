package com.liujiakuo.boss.biz.company;

import java.io.Serializable;

/**
 * liujiakuo on 2019/2/26 10:49
 */
public class CompanyInfo implements Serializable {

    /**
     * 公司名称
     */
    private String name;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 融资阶段
     * 按等级依次为 不需要融资、未融资、天使轮、A、B、C、D及以上、已上市。
     */
    private String stage;

    /**
     * 员工人数
     * 0-20、20-99、100-499、500-1000、1000-9999、10000以上
     */
    private String number;

    /**
     * id
     */
    private String cid;

    /**
     * 注册资金
     */
    private String capital;

    /**
     * 注册时间
     */
    private String registrationTime;

    /**
     * 法人
     */
    private String person;

    /**
     * 公司官网
     */
    private String website;

    /**
     * 公司信息
     */
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}