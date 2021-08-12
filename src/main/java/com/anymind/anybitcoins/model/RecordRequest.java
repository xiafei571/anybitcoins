package com.anymind.anybitcoins.model;

import java.util.Date;

public class RecordRequest {

	private Date startDatetime;
	private Date endDatetime;
	
	public Date getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	public Date getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	
	@Override
	public String toString() {
		return "RecordRequest [startDatetime=" + startDatetime + ", endDatetime=" + endDatetime + "]";
	}
}
