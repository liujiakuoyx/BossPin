package com.liujiakuo.boss.biz.seeker;

import java.util.Date;

/**
 * Created by 佳阔 on 2019/3/24.
 */

public class Resume {

    /**
     * id
     */
    private Long ID;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private int sex;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 联系方式
     */
    private String ContactInfo;

    /**
     * 最高学历
     */
    private String education;

    /**
     * 学校
     */
    private String school;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 期待职位
     */
    private String desiredPosition;

    /**
     * 期望工作城市
     */
    private String desiredCity;

    /**
     * 自我评价
     */
    private String evaluate;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(String contactInfo) {
        ContactInfo = contactInfo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public String getDesiredCity() {
        return desiredCity;
    }

    public void setDesiredCity(String desiredCity) {
        this.desiredCity = desiredCity;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}

