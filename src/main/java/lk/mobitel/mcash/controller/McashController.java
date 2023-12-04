package lk.mobitel.mcash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.mobitel.mcash.model.MoneyTransfer;
import lk.mobitel.mcash.model.Wallet;
import lk.mobitel.mcash.model.WithdrawalObject;
import lk.mobitel.mcash.response.McashResponse;
import lk.mobitel.mcash.service.McashService;

@RestController
public class McashController {
	
	@Autowired
	McashService mcashService;
	
	@PostMapping("/createWallet")
	public McashResponse createWallet(@RequestBody Wallet wallet) {
		return mcashService.create(wallet);
	}
	
	@PostMapping("/checkBalance")
	public McashResponse getExistingBalance(@RequestBody Wallet wallet) {
		return mcashService.checkBalance(wallet.getMobileno(),wallet.getPin());
	}
	
	@PostMapping("/moneyTransfer")
	public McashResponse moneyTransfer(@RequestBody MoneyTransfer moneyTransfer) {
		return mcashService.moneyTransfer(moneyTransfer);
	}
	
	@PostMapping("/moneyWithdrawal")
	public McashResponse moneyWithdrawal(@RequestBody WithdrawalObject moneyWithdrawal) {
		return mcashService.moneyWithdrawal(moneyWithdrawal);
	}

}
