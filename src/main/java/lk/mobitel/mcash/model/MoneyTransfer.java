package lk.mobitel.mcash.model;

import lombok.Data;

@Data
public class MoneyTransfer {
	int fromnumber;
	int tonumber;
	int pin;
	double amount;

}
