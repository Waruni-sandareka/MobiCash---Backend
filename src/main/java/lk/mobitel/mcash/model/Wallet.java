package lk.mobitel.mcash.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Wallet {
	@Id
	private int mobileno;
	private double amount;
	private int pin;
	
	
	

}
