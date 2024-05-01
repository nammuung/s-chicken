package com.groups.schicken.weather;

import lombok.Getter;

@Getter
public enum WindType {
    N0(0, "북"),
    NNE(1, "북북동"),
    NE(2, "북동"),
    ENE(3, "동북동"),
    E(4, "동"),
    ESE(5, "동남동"),
    SE(6, "남동"),
    SSE(7, "남남동"),
    S(8, "남"),
    SSW(9, "남남서"),
    SW(10, "남서"),
    WSW(11, "서남서"),
    W(12, "서"),
    WNW(13, "서북서"),
    NW(14, "북서"),
    NNW(15, "북북서"),
    N16(16, "북");

    private int code;

    private String name;

    private WindType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static WindType value(int value) {
        for (WindType type : WindType.values()) {
            if (type.getCode() == value) {
                return type;
            }
        }
        return null;
    }
}