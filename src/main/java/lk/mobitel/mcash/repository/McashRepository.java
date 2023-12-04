package lk.mobitel.mcash.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lk.mobitel.mcash.model.Wallet;

public interface McashRepository extends CrudRepository<Wallet,Long> {
	@Query("SELECT w FROM Wallet w WHERE mobileno=:mobileno AND pin=:pin")
	public Wallet getBalance(@Param(value="mobileno")int mobileno,@Param(value="pin")int pin);
	
	@Query("SELECT w FROM Wallet w WHERE mobileno=:mobileno")
	public Wallet getBalanceWithoutPin(@Param(value="mobileno")int mobileno);
	
	@Modifying(flushAutomatically=true, clearAutomatically=true)
	@Query("UPDATE Wallet SET amount=:amount WHERE mobileno=:mobileno")
	public void updateBalance(@Param(value="mobileno") int mobileno,@Param(value="amount") double amount);
}
