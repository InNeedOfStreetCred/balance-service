package ch.cryptostorage.broker.balanceservice.web.api;

import java.util.List;

public class BalancesListResponse {

	private List<BalanceInfo> balances;

	public List<BalanceInfo> getBalances() {
		return balances;
	}

	public void setBalances(List<BalanceInfo> balances) {
		this.balances = balances;
	}
}
