package booking.travel.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.travel.entity.Account;
import booking.travel.service.AccountService;
import booking.travel.utils.SendMailService;
import booking.travel.utils.Ultility;

@Controller
public class SecurityForgotPasswordController {

	@Autowired
	AccountService accountService;

	@Autowired
	SendMailService sendMailService;

	// VÀO FORM NHẬP EMAIL ĐỂ LẤY LẠI MẬT KHẨU
	@GetMapping("/security_forgot_password")
	public String formForgotPassword(Model model) {
		return "security/login";
	}

	// TIẾN HÀNH CHẠY QUÁ TRÌNH LẤY LẠI MẬT KHẨU
	@PostMapping("/security_forgot_password")
	public String processForgotPassword(Model model, HttpServletRequest request){

		String email = request.getParameter("email");

		try {
			accountService.updateResetPasswordToken(email, Ultility.getSiteURL(request));
			model.addAttribute("messageForogotSuccess", "Gửi email thành công, vui lòng kiểm tra hòm thư");

		} catch (AccountNotFoundException e) {
			model.addAttribute("errorSendMailAccount", "Không tìm thấy tài khoản cho email "+email);
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("errorSendMail", "Lỗi khi gửi email");
		}

		return "security/login";
	}

	// KIỂM TRA XEM MÃ TOKEN ĐÃ TỒN TẠI CHƯA
	@GetMapping("/reset_password")///mapping theo đường dẫn trên thành địa chỉ
	public String showResetPasswordForm(Model model, Account account, @RequestParam("token") String token) {

		account = accountService.getToken(token);///TÌM TOKEN CÓ TRONG ĐỊA CHỈ WEB
		if (account == null) {
			model.addAttribute("errorToken", "Không có mã token");
			return "security/_xacnhanquenmatkhau";
			
		}
		model.addAttribute("token", token);//KHÔNG NULL THÌ TRẢ VỀ MODEL CÓ CHỨA TOKEN
		return "security/_xacnhanquenmatkhau";
	}

	// THAY ĐỔI MẬT KHẨU SAU KHI ĐÃ KIỂM TRA MÃ TOKEN THÀNH CÔNG
	@PostMapping("/reset_password")
	public String processResetPassword(Model model, Account account, HttpServletRequest request) {

		String token = request.getParameter("token");///LẤY TOKEN TỪ MODEL BÊN TRÊN
		String password = request.getParameter("newPassword");

		account = accountService.getToken(token);
		if (account == null) {
			model.addAttribute("errorToken", "Không có mã token");
			return "security/_xacnhanquenmatkhau";

		}else{
			accountService.updatePassword(account, password);
			model.addAttribute("messageChangeSuccess","Đổi mật khẩu thành công");
		}

		return "security/login";
	}

}
