package com.oev.apis.grosspension.controller.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value.Immutable
@JsonSerialize(as = ImmutableGrossPensionRequest.class)
@JsonDeserialize(as = ImmutableGrossPensionRequest.class)
public interface GrossPensionRequest {

    @NotNull
    LocalDate getStartOfEmployment();

    @NotNull
    int getAge();

    BigDecimal getGrossAnnualSalary();
}
