package com.fn.utils;


/**
 * 字符串格式验证
 *
 * @author Administrator
 */
public class ValidateUtils {
    private static final String MOBILE_PATTERN = "^1\\d{10}$";
    private static final String REAL_NAME = "^[\u4e00-\u9fa5]{0,}$";
    private static final String EMAIL_PATTERN = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    /**
     * 匹配电话号码格式
     *
     * @param mobilePhoneNumber
     * @return true:成功 false:失败
     */
    public static boolean validateMobileNumber(String mobilePhoneNumber) {
        return validateByPattern(mobilePhoneNumber, MOBILE_PATTERN);
    }

    /**
     * 匹配email格式
     *
     * @param email
     * @return true:成功 false:失败
     */
    public static boolean validateEmail(String email) {
        return validateByPattern(email, EMAIL_PATTERN);
    }

    public static boolean validateRealName(String realName) {
        return validateByPattern(realName, REAL_NAME);
    }

    public static boolean validateByPattern(String value, String pattern) {
        if (StringUtils.isBlank(value))
            return false;

        return value.trim().matches(pattern);
    }


}
