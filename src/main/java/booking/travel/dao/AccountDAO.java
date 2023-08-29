package booking.travel.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{

	//TÌM USERNAME ĐÃ ĐƯỢC KÍCH HOẠT
	@Query("SELECT ac FROM Account ac "
			+ "WHERE ac.enabled = true AND ac.isDeleted = false AND (ac.username = ?1 OR ac.email = ?1)")
	Account findByUsernameAndActive(String username);

	//TÌM THEO EMAIL
	@Query("SELECT ac FROM Account ac "
			+ "WHERE ac.email = ?1")
	public Account findByEmail(String email);
	
	//TÌM MÃ XÁC NHẬN TÀI KHOẢN
	@Query("SELECT ac FROM Account ac "
			+ "WHERE ac.verificationCode = ?1")
	public Account findByVerificationCode(String code);

	//TÌM TÀI KHOẢN THEO USERNAME
	@Query("SELECT ac FROM Account ac "
			+ "WHERE ac.username=?1")
	Account findByUsername(String username);
	
	//TÌM MÃ RESET PASSWORD
	public Account findByResetPasswordToken(String token);
	
	@Query("SELECT ac FROM Account ac WHERE ac.email = ?1")
	String findbyEmailCurrent(String currentEmail);

	@Query("SELECT ac FROM Account ac WHERE ac.username = ?1")
	String findByUsernameCurrent(String currentUsername);
	
	////////--------DÀNH CHO ADMIN--------------------//////
	//ĐỔ CÁC TÀI KHOẢN CÓ QUYỀN STAFF HOẶC DIRECTOR
	@Query("SELECT DISTINCT ac FROM Account ac JOIN ac.roles r "
			+ "WHERE ac.isDeleted = false AND r.roleId = 'STAF' OR r.roleId = 'DIRE'")
	public List<Account> findByAdmin(Sort sort);
	
	//ĐỔ CÁC TÀI KHOẢN CÓ QUYỀN CUSTOMER HOẶC ENTERPRISE
	@Query("SELECT DISTINCT ac FROM Account ac JOIN ac.roles r "
			+ "WHERE ac.isDeleted = false AND r.roleId = 'CUST' OR r.roleId = 'ENTE'")
	public List<Account> findByPartner(Sort sort);

	@Query("SELECT ac FROM Account ac "
			+ "WHERE ac.isDeleted = false AND CONCAT(ac.username, ac.fullname, ac.email, ac.phone) LIKE %?1%")
	List<Account> searchAccount(String keyword);
	
	@Query("SELECT DISTINCT ac FROM Account ac JOIN ac.roles r "
			+ "WHERE r.roleId = 'CUST' OR r.roleId = 'ENTE' "
			+ "ORDER BY ac.username")
	List<Account> sortByName();

	@Query("SELECT DISTINCT ac FROM Account ac WHERE ac.isDeleted = true")
	List<Account> listAccountTrash();
	
	@Query("SELECT ac FROM Account ac "
			+ "WHERE ac.username=?1 OR ac.email = ?1")
	Account findByUsernameOrEmail(String remoteUser);

	@Query("SELECT ac FROM Account ac "
			+ "WHERE ac.isDeleted = true "
			+ "AND CONCAT(ac.username, ac.fullname, ac.email, ac.phone) "
			+ "LIKE %?1%")
	List<Account> searchTrashAccount(String keyword);

}
