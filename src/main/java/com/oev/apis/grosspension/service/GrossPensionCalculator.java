package com.oev.apis.grosspension.service;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
// todo: add unit tests
public class GrossPensionCalculator {
    public BigDecimal calculate(int age, @NonNull BigDecimal grossAnnualSalary, @NonNull LocalDate startOfEmployment) {
        // todo: implement validation
        // todo: implement calculation
        return BigDecimal.ZERO;
    }
}
