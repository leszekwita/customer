package radzik.michal.customer.mapper.impl;

import radzik.michal.customer.domain.dao.Customer;
import radzik.michal.customer.domain.dto.CustomerDto;
import radzik.michal.customer.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder().firstName(customer.getFirstName()).lastName(customer.getLastName()).id(customer.getId()).build();
    }

    @Override
    public Customer toDao(CustomerDto customerDto) {
        return Customer.builder().firstName(customerDto.getFirstName()).lastName(customerDto.getLastName()).build();
    }

    @Override
    public List<CustomerDto> toDtos(List<Customer> customers) {
        return customers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
