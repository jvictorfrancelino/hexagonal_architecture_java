package com.project.hexagonal.config;

import com.project.hexagonal.application.core.useCase.InsertCustomerUseCase;
import com.project.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import com.project.hexagonal.application.ports.out.InsertCustomerOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort,
                                                       InsertCustomerOutputPort insertCustomerOutputPort) {
        return new InsertCustomerUseCase(findAddressByZipCodeOutputPort, insertCustomerOutputPort);
    }
}