package com.anymind.anybitcoins.service.exception;

public enum ErrorCodeEnum {

	INVALID_PARAMETER(9000, "Invalid parameters"),
	INVALID_DATETIME(9001, "Invalid datetime"),
	SYSTEM_ERROR(9999, "System error, Please contact the administrator");

	private int status;

	private String msg;

	private ErrorCodeEnum(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
