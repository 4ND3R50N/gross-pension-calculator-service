package com.oev.apis.grosspension.service;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum RetirementYears {

    OLDER_THAN_1958(66),
    YOUNGER_THAN_1958(67),
    @JsonEnumDefaultValue
    UNKNOWN(0);

    public final int value;

    private static final int RETIREMENT_BORDER = 1958;

    RetirementYears(int value) {
        this.value = value;
    }

    public static RetirementYears valueOf(int year) {
        return year >= RETIREMENT_BORDER ? RetirementYears.YOUNGER_THAN_1958 : RetirementYears.OLDER_THAN_1958;
    }
}
