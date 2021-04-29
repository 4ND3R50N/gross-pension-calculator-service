package com.oev.apis.grosspension.service;

import com.oev.apis.grosspension.error.InvalidAgeException;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Component
public class GrossPensionCalculator {

    public static final int AGE_BORDER = 16;

    public BigDecimal calculate(@NonNull Integer age, @NonNull BigDecimal grossAnnualSalary, @NonNull LocalDate startOfEmployment) {

        if (age <= AGE_BORDER) {
            throw new InvalidAgeException(age);
        }

        int birthYear = LocalDate.now().getYear() - age;
        int retirementYears = RetirementYears.valueOf(birthYear).value;
        BigDecimal yearsToWorkUnitRetirement = getYearsToWorkUnitRetirement(birthYear, retirementYears, startOfEmployment);
        return grossPensionCalculation(yearsToWorkUnitRetirement, grossAnnualSalary);
    }

    private BigDecimal grossPensionCalculation(BigDecimal yearsToWorkUntilRetirement, BigDecimal grossAnnualSalary) {
        return grossAnnualSalary
                .divide(BigDecimal.valueOf(1000), 2, RoundingMode.CEILING)
                .multiply(yearsToWorkUntilRetirement);
    }

    private BigDecimal getYearsToWorkUnitRetirement(int birthYear, int retirementYears, LocalDate startOfEmployment) {
        int yearsUntilEmployment = startOfEmployment.getYear() - birthYear;
        return BigDecimal.valueOf(retirementYears - yearsUntilEmployment);
    }
}
