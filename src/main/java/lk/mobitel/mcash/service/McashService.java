package lk.mobitel.mcash.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.mobitel.mcash.model.MoneyTransfer;
import lk.mobitel.mcash.model.Wallet;
import lk.mobitel.mcash.model.WithdrawalObject;
import lk.mobitel.mcash.repository.McashRepository;
import lk.mobitel.mcash.response.McashResponse;

@Service
public class McashService {
	@Autowired
	McashRepository mcashRepository;
	public McashResponse create(Wallet wallet) {
		McashResponse response = new McashResponse();
		try {
			Wallet w = mcashRepository.save(wallet);
			response.setCode("100");
			response.setMessage("Successfully Created");
			response.setWallet(w);
			return response;
		}
		catch(Exception e) {
			response.setCode("-1");
			response.setMessage("Something went wrong");
			response.setWallet(wallet);
			return response;
		}
	}
	@Transactional
	public McashResponse checkBalance(int mobileno,int pin) {
		McashResponse res = new McashResponse();
		try {
			Wallet wallet = mcashRepository.getBalance(mobileno,pin);
			if(wallet==null||wallet.getMobileno()<1) {
				res.setCode("-100");
				res.setMessage("Invalid mobileno or password");
				res.setWallet(wallet);
				return res;
			}
			else {
				res.setCode("100");
				res.setMessage("Return your existing balance");
				res.setWallet(wallet);
				return res;
			}
		}
		catch(Exception e) {
			res.setCode("-100");
			res.setMessage("Exception occured");
			return res;
		}
	}
	@Transactional
	public McashResponse moneyTransfer(MoneyTransfer obj) {
		McashResponse res = new McashResponse();
		try {
			Wallet wallet = mcashRepository.getBalance(obj.getFromnumber(),obj.getPin());
			if(wallet!=null) {
				if(wallet.getAmount()>= obj.getAmount()) {
					Wallet w = mcashRepository.getBalanceWithoutPin(obj.getTonumber());
					double updatedToBalance = w.getAmount()+obj.getAmount();
					double updatedFrombalance = wallet.getAmount()-obj.getAmount();
					mcashRepository.updateBalance(obj.getTonumber(),updatedToBalance);
					mcashRepository.updateBalance(obj.getFromnumber(),updatedFrombalance);
					res.setCode("100");
					res.setMessage("Üpdated your Existing balance is " + updatedFrombalance);
					res.setWallet(wallet);
				}
				else {
					res.setCode("-300");
					res.setMessage("Not enough Your Account Balance");
					res.setWallet(wallet);
				}
			}
			else {
				res.setCode("-100");
				res.setMessage("Invalid mobileno or pin number");
			}
		}
		catch(Exception e) {
			res.setCode("-100");
			res.setMessage("Ëxception Occured");
		}
		return res;
	}
	@Transactional
	public McashResponse moneyWithdrawal(WithdrawalObject obj) {
		McashResponse res = new McashResponse();
		try {
			Wallet wallet = mcashRepository.getBalance(obj.getMobileno(),obj.getPin());
			if(wallet.getAmount()>=obj.getAmount()) {
				double updateWithdrawalBalance = wallet.getAmount()-obj.getAmount();
				mcashRepository.updateBalance(obj.getMobileno(), updateWithdrawalBalance);
				res.setCode("100");
				res.setMessage("Withdrawal is Success");
				res.setWallet(wallet);
				return res;
			}
			else {
				res.setCode("-100");
				res.setMessage("Not Enough Money in Your Wallet");
				res.setWallet(wallet);
				return res;
			}
		}
		catch(Exception e) {
			res.setCode("-300");
			res.setMessage("Exception Occured");
			return res;
		}
	}
	
	
}






