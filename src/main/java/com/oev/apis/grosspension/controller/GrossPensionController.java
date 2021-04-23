package com.oev.apis.grosspension.controller;

import com.oev.apis.grosspension.api.CalculateApiDelegate;
import com.oev.apis.grosspension.model.GrossPensionRequest;
import com.oev.apis.grosspension.model.GrossPensionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GrossPensionController implements CalculateApiDelegate {

    @Override
    public ResponseEntity<GrossPensionResponse> grossPensionCalculation(GrossPensionRequest grossPensionRequest) {
        return ResponseEntity.ok(new GrossPensionResponse());
    }

}
