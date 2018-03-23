package DAO;

import java.util.Map;

import POJO.Account;

public interface WalletDao {

	boolean save(Account acc);

	Account find(String mobileNumber);

	

}
