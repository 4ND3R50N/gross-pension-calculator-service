package com.oev.apis.grosspension.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
@RequiredArgsConstructor
public class ProblemConfiguration implements InitializingBean {

    private final ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet() {
        objectMapper.registerModules(new ProblemModule(), new ConstraintViolationProblemModule());
    }
}