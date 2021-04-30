package com.oev.apis.grosspension.controller;

import com.oev.apis.grosspension.controller.model.GrossPensionRequest;
import com.oev.apis.grosspension.controller.model.GrossPensionResponse;
import com.oev.apis.grosspension.service.GrossPensionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("${app.current-api-version}")
public class GrossPensionController {

    private final GrossPensionService grossPensionService;

    @PostMapping(value = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GrossPensionResponse> grossPensionCalculation(
            @NonNull @RequestBody GrossPensionRequest grossPensionRequest) {
        return ResponseEntity.ok(grossPensionService.calculateGrossPension(grossPensionRequest));
    }

}
