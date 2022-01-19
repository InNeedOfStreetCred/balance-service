package ch.cryptostorage.broker.balanceservice.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TransactionHistoryCacheInvolvedAddress {

	private @Id
	@GeneratedValue
	Long id;

	private String address;

	private BigDecimal transactedAmount;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private TransactionHistoryCache transactionHistoryCache;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getTransactedAmount() {
		return transactedAmount;
	}

	public void setTransactedAmount(BigDecimal amount) {
		this.transactedAmount = amount;
	}

	public TransactionHistoryCache getTransactionHistoryCache() {
		return transactionHistoryCache;
	}

	public void setTransactionHistoryCache(TransactionHistoryCache transactionHistoryCache) {
		this.transactionHistoryCache = transactionHistoryCache;
	}
}
