package DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import POJO.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findOneByMobile(String mobileNumber);
	
	

}