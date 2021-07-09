package com.oev.apis.grosspension.service

import com.oev.apis.grosspension.controller.model.GrossPensionRequest
import com.oev.apis.grosspension.controller.model.GrossPensionResponse
import com.oev.apis.grosspension.controller.model.ImmutableGrossPensionRequest
import org.slf4j.Logger
import spock.lang.Specification

import java.time.LocalDate

class GrossPensionServiceSpec extends Specification {

    GrossPensionMapper grossPensionMapper = Mock()
    GrossPensionCalculator grossPensionCalculator = Mock()
    Logger logger = Mock()

    GrossPensionService grossPensionService = new GrossPensionService(grossPensionCalculator, grossPensionMapper, logger)

    def "it should check nullpointer exception"() {

        when:
        grossPensionService.calculateGrossPension(null)

        then:
        thrown(NullPointerException)

    }

    def "it should run service"() {
        given: "gross pension payload"
        GrossPensionRequest grossPensionRequest = ImmutableGrossPensionRequest.builder()
                .age(18)
                .grossAnnualSalary(30000.0)
                .startOfEmployment(LocalDate.now())
                .build()
        and: "mocked calculation value"
        BigDecimal value = BigDecimal.valueOf(130000.0)

        and: "mocked mapping result"
        GrossPensionResponse mappingResult = Mock(GrossPensionResponse)

        when: "service is running"
        GrossPensionResponse result = grossPensionService.calculateGrossPension(grossPensionRequest)

        then: "calculator should be triggered once"
        1 * grossPensionCalculator.calculate(grossPensionRequest.getAge(),
                grossPensionRequest.getGrossAnnualSalary(),
                grossPensionRequest.getStartOfEmployment()) >> value

        and: "mapper should be triggered once"
        1 * grossPensionMapper.map(grossPensionRequest, value) >>mappingResult

        and: "return value of service should be equal to the mapper return value"
        result == mappingResult
    }
}
