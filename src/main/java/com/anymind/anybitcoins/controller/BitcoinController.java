package com.anymind.anybitcoins.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anymind.anybitcoins.model.RecordRequest;
import com.anymind.anybitcoins.model.WalletRecord;
import com.anymind.anybitcoins.service.BitcoinService;
import com.anymind.anybitcoins.service.response.Result;

@Controller
public class BitcoinController {

	@Resource
	private BitcoinService bitcoinService;

	@GetMapping("/index")
	public String index() {
		return "/index.html";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result<String> addBitCoin(@RequestBody WalletRecord record){
		return bitcoinService.addBitcoin(record);
	}

	@ResponseBody
	@RequestMapping(value = "/history", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result<List<WalletRecord>> getHistory(@RequestBody RecordRequest request) {
		return bitcoinService.showHistory(request.getStartDatetime(), request.getEndDatetime());
	}
}
