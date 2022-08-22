package radzik.michal.customer.connector;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountDto {
    private String nrb;
    private String currency;
    private BigDecimal availableFunds;

}
