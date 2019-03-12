package com.liujiakuo.boss.biz.login;

/**
 * Created by 佳阔 on 2019/3/11.
 */

public class UserBean{
    /**
     * id
     */
    private Long ID;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * user类型
     */
    private int type;

    /**
     * user通行证（账号）
     */
    private String key;

    /**
     * user密码
     */
    private String pass;

    /**
     * user昵称
     */
    private String nick;

    /**
     * 职务
     */
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
