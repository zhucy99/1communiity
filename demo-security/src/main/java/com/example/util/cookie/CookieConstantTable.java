package com.example.util.cookie;

public class CookieConstantTable {
    // cookie的有效期默认为30天
    public final static int COOKIE_MAX_AGE = 60 * 60 * 24 * 30; 
    //cookie加密时的额外的salt
    public final static String salt = "chongyang";
    //自动登录的Cookie名
    public final static String RememberMe = "remember-me";
}
