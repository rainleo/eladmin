package com.fn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumGenerator {

    // 生成订单号规则
    private static int count = 0;

    public static String getNumber() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        SimpleDateFormat simpt = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpt.format(new Date()) + ++count;
    }

    public static void main(String[] args) {
        System.out.println(getNumber());
    }

}
