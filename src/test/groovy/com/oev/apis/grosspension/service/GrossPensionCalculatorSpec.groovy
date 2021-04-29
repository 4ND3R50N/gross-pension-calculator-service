package com.oev.apis.grosspension.service

import com.oev.apis.grosspension.error.InvalidAgeException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDate

class GrossPensionCalculatorSpec extends Specification {

    @Subject
    GrossPensionCalculator grossPensionCalculator = new GrossPensionCalculator()

    @Unroll
    def "it should calculate #description"() {

        expect:
        expectedResult == grossPensionCalculator.calculate(age, grossAnnualSalary, startOfEmployment)

        where:
        description                                             | age | grossAnnualSalary | startOfEmployment         || expectedResult
        "with retirement years 66 due older or equal than 1958" | 64  | 50000.0           | LocalDate.of(2020, 1, 12) || BigDecimal.valueOf(150.00)
        "with retirement years 67 due younger than 1958"        | 63  | 50000.0           | LocalDate.of(2020, 1, 12) || BigDecimal.valueOf(250.00)
    }

    @Unroll
    def "it should throw exception if #description"() {
        given: "value that indicated an exception was thrown"
        Exception exceptionToValidate = null
        when:
        try {
            grossPensionCalculator.calculate(age, grossAnnualSalary, startOfEmployment)
        } catch (Exception e) {
            exceptionToValidate = e
        }

        then:
        exceptionToValidate.getClass().isInstance(expectedException)

        where:
        description                 | age  | grossAnnualSalary | startOfEmployment         || expectedException
        "age is null"               | null | 50000.0           | LocalDate.of(2020, 1, 12) || new NullPointerException()
        "grossAnnualSalary is null" | 63   | null              | LocalDate.of(2020, 1, 12) || new NullPointerException()
        "startOfEmployment is null" | 63   | 50000.0           | null                      || new NullPointerException()
        "age is not over 16"        | 16   | 50000.0           | LocalDate.of(2020, 1, 12) || new InvalidAgeException(age)
    }


}
