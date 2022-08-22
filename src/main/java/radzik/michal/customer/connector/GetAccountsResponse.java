package radzik.michal.customer.connector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GetAccountsResponse {

    private List<AccountDto> accounts;
}
