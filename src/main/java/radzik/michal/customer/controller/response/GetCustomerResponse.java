package radzik.michal.customer.controller.response;

import radzik.michal.customer.domain.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GetCustomerResponse {
    private CustomerDto customer;
}
