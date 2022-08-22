package radzik.michal.customer.repository;


import radzik.michal.customer.domain.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> getCustomerById(Long id);
}