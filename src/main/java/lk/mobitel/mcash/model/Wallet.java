package lk.mobitel.mcash.model;


import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;

@Data
@Entity
public class Wallet {
	@Id
	private int mobileno;
	private double amount;
	private int pin;
	
	
	

}
