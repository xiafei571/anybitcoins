package com.anymind.anybitcoins.service;

import java.util.Date;
import java.util.List;

import com.anymind.anybitcoins.model.WalletRecord;
import com.anymind.anybitcoins.service.response.Result;

public interface BitcoinService {

	Result<String> addBitcoin(WalletRecord walletRecord);

	Result<List<WalletRecord>> showHistory(Date startDatetime, Date endDatetime);
}
