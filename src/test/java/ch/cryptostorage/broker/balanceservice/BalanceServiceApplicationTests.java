package ch.cryptostorage.broker.balanceservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.List;

import ch.cryptostorage.broker.balanceservice.web.api.BalanceHistory;
import ch.cryptostorage.broker.balanceservice.web.api.BalanceInfo;
import ch.cryptostorage.broker.balanceservice.web.api.BalancesListRequest;
import ch.cryptostorage.broker.balanceservice.web.api.BalancesListResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BalanceServiceApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(BalanceServiceApplicationTests.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private static final LocalDate START = LocalDate.of(2019, 8, 26);

	private static final LocalDate START_PLUS_3M = LocalDate.of(2019, 11, 26);

	private static final OffsetTime BALANCE_TIME_23_59 = OffsetTime.of(23, 59, 0, 0, ZoneOffset.UTC);

	@Test
	void getBalancesByAddresses() {
		String url = "http://localhost:" + port + "/reporting/balancesByAddresses";

		// get the balances every time at 23:59 for the 2 addresses during the period of 3 months
		BalancesListRequest request = new BalancesListRequest();
		request.setAddresses(List.of("Company", "Employee"));
		request.setFromDate(START);
		request.setToDate(START_PLUS_3M);
		request.setBalanceTime(BALANCE_TIME_23_59);

		ResponseEntity<BalancesListResponse> response =
				restTemplate.postForEntity(url, request, BalancesListResponse.class);

		assertThat(response.getStatusCodeValue(), is(200));
		BalancesListResponse body = response.getBody();
		List<BalanceInfo> balances = body.getBalances();
		assertThat(balances, not(empty()));
		for (BalanceInfo balance : balances) {
			LOGGER.info("Address='{}'", balance.getAddress());
			for (BalanceHistory balanceHistory : balance.getHistory()) {
				LOGGER.info("   timeStamp='{}', balance='{}'", balanceHistory.getTimestamp(), balanceHistory.getBalance());
			}

		}

	}
}
