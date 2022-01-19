package ch.cryptostorage.broker.balanceservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.cryptostorage.broker.balanceservice.web.api.BalanceHistory;
import ch.cryptostorage.broker.balanceservice.web.api.BalanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportingService.class);

	@PersistenceContext
	private EntityManager em;

	public List<BalanceInfo> findBalancesForAddresses(
			List<String> addresses,
			LocalDate fromDate,
			LocalDate toDate,
			OffsetTime balanceTime) {

		LOGGER.info("Calculating balances for {} addresses each day at {} in date range '{}' to '{}'",
				addresses.size(), balanceTime, fromDate, toDate);

		OffsetDateTime from = OffsetDateTime.of(fromDate, balanceTime.toLocalTime(), balanceTime.getOffset());
		OffsetDateTime to = OffsetDateTime.of(toDate, balanceTime.toLocalTime(), balanceTime.getOffset());

		LOGGER.debug("OffsetDateTime from/to: '{}'/'{}'", from, to);

		// create the BalanceInfos for each address in parallel
		return addresses.parallelStream().map(address -> {
					BalanceInfo balanceInfo = new BalanceInfo();
					balanceInfo.setAddress(address);
					balanceInfo.setHistory(calculateBalancesForAddress(address, from, to));
					return balanceInfo;
				}).filter(balanceInfo -> !balanceInfo.getHistory().isEmpty())
				.collect(Collectors.toList());
	}

	private List<BalanceHistory> calculateBalancesForAddress(
			String address,
			final OffsetDateTime fromDate,
			final OffsetDateTime toDate) {

		// Create the BalanceHistory in the from/to range, one per day
		List<BalanceHistory> result = new ArrayList<>();
		OffsetDateTime date = fromDate;
		while (date.compareTo(toDate) <= 0) {
			BalanceHistory balanceHistory = new BalanceHistory();
			balanceHistory.setTimestamp(date);
			result.add(balanceHistory);
			date = date.plusDays(1);
		}

		// fetch the balances for each created balanceHistory entry
		// this runs in parallel
		result.parallelStream().forEach(balanceHistory ->
				balanceHistory.setBalance(calculateBalance(address, balanceHistory.getTimestamp()).toPlainString()));

		return result;
	}

	private BigDecimal calculateBalance(String address, OffsetDateTime balanceTime) {
		BigDecimal balance = em.createQuery(
						"SELECT sum(a.transactedAmount) FROM TransactionHistoryCache c "
								+ "join TransactionHistoryCacheInvolvedAddress a on c.id = a.transactionHistoryCache.id "
								+ "where a.address=:address and c.transactionTimestamp<=:balanceTime",
						BigDecimal.class)
				.setParameter("address", address)
				.setParameter("balanceTime", balanceTime)
				.getSingleResult();
		em.close();
		if (balance == null) {
			balance = BigDecimal.ZERO;
		}
		LOGGER.debug("balance={} calculated for address {} until {}", balance, address, balanceTime);
		return balance;
	}
}
