package com.groups.schicken.common.util;

public class NumberToKorean {
    private static final String[] NUMBERS = { "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };
    private static final String[] UNITS = { "", "십", "백", "천" };
    private static final String[] BIG_UNITS = { "", "만", "억", "조", "경" };

    public static String convert(long number) {
        if (number == 0) return "영";

        StringBuilder sb = new StringBuilder();
        long remains = number;

        for (int i = 0; remains > 0; i++) {
            long part = remains % 10000;
            remains /= 10000;

            StringBuilder partBuilder = new StringBuilder();
            for (int j = 0; part > 0 && j < 4; j++) {
                int digit = (int)(part % 10);
                if (digit > 0) {
                    partBuilder.insert(0, NUMBERS[digit] + UNITS[j]);
                }
                part /= 10;
            }

            if (partBuilder.length() > 0) {
                sb.insert(0, partBuilder.toString() + BIG_UNITS[i]);
            }
        }

        return sb.toString() + "원";
    }
}
