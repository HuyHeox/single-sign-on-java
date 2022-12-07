package com.ks.sso.config;

public class Config {

    public static final Long NULL_ID = -1L;
    public static final String NULL_TXT = null;
    public static final String TEXT_EMPTY = null;
    public static final int USER_STATUS_NORMAL = 0;

    public static final int STATUS_OPENNED = 2;
    public static final int GENDER_FEMALE = 0;
    public static final int GENDER_MALE = 1;

    public static final int LOGIN_ACCOUNT_OFF = 5;

    public static final int LOGIN_ACCOUNT_NOT_EXIST = 2;

    public static final String NTH = "Hungnt@701B1";
    public static final String HAINT = "HaiNtSoICT";

    public static final String TAS = "123456";

    public static final int USER_STATUS_NEW = -1;

    public static final int USER_STATUS_QUIT = 3;

    public static String getGender(int gender) {
        if (gender == Config.GENDER_FEMALE) {
            return "Nữ";
        } else if (gender == Config.GENDER_MALE) {
            return "Nam";
        } else {
            return Config.TEXT_EMPTY;
        }
    }
}
