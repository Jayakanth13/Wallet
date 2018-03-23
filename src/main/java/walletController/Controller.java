package walletController;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpringStarter.DemoSpringStarterApplication;

import POJO.Account;
import DAO.AccountRepository;
import DAO.InvalidRequestException;
import DAO.WalletService;

@RestController
public class Controller {

	@RequestMapping("/")
	public String getString() {

		System.out.println("Controller called....");
		return "Hello World from Spring MVC";
	}

	@Autowired
	private WalletService wso;

	
	@RequestMapping(method = RequestMethod.POST, value = "/createAccount")
	public Account accountCreate(String mobileNumber, String name, String amount) {

		Account retrievedAccount = wso.createAccount(mobileNumber, name, new BigDecimal(amount));
		return retrievedAccount;
	}
		
	@RequestMapping(method = RequestMethod.POST, value ="/deposit")
	public Account depositAccount(String mobileNumber, String amount) throws InvalidRequestException, SQLException {
		 double amt=Double.parseDouble(amount);

		Account retrievedAccount = wso.deposit(mobileNumber,amt);
		return retrievedAccount;
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/withdraw")
	public Account withdrawAccount(String mobileNumber, String amount) throws InvalidRequestException, SQLException {
		 double amt=Double.parseDouble(amount);

		Account retrievedAccount = wso.withdraw(mobileNumber,amt);
		return retrievedAccount;
	}
		
	@RequestMapping(method = RequestMethod.POST, value ="/showBalance")
	public Account showBalanceAccount(String mobileNumber) throws InvalidRequestException, SQLException {
		 
		Account retrievedAccount = wso.showBalance(mobileNumber);
		return retrievedAccount;
	}
}
