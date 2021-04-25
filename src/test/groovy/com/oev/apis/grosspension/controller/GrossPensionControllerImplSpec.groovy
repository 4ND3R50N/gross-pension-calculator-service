package com.oev.apis.grosspension.controller

import com.oev.apis.grosspension.model.GrossPensionRequest
import com.oev.apis.grosspension.model.GrossPensionResponse
import com.oev.apis.grosspension.service.GrossPensionService
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class GrossPensionControllerImplSpec extends Specification {

    GrossPensionService grossPensionService = Mock()

    @Subject
    GrossPensionControllerImpl grossPensionController = new GrossPensionControllerImpl(grossPensionService)

    def "it should throw exception if controller parameter is null"() {

        when: "controller parameter is null"
        grossPensionController.grossPensionCalculation(null)

        then: "it should throw exception"
        thrown(NullPointerException)
    }

    def "it should use the service"() {

        given: "valid payload"
        GrossPensionRequest grossPensionRequest = GrossPensionRequest.builder()
                .age(18)
                .grossAnnualSalary(30000.0)
                .startOfEmployment(LocalDate.now())
                .build()
        and: "mocked response from service"
        GrossPensionResponse grossPensionResponse = Mock(GrossPensionResponse)

        and: "service should be used once"
        1 * grossPensionService.calculateGrossPension(grossPensionRequest) >> grossPensionResponse

        when: "controller parameter is null"
        GrossPensionResponse result = grossPensionController.grossPensionCalculation(grossPensionRequest).getBody()

        then: "result should be the the mapped response from service"
        result == grossPensionResponse
    }

}
