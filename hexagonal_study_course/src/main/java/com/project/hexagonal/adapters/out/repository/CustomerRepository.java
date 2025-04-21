package com.project.hexagonal.adapters.out.repository;

import com.project.hexagonal.adapters.out.repository.entity.CustomerEntity;
import com.project.hexagonal.application.core.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}
