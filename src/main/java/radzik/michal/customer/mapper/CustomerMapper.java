package radzik.michal.customer.mapper;

import radzik.michal.customer.domain.dao.Customer;
import radzik.michal.customer.domain.dto.CustomerDto;

import java.util.List;

public interface CustomerMapper {

    CustomerDto toDto (Customer customer);

    Customer toDao (CustomerDto customerDto);

    List<CustomerDto> toDtos (List<Customer> customer);
}
