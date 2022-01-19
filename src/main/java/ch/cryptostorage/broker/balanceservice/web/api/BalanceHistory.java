package ch.cryptostorage.broker.balanceservice.web.api;

import java.time.OffsetDateTime;

/**
 * Balance of at a certain time
 */
public class BalanceHistory {

	private String balance;

	private OffsetDateTime timestamp;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
