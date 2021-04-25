package com.oev.apis.grosspension.service;

import com.oev.apis.grosspension.model.GrossPensionRequest;
import com.oev.apis.grosspension.model.GrossPensionResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
// todo: add unit tests
public class GrossPensionMapper {
    public GrossPensionResponse map(GrossPensionRequest grossPensionRequest, BigDecimal grossPension) {
        return GrossPensionResponse.builder()
                .age(grossPensionRequest.getAge())
                .grossAnnualSalary(grossPensionRequest.getGrossAnnualSalary())
                .startOfEmployment(grossPensionRequest.getStartOfEmployment())
                .grossPension(grossPension)
                .build();
    }
}
