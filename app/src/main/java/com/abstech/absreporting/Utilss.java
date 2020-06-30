package com.abstech.absreporting;

public class Utilss {
    //Email Validation pattern
    public static final String regEx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //Password matcher
    public static final String regExPassword ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$";

    //Fragments Tags
    public static final String Login_Fragment = "Login_Fragment";
    public static final String SignUp_Fragment = "SignUp_Fragment";
    public static final String ForgotPassword_Fragment = "ForgotPassword_Fragment";
}
