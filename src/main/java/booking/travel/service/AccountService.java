package booking.travel.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;

import booking.travel.entity.Account;
import booking.travel.entity.Role;

public interface AccountService {

	Account findByUsernameAndActive(String username);

	Account findByUsername(String username);

	Account findByEmail(String email);

	void registerEnterprise(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException;
	
	boolean verify(String verificationCode);

	void registerCustomer(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException;

	void updateResetPasswordToken(String email, String siteURL)throws AccountNotFoundException, UnsupportedEncodingException, MessagingException;

	Account getToken(String token);

	void updatePassword(Account account, String password);

	void saveAccount(Account account);

	Account saveLogoAccountWithCustomers(Account account);

	Account saveLogoAccountWithEnterprises(Account account);

	Account saveLogoAccountWithStaffs(Account account);

	List<Account> findByAdmin(String sortField, String sortDir);

	List<Account> findByPartner(String sortField, String sortDir);

	Account findById(String username);

	List<Role> getAllRoles();

	void deleteById(String username);

	List<Account> searchAccount(String keyword);

	List<Account> sortByName();

	void saveDelete(Account account);

	List<Account> listAccoutTrash();

	Account findByUsernameOrEmail(String remoteUser);

	String findByUsernameCurrent(String getUsername);

	String findByEmailCurrent(String getEmail);

	List<Account> searchTrashAccount(String keyword);

	Account saveLogoAccountWithDirector(Account account);

}
