package ch.cryptostorage.broker.balanceservice.web;


import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.List;

import ch.cryptostorage.broker.balanceservice.service.ReportingService;
import ch.cryptostorage.broker.balanceservice.web.api.BalanceInfo;
import ch.cryptostorage.broker.balanceservice.web.api.BalancesListRequest;
import ch.cryptostorage.broker.balanceservice.web.api.BalancesListResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceService {

	private final ReportingService reportingService;

	public BalanceService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	@PostMapping("/reporting/balancesByAddresses")
	public BalancesListResponse getBalancesByAddresses(@RequestBody BalancesListRequest request) {
		List<String> addresses = request.getAddresses();
		LocalDate fromDate = request.getFromDate();
		LocalDate toDate = request.getToDate();
		OffsetTime balanceTime = request.getBalanceTime();
		List<BalanceInfo> balancesForAddresses =
				reportingService.findBalancesForAddresses(addresses, fromDate, toDate, balanceTime);

		BalancesListResponse response = new BalancesListResponse();
		response.setBalances(balancesForAddresses);
		return response;
	}
}
