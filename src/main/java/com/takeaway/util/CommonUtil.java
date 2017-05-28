package com.takeaway.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    public static String newOrderNo(String prefix, Date date) {

        return prefix +
                new SimpleDateFormat("yyyyMMddHHmmss").format(date) +
                new Random().nextInt(9) +
                new Random().nextInt(9) +
                new Random().nextInt(9);

    }

}
