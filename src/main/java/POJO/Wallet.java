package POJO;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	BigDecimal amount;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Wallet(BigDecimal amount) {
		super();

		this.amount = amount;
	}
	public Wallet() {
			
	}
	
	

}
