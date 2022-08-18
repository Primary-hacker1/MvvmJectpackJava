package com.justsafe.domain.persional;

import java.io.Serializable;

public class UserModel implements Serializable {
    //手机号     private String identifier = "";
    public String phone;
    //唯一标识     private String identifier = "";
    public String userId;
    //唯一标识
    public String userSig;
    //用户名，昵称     private String nickName = "";
    public String userName;
    //头像链接     private String faceUrl = "";
    public String userAvatar;
    //签名     private String selfSignature = "";
    public String sign;

    public String score;

    public String weight;

    public String diagnose;

    public String hospital;

    public String allowType = "";

    public int gender = 0;

    public int birthday = 0;

    public int language = 0;

    public String location = "";

    public int role = 0;

    public int level = 0;

    @Override
    public String toString() {
        return "UserModel{" +
                "phone='" + phone + '\'' +
                ", userId='" + userId + '\'' +
                ", userSig='" + userSig + '\'' +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                '}';
    }
}
