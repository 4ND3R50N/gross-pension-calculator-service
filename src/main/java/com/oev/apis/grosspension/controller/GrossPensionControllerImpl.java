package com.oev.apis.grosspension.controller;

import com.oev.apis.grosspension.api.CalculateApiDelegate;
import com.oev.apis.grosspension.model.GrossPensionRequest;
import com.oev.apis.grosspension.model.GrossPensionResponse;
import com.oev.apis.grosspension.service.GrossPensionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrossPensionControllerImpl implements CalculateApiDelegate {

    private final GrossPensionService grossPensionService;

    @Override
    public ResponseEntity<GrossPensionResponse> grossPensionCalculation(@NonNull GrossPensionRequest grossPensionRequest) {
        return ResponseEntity.ok(grossPensionService.calculateGrossPension(grossPensionRequest));
    }

}
