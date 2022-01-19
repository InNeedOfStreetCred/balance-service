package ch.cryptostorage.broker.balanceservice.web.api;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.List;


public class BalancesListRequest {

	private List<String> addresses;

	private LocalDate fromDate;

	private LocalDate toDate;

	private OffsetTime balanceTime;

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public OffsetTime getBalanceTime() {
		return balanceTime;
	}

	public void setBalanceTime(OffsetTime balanceTime) {
		this.balanceTime = balanceTime;
	}
}
