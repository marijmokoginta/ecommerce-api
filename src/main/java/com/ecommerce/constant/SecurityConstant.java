package com.ecommerce.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 18_000_000; // 5h expressed in milisecond
    public static final String SECRET_KEY = "84n*75^^349HdjknfJKk234[])90k[=+987473yGgdb;jkjshjka";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORITIES = "authorities";

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
}
