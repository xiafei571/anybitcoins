package com.anymind.anybitcoins.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anymind.anybitcoins.model.WalletRecord;
import com.anymind.anybitcoins.service.BitcoinService;
import com.anymind.anybitcoins.service.exception.ErrorCodeEnum;
import com.anymind.anybitcoins.service.init.RecordInfo;
import com.anymind.anybitcoins.service.response.Result;

@Service
public class BitcoinServiceMemoryImpl implements BitcoinService {
	private static double currAmount = 0;

	@Override
	public Result<String> addBitcoin(WalletRecord walletRecord) {
		if (walletRecord.getAmount() == null || walletRecord.getAmount() <= 0 || walletRecord.getDatetime() == null) {
			return new Result<String>(ErrorCodeEnum.INVALID_PARAMETER);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(walletRecord.getDatetime());

		String key = getKeyByDate(calendar);
		int hour_24 = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, hour_24);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);

		WalletRecord[] records = RecordInfo.cache.getOrDefault(key, new WalletRecord[24]);
		WalletRecord record = records[hour_24];

		currAmount += walletRecord.getAmount();

		if (record == null) {
			record = new WalletRecord(calendar.getTime(), currAmount);
		} else {
			record.setAmount(currAmount);
		}
		records[hour_24] = record;
		RecordInfo.cache.put(key, records);
		return new Result<String>(String.valueOf(currAmount));
	}

	@Override
	public Result<List<WalletRecord>> showHistory(Date startDatetime, Date endDatetime) {
		if (startDatetime == null || endDatetime == null) {
			return new Result<>(ErrorCodeEnum.INVALID_PARAMETER);
		}
		try {
			List<String> keys = getBetweenDate(startDatetime, endDatetime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDatetime);
			int hour_start = calendar.get(Calendar.HOUR_OF_DAY);
			calendar.setTime(endDatetime);
			int hour_end = calendar.get(Calendar.HOUR_OF_DAY);
			
			List<WalletRecord> list = new ArrayList<WalletRecord>();
			for (int k = 0; k < keys.size(); k++) {
				String key = keys.get(k);
				WalletRecord[] records = RecordInfo.cache.get(key);
				if (records == null) {
					return new Result<>();
				}
				for (int i = 0; i < records.length; i++) {
					if (records[i] != null) {
						if(k == 0 && i < hour_start) {
							continue;
						}else if(k == keys.size() - 1 && i > hour_end) {
							continue;
						}else {
							list.add(records[i]);
						}
					}
				}
			}
			Result<List<WalletRecord>> result = new Result<List<WalletRecord>>(list);
			return result;
		} catch (Exception e) {
			return new Result<List<WalletRecord>>(ErrorCodeEnum.INVALID_DATETIME);
		}
	}

	private static String[] MONTH_MAP = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private static String[] DAY_MAP = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
			"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
			"31" };

	private static String getKeyByDate(Calendar calendar) {
		StringBuilder sb = new StringBuilder();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);

		sb.append(year).append("-").append(MONTH_MAP[month]).append("-").append(DAY_MAP[day]);
		return sb.toString();
	}

	public static List<String> getBetweenDate(Date startDatetime, Date endDatetime) throws Exception {
		if (startDatetime.compareTo(endDatetime) > 0) {
			throw new Exception();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		while (startDatetime.getTime() <= endDatetime.getTime()) {
			list.add(sdf.format(startDatetime));
			calendar.setTime(startDatetime);
			calendar.add(Calendar.DATE, 1);
			startDatetime = calendar.getTime();
		}
		return list;
	}
}
