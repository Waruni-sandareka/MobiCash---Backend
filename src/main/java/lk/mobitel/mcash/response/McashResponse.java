package lk.mobitel.mcash.response;


import lk.mobitel.mcash.model.Wallet;
import lombok.Data;

@Data
public class McashResponse {
	private String message;
	private String code;
	private Wallet wallet;
	

}
