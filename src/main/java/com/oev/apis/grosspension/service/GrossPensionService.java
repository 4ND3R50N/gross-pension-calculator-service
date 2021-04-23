package com.oev.apis.grosspension.service;

import com.oev.apis.grosspension.model.GrossPensionRequest;
import com.oev.apis.grosspension.model.GrossPensionResponse;
import org.springframework.stereotype.Service;

@Service
public class GrossPensionService {

    public GrossPensionResponse calculateGrossPension(GrossPensionRequest grossPensionRequest) {
        return GrossPensionResponse.builder()
                .age(grossPensionRequest.getAge())
                .grossAnnualSalary(grossPensionRequest.getGrossAnnualSalary())
                .startOfEmployment(grossPensionRequest.getStartOfEmployment())
                .grossPension(null)
                .build();
    }
}
