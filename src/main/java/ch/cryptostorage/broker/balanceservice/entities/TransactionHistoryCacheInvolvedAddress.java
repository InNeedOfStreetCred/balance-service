package ch.cryptostorage.broker.balanceservice.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionHistoryCacheInvolvedAddress {

	@Id
	@GeneratedValue
	private	Long id;

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
