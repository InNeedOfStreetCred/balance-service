package ch.cryptostorage.broker.balanceservice.entities;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TransactionHistoryCache {

	@Id
	@GeneratedValue
	private Long id;

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
