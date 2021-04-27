package com.oev.apis.grosspension.service;

import com.oev.apis.grosspension.model.GrossPensionRequest;
import com.oev.apis.grosspension.model.GrossPensionResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GrossPensionMapper {
    public GrossPensionResponse map(@NonNull GrossPensionRequest grossPensionRequest, @NonNull BigDecimal grossPension) {
        return GrossPensionResponse.builder()
                .age(grossPensionRequest.getAge())
                .grossAnnualSalary(grossPensionRequest.getGrossAnnualSalary())
                .startOfEmployment(grossPensionRequest.getStartOfEmployment())
                .grossPension(grossPension)
                .build();
    }
}
