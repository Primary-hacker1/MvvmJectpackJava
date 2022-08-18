package com.justsafe.libview.base;

public class LiveDataEvent {

    public static final String SUCCESS = "000000";

    public static final int LOGIN_SUCCESS = 0x01;
    public static final int LOGIN_FAIL = 0x02;

    public static final int REGISTER_SUCCESS = 0x03;
    public static final int REGISTER_FAIL = 0x04;

    public static final int GET_SMSCODE_SUCCESS = 0x07;
    public static final int GET_SMSCODE_FAIL = 0x08;

    public static final int VM_DEVICE_SUCCESS=0x011;
    public static final int DEVICE_SUCCESS=0x05;
    public static final int DEVICE_FAIL=0x06;
    public static final int DEVICE_AUTO_PLAY_REAL_SUCCESS=0x15;
    public static final int DEVICE_AUTO_PLAY_REAL_FAIL=0x16;

    public static final int CHANGEPASSWORD_SUCCESS = 0x21;
    public static final int CHANGEPASSWORD_FAIL = 0x22;

    public static final int CHECK_SMS_SUCCESS = 0x23;
    public static final int CHECK_SMS_FAIL = 0x24;

    public static final int VIDEO_CONF_SUCCESS = 0x25;
    public static final int VIDEO_CONF_FAIL = 0x26;

    public static final int SCORE_SUCCESS = 0x27;
    public static final int SCORE_FAIL = 0x28;



    public static final String EMC_SUCCESS = "0";

    public static final int EMC_LOGIN_FAIL = 0x30;
    public static final int EMC_LOGIN_SUCCESS = 0x31;

    public static final int EMC_MEETING_FAIL = 0x32;

    public static final int UPDATE_SUCCESS = 0x33;
    public static final int UPDATE_FAIL = 0x34;

    public static final int SCORE_DETAIL_SUCCESS = 0x35;
    public static final int SCORE_DETAIL_FAIL = 0x36;






    public int action;
    public Object object;

    public LiveDataEvent(int action, Object object) {
        this.action = action;
        this.object = object;
    }
}
