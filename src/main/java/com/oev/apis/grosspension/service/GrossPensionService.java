package com.oev.apis.grosspension.service;

import com.oev.apis.grosspension.controller.model.GrossPensionRequest;
import com.oev.apis.grosspension.controller.model.GrossPensionResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GrossPensionService {

    private final GrossPensionCalculator grossPensionCalculator;
    private final GrossPensionMapper grossPensionMapper;
    private final Logger logger;

    public GrossPensionResponse calculateGrossPension(@NonNull GrossPensionRequest grossPensionRequest) {

        BigDecimal grossPension = grossPensionCalculator.calculate(grossPensionRequest.getAge(),
                grossPensionRequest.getGrossAnnualSalary(),
                grossPensionRequest.getStartOfEmployment());

        GrossPensionResponse response = grossPensionMapper.map(grossPensionRequest, grossPension);
        logger.debug("Successfully processed request: {}. Calculated value: {}",
                grossPensionRequest,
                response.getGrossPension());
        return response;
    }
}
