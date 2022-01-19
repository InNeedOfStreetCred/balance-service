package ch.cryptostorage.broker.balanceservice.entities;

import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransactionHistoryCache {

	private @Id
	@GeneratedValue
	Long id;

	private OffsetDateTime transactionTimestamp;

	@OneToMany(mappedBy = "transactionHistoryCache", cascade = CascadeType.ALL)
	private List<TransactionHistoryCacheInvolvedAddress> involvedAddresses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TransactionHistoryCacheInvolvedAddress> getInvolvedAddresses() {
		return involvedAddresses;
	}

	public void setInvolvedAddresses(
			List<TransactionHistoryCacheInvolvedAddress> involvedAddresses) {
		this.involvedAddresses = involvedAddresses;
	}

	public OffsetDateTime getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(OffsetDateTime transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}
}
