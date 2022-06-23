package com.liang.common.utils;

import lombok.Getter;

public enum ResultCode {

    SUCCESS(20000),
    ERROR(20001);

    @Getter
    private final int type;

    ResultCode(int type) {
        this.type = type;
    }
}
