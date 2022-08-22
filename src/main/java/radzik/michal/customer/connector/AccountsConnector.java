package radzik.michal.customer.connector;

import radzik.michal.customer.configuration.RibbonConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@FeignClient(name = "accounts", fallback = AccountsConnectorFallback.class)
@RibbonClient(name = "accounts", configuration = RibbonConfiguration.class)
public interface AccountsConnector {
    @GetMapping("/v1/accounts")
    GetAccountsResponse getAccounts(@RequestParam("customerId") Long customerId);
}


@Slf4j
@Component
class AccountsConnectorFallback implements AccountsConnector{

    @Override
    public GetAccountsResponse getAccounts(Long customerId) {
       log.warn("Can not get accounts for customerId {}", customerId);

        return new GetAccountsResponse(Collections.emptyList());
    }
}
