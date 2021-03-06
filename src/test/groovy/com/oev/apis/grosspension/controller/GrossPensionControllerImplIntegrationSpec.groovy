package com.oev.apis.grosspension.controller

import com.oev.apis.grosspension.model.GrossPensionRequest
import com.oev.apis.grosspension.model.GrossPensionResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.LocalDate

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GrossPensionControllerImplIntegrationSpec extends Specification {

    @Autowired
    TestRestTemplate testRestTemplate

    def "it should calculate gross pension"() {
        given: "valid request"
        GrossPensionRequest grossPensionRequest = GrossPensionRequest.builder()
                .age(20)
                .grossAnnualSalary(50000.0)
                .startOfEmployment(LocalDate.of(2020, 01, 01))
                .build()

        when: "api call is performed"
        ResponseEntity<GrossPensionResponse> result =
                testRestTemplate.postForEntity("/api/v1/calculate", grossPensionRequest, GrossPensionResponse.class)

        then: "gross pension has to be calculated correctly"
        with(result.getBody()) {
            getGrossPension() == 2400.0
        }
    }

    def "it should return InvalidAgeException / bad request if age is not fitting"() {
        given: "valid request"
        GrossPensionRequest grossPensionRequest = GrossPensionRequest.builder()
                .age(10)
                .grossAnnualSalary(50000.0)
                .startOfEmployment(LocalDate.of(2020, 01, 01))
                .build()

        when: "api call is performed"
        ResponseEntity<String> result =
                testRestTemplate.postForEntity("/api/v1/calculate", grossPensionRequest, String.class)

        then: "bad request should be returned as status"
        result.getStatusCode() == HttpStatus.BAD_REQUEST
    }

    def "it should return NullPointerException / 500 if required field are not set"() {
        given: "valid request"
        GrossPensionRequest grossPensionRequest = GrossPensionRequest.builder()
                .age(20)
                .startOfEmployment(LocalDate.of(2020, 01, 01))
                .build()

        when: "api call is performed"
        ResponseEntity<String> result =
                testRestTemplate.postForEntity("/api/v1/calculate", grossPensionRequest, String.class)

        then: "internal server error should be returned as status"
        result.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
    }

}
