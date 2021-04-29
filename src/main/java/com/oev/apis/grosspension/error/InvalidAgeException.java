package com.oev.apis.grosspension.error;

import lombok.NonNull;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(@NonNull Integer age) {
        super("Age is invalid. Age: " + age + ", required: > 16");
    }
}
