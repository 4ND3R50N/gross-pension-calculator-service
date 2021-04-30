package com.oev.apis.grosspension.service

import com.oev.apis.grosspension.model.GrossPensionRequest
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDate

class GrossPensionMapperSpec extends Specification {

    @Subject
    GrossPensionMapper grossPensionMapper = new GrossPensionMapper()

    @Unroll
    def "it should #description"() {

        given: "Validation indicator"
        boolean isValid

        when: "we check the postal code against the respective country regEx"
        try {
            grossPensionMapper.map(request, grossPension)
            isValid = true
        } catch (NullPointerException ignored) {
            isValid = false
        }

        then: "we compare the result of the apply with our expectation"
        expectedResult == isValid

        where:
        description                            | request           | grossPension || expectedResult
        "fail if request is null"              | null              | 30000.0      || false
        "fail if grossPension is null"         | getValidRequest() | null         || false
        "map successful if payload is correct" | getValidRequest() | 30000.0      || true

    }

    private static GrossPensionRequest getValidRequest() {
        return GrossPensionRequest.builder()
                .age(18)
                .grossAnnualSalary(30000.0)
                .startOfEmployment(LocalDate.now())
                .build();
    }
}
