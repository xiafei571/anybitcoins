package com.anymind.anybitcoins.service.response;

import com.anymind.anybitcoins.service.exception.ErrorCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value=JsonInclude.Include.NON_EMPTY)
public class Result<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private int status = 0;

    private String msg;

    private T res;

    public Result() {
    }

    public Result(T res) {
        this.res = res;
    }


    public Result(ErrorCodeEnum errorCodeEnum) {
        this.status = errorCodeEnum.getStatus();
        this.msg = errorCodeEnum.getMsg();
    }

    public Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return status == 0 ? true : false;
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

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

	@Override
	public String toString() {
		return "Result [status=" + status + ", msg=" + msg + ", res=" + res + "]";
	}
}
