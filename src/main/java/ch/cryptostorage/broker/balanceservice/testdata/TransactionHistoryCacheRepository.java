package ch.cryptostorage.broker.balanceservice.testdata;

import ch.cryptostorage.broker.balanceservice.entities.TransactionHistoryCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryCacheRepository extends JpaRepository<TransactionHistoryCache, Long> {

}
