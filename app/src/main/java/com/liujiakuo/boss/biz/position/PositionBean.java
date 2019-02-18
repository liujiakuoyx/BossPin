package com.liujiakuo.boss.biz.position;

/**
 * liujiakuo on 2019/2/18 11:43
 */

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

    public static class CompanyInfo implements Serializable{

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

    public static class UserInfo implements Serializable{
        /**
         * 头像
         */
        private String headUrl;

        /**
         * user昵称
         */
        private String nick;

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }
    }

}
