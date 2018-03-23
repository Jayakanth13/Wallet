package DAO;

import java.math.BigDecimal;
import java.sql.SQLException;

import POJO.Account;

public interface WalletServiceInterface {

	Account createAccount(String mobileNumber, String name, BigDecimal amount) throws InvalidRequestException, SQLException;

	Account showBalance(String mobileNumber) throws InvalidRequestException, SQLException;

	Account deposit(String mobileNumber, double amount) throws InvalidRequestException, SQLException;

	Account withdraw(String mobileNumber, double amount) throws SQLException;

}
