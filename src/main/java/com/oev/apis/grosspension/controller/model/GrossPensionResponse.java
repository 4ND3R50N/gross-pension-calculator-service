package com.oev.apis.grosspension.controller.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableGrossPensionResponse.class)
@JsonDeserialize(as = ImmutableGrossPensionResponse.class)
public interface GrossPensionResponse {

    BigDecimal getGrossPension();
    @NotNull
    Optional<LocalDate> getStartOfEmployment();

    Optional<Integer> getAge();

    Optional<BigDecimal> getGrossAnnualSalary();

}
