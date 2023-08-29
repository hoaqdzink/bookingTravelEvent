package booking.travel.service.lmpl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import booking.travel.dao.AccountDAO;
import booking.travel.dao.RoleDAO;
import booking.travel.entity.Account;
import booking.travel.entity.Role;
import booking.travel.service.AccountService;
import booking.travel.utils.SendMailService;
import net.bytebuddy.utility.RandomString;

@Service
public class AccountServicelmpl implements AccountService{

	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	SendMailService sendMailService;
	
	//TÌM KIẾM TÀI KHOẢN ĐÃ ACTIVE
	@Override
	public Account findByUsernameAndActive(String username) {
		return accountDao.findByUsernameAndActive(username);
	}

	//TÌM TÀI KHOẢN THEO USERNAME
	@Override
	public Account findByUsername(String username) {
		return accountDao.findByUsername(username);
	}

	//TÌM TÀI KHOẢN THEO EMAIL
	@Override
	public Account findByEmail(String email) {
		return accountDao.findByEmail(email);
	}

	//////////--------------ĐĂNG KÝ TÀI KHOẢN DÀNH CHO DOANH NGHIỆP------------//////
	@Override
	public void registerEnterprise(Account account, String siteURL)
			throws UnsupportedEncodingException, MessagingException {
		
		String randomCode = RandomString.make(64);
		account.setVerificationCode(randomCode);
		account.setEnabled(false);
		
		Role roleAccount = roleDao.findById("ENTE").get();
		account.addRole(roleAccount);
		
		accountDao.save(account);
		
		sendMailService.sendVerificationEmailEnterprise(account, siteURL);
	}	
	
	//////////--------------ĐĂNG KÝ TÀI KHOẢN DÀNH CHO KHÁCH HÀNG------------//////
	@Override
	public void registerCustomer(Account account, String siteURL)
			throws UnsupportedEncodingException, MessagingException {
		
		String randomCode = RandomString.make(64);
		account.setVerificationCode(randomCode);
		account.setEnabled(false);
		
		Role roleAccount = roleDao.findById("CUST").get();
		account.addRole(roleAccount);
		
		accountDao.save(account);
		
		sendMailService.sendVerificationEmailCustomer(account, siteURL);
	}
	
	//TIẾN HÀNH KIỂM TRA TÀI KHOẢN ĐÃ ĐĂNG KÍ CÓ MÃ XÁC NHẬN VÀ TIẾN HÀNH ACTIVE TÀI KHOẢN
	@Override
	public boolean verify(String verificationCode) {
		Account account = accountDao.findByVerificationCode(verificationCode);
		
		if(account == null || account.isEnabled()) {
			return false;
		}else {
			account.setVerificationCode(null);
			account.setEnabled(true);
			accountDao.save(account);
			
			return true;
		}
	}

	////LẤY TOKEN VỪA TẠO VÀ LƯU VỀ LẠI DB
	@Override
	public void updateResetPasswordToken(String email, String siteURL) throws AccountNotFoundException, UnsupportedEncodingException, MessagingException {
		Account account = accountDao.findByEmail(email);
		String token = RandomString.make(45);
		
		if(account != null) {
			account.setResetPasswordToken(token);
			accountDao.save(account);
			sendMailService.sendMailForgotPassword(account, siteURL);
			
		}else {
			throw new AccountNotFoundException("Không tìm thấy tài khoản cho email: "+email);
		}
		
	}

	////DÒ TÌM LẤY MÃ TOKEN TRONG DB ACCOUNT
	@Override
	public Account getToken(String resetPasswordToken) {
		return accountDao.findByResetPasswordToken(resetPasswordToken);
	}

	///TIẾN HÀNH CHO TOKEN VỀ NULL VÀ SET MẬT KHẨU MỚI
	@Override
	public void updatePassword(Account account, String newPassword) {
		account.setPassword(newPassword);
		account.setResetPasswordToken(null);
		
		accountDao.save(account);
		
	}

	////HÀM LƯU TÀI KHOẢN
	@Override
	public void saveAccount(Account account) {
		account.setEnabled(true);
		accountDao.save(account);
	}
	
	
	///LƯU TÀI KHOẢN VỚI QUYỀN ENTERPRISE
	@Override
	public Account saveLogoAccountWithEnterprises(Account account) {
		Role roleAccount = roleDao.findById("ENTE").get();
		account.addRole(roleAccount);
		account.setEnabled(true);
		return accountDao.save(account);
	}

	///LƯU TÀI KHOẢN VỚI QUYỀN CUSTOMER
	@Override
	public Account saveLogoAccountWithCustomers(Account account) {
		Role roleAccount = roleDao.findById("CUST").get();
		account.addRole(roleAccount);
		account.setEnabled(true);
		return accountDao.save(account);
	}
	
	///LƯU TÀI KHOẢN VỚI QUYỀN STAFFS
	@Override
	public Account saveLogoAccountWithStaffs(Account account) {
		Role roleAccount = roleDao.findById("STAF").get();
		account.addRole(roleAccount);
		account.setEnabled(true);
		return accountDao.save(account);
	}
	
	///LƯU TÀI KHOẢN VỚI QUYỀN DIRECTOR
	@Override
	public Account saveLogoAccountWithDirector(Account account) {
		Role roleAccount = roleDao.findById("DIRE").get();
		account.addRole(roleAccount);
		account.setEnabled(true);
		return accountDao.save(account);
	}

	///ĐỔ TÀI KHOẢN CÓ QUYỀN ADMIN
	@Override
	public List<Account> findByAdmin(String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		return accountDao.findByAdmin(sort);
	}

	///ĐỔ TÀI KHOẢN CÓ QUYỀN ĐỐI TÁC
	@Override
	public List<Account> findByPartner(String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		return accountDao.findByPartner(sort);
	}

	@Override
	public Account findById(String username) {
		// TODO Auto-generated method stub
		return accountDao.findById(username).get();
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	@Override
	public void deleteById(String username) {
		accountDao.deleteById(username);
		
	}

	@Override
	public List<Account> searchAccount(String keyword) {
		return accountDao.searchAccount(keyword);
	}

	@Override
	public List<Account> sortByName() {
		return accountDao.sortByName();
	}

	@Override
	public void saveDelete(Account account) {
		accountDao.save(account);
		
	}

	@Override
	public List<Account> listAccoutTrash() {
		return accountDao.listAccountTrash();
	}

	@Override
	public Account findByUsernameOrEmail(String remoteUser) {
		// TODO Auto-generated method stub
		return accountDao.findByUsernameOrEmail(remoteUser);
	}

	@Override
	public String findByEmailCurrent(String currentEmail) {
		return accountDao.findbyEmailCurrent(currentEmail);
	}

	@Override
	public String findByUsernameCurrent(String currentUsername) {
		return accountDao.findByUsernameCurrent(currentUsername);
	}

	@Override
	public List<Account> searchTrashAccount(String keyword) {
		return accountDao.searchTrashAccount(keyword);
	}

}
