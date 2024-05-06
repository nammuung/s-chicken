package com.groups.schicken.common.util;

public class NumberFormatter {

    public static String formatting(Integer number){
        return formatting(number + "");
    }

    public static String formatting(Long number){
        return formatting(number + "");
    }

    public static String formatting(String number){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < number.length(); i++) {
            sb.append(number.charAt(number.length() -1 -i));
            if(i % 3 == 2 && i != number.length()-1){
                sb.append(",");
            }
        }

        return sb.reverse().toString();
    }
}
