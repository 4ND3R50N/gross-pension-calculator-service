package com.oev.apis.grosspension.service;

import com.oev.apis.grosspension.model.GrossPensionRequest;
import com.oev.apis.grosspension.model.GrossPensionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
// todo: add unit tests
public class GrossPensionService {

    private final GrossPensionCalculator grossPensionCalculator;
    private final GrossPensionMapper grossPensionMapper;

    public GrossPensionResponse calculateGrossPension(GrossPensionRequest grossPensionRequest) {

        BigDecimal grossPension = grossPensionCalculator.calculate(grossPensionRequest.getAge(),
                grossPensionRequest.getGrossAnnualSalary(),
                grossPensionRequest.getStartOfEmployment());

        return grossPensionMapper.map(grossPensionRequest, grossPension);
    }
}
