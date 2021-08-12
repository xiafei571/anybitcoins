package com.anymind.anybitcoins.service.init;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.anymind.anybitcoins.model.WalletRecord;

@Component
public class RecordInfo implements InitializingBean{
	
	public static Map<String, WalletRecord[]> cache;

	@Override
	public void afterPropertiesSet() throws Exception {
		cache = new HashMap<String, WalletRecord[]>();
	}

}
