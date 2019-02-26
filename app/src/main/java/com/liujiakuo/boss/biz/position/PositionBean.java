package com.liujiakuo.boss.biz.position;

/**
 * liujiakuo on 2019/2/18 11:43
 */

import com.liujiakuo.boss.biz.company.CompanyInfo;
import com.liujiakuo.boss.biz.user.UserInfo;

import java.io.Serializable;

/**
 * 职位bean
 */
public class PositionBean implements Serializable {
    /**
     * 工作名称
     */
    private String name;

    /**
     * 地点
     */
    private String location;

    /**
     * 学历要求
     * 学历不限、大专、本科、研究生、博士
     */
    private String education;

    /**
     * 工龄
     */
    private String workYears;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 职位详情
     */
    private String details;

    /**
     * 发布者
     */
    private UserInfo user;

    /**
     * 所属公司
     */
    private CompanyInfo company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkYears() {
        return workYears;
    }

    public void setWorkYears(String workYears) {
        this.workYears = workYears;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public CompanyInfo getCompany() {
        return company;
    }

    public void setCompany(CompanyInfo company) {
        this.company = company;
    }


}
