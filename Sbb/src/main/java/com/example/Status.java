package com.example;

import org.apache.commons.lang3.StringUtils;

public enum Status {
    UNCONFIRMED, CONFIRMED;

    public static Status fromStringOrNull(String value) {
        if (StringUtils.isNotBlank(value)) {
            for (Status status : Status.values()) {
                if (value.equalsIgnoreCase(status.name())) {
                    return status;
                }
            }
        }
        return null;
    }
}
