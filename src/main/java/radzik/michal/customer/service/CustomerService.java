package radzik.michal.customer.service;

import radzik.michal.customer.domain.dao.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer account);

    Optional<Customer> findCustomer(long id);
}
