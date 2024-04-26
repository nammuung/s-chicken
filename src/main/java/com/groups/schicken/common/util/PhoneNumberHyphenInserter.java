package com.groups.schicken.common.util;

public class PhoneNumberHyphenInserter {
    /**
     * 핸드폰 번호를 000-0000-0000 형태가 되게 하이픈을 넣어주는 함수
     * @param phoneNumber 하이픈이 존재하지 않는 핸드폰 번호
     * @return 하이픈이 존재하거나 11자리가 아니거나 숫자가 아니면 인풋을 그대로 리턴하고, 그렇지 않으면 하이픈을 넣어서 리턴
     */
    public static String hyphenInsert(String phoneNumber){
        System.out.println("phoneNumber = " + phoneNumber);

        if(phoneNumber.split("-").length > 1){
            System.out.println("with hyphen");
            return phoneNumber;
        }

        if(phoneNumber.length() != 11){
            System.out.println("length no : " + phoneNumber.length());
            return phoneNumber;
        }

        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e){
            System.out.println("no integer");
            return phoneNumber;
        }

        return phoneNumber.substring(0, 3) +
                "-" +
                phoneNumber.substring(3, 7) +
                "-" +
                phoneNumber.substring(7, 11);
    }
}
