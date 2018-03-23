package DAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import POJO.Account;
import POJO.Wallet;

@Service
public class WalletService implements WalletServiceInterface {

	
	@Autowired
	private AccountRepository wRepo;
	

	public WalletService() {
	

	}

	public Account createAccount(String mobileNumber, String name, BigDecimal amount) {

		Account newAccount = null;
		if (mobileNumber != null && name != null) {
			if (wRepo != null)
				System.out.println("WalletRepoJDBC wRepo  initialized");

			Account duplicateAccount = wRepo.findOneByMobile(mobileNumber);

			if (duplicateAccount != null) {
				System.out.println("Enters if duplicate block");
				throw new InvalidRequestException(
						"Cannot create account because account with this mobile number already exists");

			} else {

				System.out.println("duplicateAccount is null");
			}

			// System.out.println(mobileNumber + "Length :" + mobileNumber.length());

			if (mobileNumber.length() == 8) {
				System.out.println("Enters mobile length block in create Account");
			} else {
				System.out.println("Enters else mobile length block");
				throw new InvalidRequestException("Invalid mobile number length");

			}

			String regex = "\\d+";
			if (!Pattern.matches(regex, mobileNumber)) {
				throw new InvalidRequestException("Mobile number cannot contain non numerical values");

			}

			if (amount.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(BigDecimal.ZERO) > 100000) {
				throw new InvalidRequestException("Deposit is out of limits");

			}

			if (name.length() == 0 || name.trim().length() == 0) {
				throw new InvalidRequestException("Name cannot be empty");

			}

			String regexCharacters = ".*[a-zA-Z]+.*[a-zA-Z]";
			if (!Pattern.matches(regexCharacters, name))
				throw new InvalidRequestException("Name cannot include special characters");

		}

		if (mobileNumber == null) {
			throw new InvalidRequestException("Mobile number cannot be NULL");

		}

		if (name == null) {
			throw new InvalidRequestException("Name cannot be NULL");

		}

		Wallet newWallet = new Wallet(amount);
		System.out.println("Creating account with " + mobileNumber);
		newAccount = new Account(mobileNumber, name, amount, newWallet);

		wRepo.save(newAccount);
		/*
		 * System.out.println("this was added and found in the map " +
		 * wRepo.find(mobileNumber).getMobilenNumber());
		 * 
		 * System.out.println("createAccount " + wRepo.getAccountMap());
		 */

		return newAccount;

	}

	public Account showBalance(String mobileNumber) throws InvalidRequestException, SQLException {

	

		if (mobileNumber != null) {

			if (mobileNumber.length() != 8) {

				throw new InvalidRequestException("Invalid mobile number");
			}

			String regex = "\\d+";
			if (!Pattern.matches(regex, mobileNumber)) {
				throw new InvalidRequestException("Mobile number cannot contain non numerical values");

			}

			Account acc = wRepo.findOneByMobile(mobileNumber);

			if (acc == null) {
				System.out.println("Can't find " + mobileNumber + "while executing find method in showBalance method");

				throw new InvalidRequestException("Invalid mobile number");
			}

			if (!(wRepo.findOneByMobile(mobileNumber).getWallet().getAmount().compareTo(BigDecimal.ZERO) < 100000)) {

				throw new InvalidRequestException("Invalid balance amount");
			}
			return acc;
		}

		else {

			throw new InvalidRequestException("Mobile number cannot be NULL");

		}
		
		

	}

	public Account deposit(String mobileNumber, double amount) throws InvalidRequestException, SQLException {

		if (mobileNumber != null) {
			if (mobileNumber.length() != 8) {
				throw new InvalidRequestException("Invalid mobile number");
			}

			String regex = "\\d+";
			if (!Pattern.matches(regex, mobileNumber)) {
				throw new InvalidRequestException("Mobile number cannot contain non numerical values");
			}

			if (wRepo.findOneByMobile(mobileNumber) == null) {
				throw new InvalidRequestException("Mobile number does not exist");
			}

		} else {
			throw new InvalidRequestException("Mobile number cannot be NULL");
		}

		if (amount < 0 || amount > 100000) {
			throw new InvalidRequestException("Deposit is out of limits");
		}

		System.out.println(wRepo.findOneByMobile(mobileNumber).getWallet().getAmount().compareTo(BigDecimal.ZERO));

		Account retrievedAccount = wRepo.findOneByMobile(mobileNumber);
		Wallet retrievedWallet = retrievedAccount.getWallet();
		BigDecimal balance = retrievedWallet.getAmount();

		if (balance.intValue() + amount < 100000) {
			retrievedWallet.setAmount(new BigDecimal(balance.doubleValue() + amount));

		} else {
			throw new InvalidRequestException("Maximum balance can only be 100000");

		}

		wRepo.save(retrievedAccount);

		return retrievedAccount;

	}

	public Account withdraw(String mobileNumber, double amount) throws SQLException {

		if (mobileNumber != null) {
			if (mobileNumber.length() != 8) {
				throw new InvalidRequestException("Invalid mobile number");
			}

			String regex = "\\d+";
			if (!Pattern.matches(regex, mobileNumber)) {
				throw new InvalidRequestException("Mobile number cannot contain non numerical values");
			}

			if (wRepo.findOneByMobile(mobileNumber) == null) {
				throw new InvalidRequestException("Mobile number does not exist");
			}

			if (amount < 0 || amount > 100000) {

				throw new InvalidRequestException("Withdrawal is out of limits");
			}

		} else {
			throw new InvalidRequestException("Mobile number cannot be NULL");
		}

		Account retrievedAccount = wRepo.findOneByMobile(mobileNumber);
		Wallet retrievedWallet = retrievedAccount.getWallet();
		BigDecimal balance = retrievedWallet.getAmount();
		Account withdrawAcc;

		if (balance.doubleValue() - amount < 0) {
			throw new InvalidRequestException("Insufficient funds for withdrawal");

		} else {
			// perform withdrawal
			retrievedWallet.setAmount(new BigDecimal( balance.doubleValue()  -amount));
			wRepo.save(retrievedAccount);
			withdrawAcc = wRepo.findOneByMobile(mobileNumber);

		}

		return withdrawAcc;
		
	}

	

}
