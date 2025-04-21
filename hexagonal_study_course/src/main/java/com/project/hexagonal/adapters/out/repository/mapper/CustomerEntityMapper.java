package com.project.hexagonal.adapters.out.repository.mapper;

import com.project.hexagonal.adapters.out.repository.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    static CustomerEntity toCustomerEntity(Customer customer);
}
