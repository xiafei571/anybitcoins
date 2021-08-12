package com.anymind.anybitcoins.model;

import java.util.Date;

public class WalletRecord {
	private Date datetime;
	private Double amount;
	
	public WalletRecord(Date datetime, Double amount) {
		super();
		this.datetime = datetime;
		this.amount = amount;
	}
	
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "WalletRecord [datetime=" + datetime + ", amount=" + amount + "]";
	}
}
