package com.project.hexagonal.application.core.useCase;

import com.project.hexagonal.application.core.domain.Customer;
import com.project.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.project.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import com.project.hexagonal.application.ports.out.InsertCustomerOutputPort;
import jdk.dynalink.linker.LinkerServices;

public class InsertCustomerUseCase implements InsertCustomerInputPort {

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;

    private final InsertCustomerOutputPort insertCustomerOutputPort;

    public InsertCustomerUseCase (FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort,
                                  InsertCustomerOutputPort insertCustomerOutputPort
    ) {
        this.findAddressByZipCodeOutputPort = findAddressByZipCodeOutputPort;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
    }

    @Override
    public void insert(Customer customer, String zipCode){
        var address = findAddressByZipCodeOutputPort.find(zipCode);
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer);
    }
}
