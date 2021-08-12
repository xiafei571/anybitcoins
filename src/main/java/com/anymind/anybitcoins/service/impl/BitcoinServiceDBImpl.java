package com.anymind.anybitcoins.service.impl;

import java.util.Date;
import java.util.List;

import com.anymind.anybitcoins.model.WalletRecord;
import com.anymind.anybitcoins.service.BitcoinService;
import com.anymind.anybitcoins.service.response.Result;

public class BitcoinServiceDBImpl implements BitcoinService {

	@Override
	public Result<String> addBitcoin(WalletRecord walletRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<WalletRecord>> showHistory(Date startDatetime, Date endDatetime) {
		// TODO Auto-generated method stub
		return null;
	}

}
