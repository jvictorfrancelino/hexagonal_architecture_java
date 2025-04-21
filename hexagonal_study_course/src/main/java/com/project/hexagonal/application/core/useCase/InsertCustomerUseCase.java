package com.project.hexagonal.application.core.useCase;

import com.project.hexagonal.application.core.domain.Customer;
import com.project.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import com.project.hexagonal.application.ports.out.InsertCustomerOutputPort;
import jdk.dynalink.linker.LinkerServices;

public class InsertCustomerUseCase {

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;

    private final InsertCustomerOutputPort insertCustomerOutputPort;

    public InsertCustomerUseCase (FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort,
                                  InsertCustomerOutputPort insertCustomerOutputPort
    ) {
        this.findAddressByZipCodeOutputPort = findAddressByZipCodeOutputPort;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
    }

    public void insert(Customer customer, String zipCode){
        var address = findAddressByZipCodeOutputPort.find(zipCode);
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer);
    }


}
