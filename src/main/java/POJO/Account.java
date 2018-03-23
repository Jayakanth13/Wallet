package POJO;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String mobile;
	private BigDecimal amount;
	
	//private List<Transactions> trx;


	@OneToOne(cascade = { CascadeType.ALL })
	private Wallet wallet;

	public String getMobilenNumber() {
		return mobile;
	}

	String name;

	public String getName() {
		return name;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public Account(String mobileNumber, String name, BigDecimal amount, Wallet wallet) {
		this.mobile = mobileNumber;
		this.name = name;
		this.wallet = wallet;

	}

	public Account() {

	}

}
