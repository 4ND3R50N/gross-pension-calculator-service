package com.oev.apis.grosspension

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIntegrationSpec extends Specification {

    @Autowired
    ApplicationContext applicationContext

    def "api context should boot"() {
        expect: "api context should booted successfully"
        applicationContext != null

    }
}
