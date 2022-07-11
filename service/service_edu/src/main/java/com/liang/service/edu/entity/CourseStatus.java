package com.liang.service.edu.entity;

import lombok.Getter;

public enum CourseStatus {

    DRAFT(false),
    NORMAL(true);

    @Getter
    private final Boolean status;

    CourseStatus(Boolean status) {
        this.status = status;
    }
}
