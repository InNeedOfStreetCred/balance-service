package ch.cryptostorage.broker.balanceservice.testdata;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ch.cryptostorage.broker.balanceservice.entities.TransactionHistoryCache;
import ch.cryptostorage.broker.balanceservice.entities.TransactionHistoryCacheInvolvedAddress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * loads some testdata into the database.
 * Would normally be done only when we run tests, but for the sake of
 * keeping the scope smaller I used this workaround.
 */
@Configuration
public class LoadDatabase {

	@Bean
	public CommandLineRunner initDatabase(TransactionHistoryCacheRepository repository) {
		return args -> {
			// investor founds the company
			foundCompany(repository);
			// employee gets paid every month
			for (int i = 0; i < 100; i++) {
				payEmployee(repository, (2019 + i / 12), (i % 12) + 1);
			}
		};
	}

	private void foundCompany(TransactionHistoryCacheRepository repository) {
		TransactionHistoryCache entry = new TransactionHistoryCache();
		entry.setTransactionTimestamp(
				OffsetDateTime.parse("2000-12-03T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
		);

		TransactionHistoryCacheInvolvedAddress from = new TransactionHistoryCacheInvolvedAddress();
		from.setAddress("Investor");
		from.setTransactedAmount(BigDecimal.valueOf(-1000000));
		from.setTransactionHistoryCache(entry);

		TransactionHistoryCacheInvolvedAddress to = new TransactionHistoryCacheInvolvedAddress();
		to.setAddress("Company");
		to.setTransactedAmount(BigDecimal.valueOf(1000000));
		to.setTransactionHistoryCache(entry);

		entry.setInvolvedAddresses(List.of(to, from));

		repository.save(entry);
	}

	private void payEmployee(TransactionHistoryCacheRepository repository, int year, int month) {
		TransactionHistoryCache entry = new TransactionHistoryCache();
		entry.setTransactionTimestamp(
				OffsetDateTime.parse("2000-12-24T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
						.withMonth(month)
						.withYear(year)
		);
		TransactionHistoryCacheInvolvedAddress from = new TransactionHistoryCacheInvolvedAddress();
		from.setAddress("Company");
		from.setTransactedAmount(BigDecimal.valueOf(-6000));
		from.setTransactionHistoryCache(entry);

		TransactionHistoryCacheInvolvedAddress to = new TransactionHistoryCacheInvolvedAddress();
		to.setAddress("Employee");
		to.setTransactedAmount(BigDecimal.valueOf(6000));
		to.setTransactionHistoryCache(entry);

		entry.setInvolvedAddresses(List.of(to, from));

		repository.save(entry);
	}
}
