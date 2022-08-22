package radzik.michal.customer.controller;

import radzik.michal.customer.connector.AccountDto;
import radzik.michal.customer.controller.response.GetCustomerProductsResponse;
import radzik.michal.customer.controller.response.GetCustomerResponse;
import radzik.michal.customer.domain.dao.Customer;
import radzik.michal.customer.domain.dto.CustomerDto;
import radzik.michal.customer.mapper.CustomerMapper;
import radzik.michal.customer.service.CustomerService;
import radzik.michal.customer.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RefreshScope
@RequestMapping(value = "/v1/customer", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CustomerController {

    private final CustomerService customerService;
    private final ProductService productService;

    private final CustomerMapper customerMapper;


    @Value("${application.allow-get-customer}")
    private boolean allowGetCustomer;

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerDto customer) {
        return customerMapper.toDto(customerService.saveCustomer(customerMapper.toDao(customer)));
    }

/*    @ResponseBody
    @RequestMapping(value="/api/customer/{id}", produces = "application/json",
            method=RequestMethod.GET)
    public CustomerDto getCustomerById(@PathVariable ("id") Long id){
        if(!allowGetCustomer){
            log.info("Getting customer is disabled");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting customer is disabled");
        }
        return customerMapper.toDto(customerService.findCustomer(id));
    }*/

    @GetMapping("/{customerId}")
    public GetCustomerResponse getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.findCustomer(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
               return  GetCustomerResponse.of(customerMapper.toDto(customer));

    }

    @GetMapping("/{customerId}/products")
    public GetCustomerProductsResponse getCustomerProducts(@PathVariable Long customerId) {
        log.info("Get getCustomerProducts for customerId:{}", customerId);
        CustomerDto customerDto = customerMapper.toDto(customerService.findCustomer(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));

        List<AccountDto> customerAccounts = productService.findCustomerAccounts(customerId);

        return GetCustomerProductsResponse.builder()
                .customerId(customerDto.getId())
                .fullName(customerDto.getFirstName() + " " + customerDto.getLastName())
                .accounts(customerAccounts)
                .build();
    }
}
