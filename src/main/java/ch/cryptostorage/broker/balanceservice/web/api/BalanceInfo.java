package ch.cryptostorage.broker.balanceservice.web.api;

import java.util.List;

public class BalanceInfo {

	private String address;

	private List<BalanceHistory> history;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<BalanceHistory> getHistory() {
		return history;
	}

	public void setHistory(List<BalanceHistory> history) {
		this.history = history;
	}
}
