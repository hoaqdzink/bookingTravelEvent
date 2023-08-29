package booking.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.travel.entity.Account;
import booking.travel.service.AccountService;
import booking.travel.utils.Ultility;

@Controller
public class SecurityRegisterController {

	@Autowired
	AccountService accountService;

	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "security/register";
	}

//////////--------------ĐĂNG KÝ TÀI KHOẢN DÀNH CHO DOANH NGHIỆP------------//////
	@PostMapping("/security/register_enterprise")
	public String registerEnterprise(Model model, Account account, HttpServletRequest request) {

		String getUsername = account.getUsername();
		String getEmail = account.getEmail();

		try {
			Account currentUsername = accountService.findByUsername(getUsername);
			Account currentEmail = accountService.findByEmail(getEmail);

			if (currentUsername != null) {
				model.addAttribute("error1Enterprise", "Tên " + getUsername + " đã có người sử dụng");
				
			} else if (currentEmail != null) {
				model.addAttribute("error2Enterprise", "Email " + getEmail + " đã có người sử dụng");

			}else {
				accountService.registerEnterprise(account, Ultility.getSiteURL(request));
				model.addAttribute("messageEnterprise", "Đăng ký thành công, kiểm tra email để kích hoạt");
				model.addAttribute("account", new Account());
			}
			


		} catch (Exception e) {
			System.out.println(e);
		}

		return "security/register";
	}

//////////--------------ĐĂNG KÝ TÀI KHOẢN DÀNH CHO KHÁCH HÀNG------------//////
	@PostMapping("/security/register_customer")
	public String registerCustomer(Model model, Account account, HttpServletRequest request) {

		String getUsername = account.getUsername();
		String getEmail = account.getEmail();

		try {
			Account currentUsername = accountService.findByUsername(getUsername);
			Account currentEmail = accountService.findByEmail(getEmail);

			if (currentUsername != null) {
				model.addAttribute("error1Customer", "Tên " + getUsername + " đã có người sử dụng");

			} else if (currentEmail != null) {
				model.addAttribute("error2Customer", "Email " + getEmail + " đã có người sử dụng");

			} else {
				accountService.registerCustomer(account, Ultility.getSiteURL(request));
				model.addAttribute("messageCustomer", "Đăng ký thành công, kiểm tra email để kích hoạt");
				model.addAttribute("account", new Account());
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return "security/register";
	}

	//// XÁC NHẬN THÔNG TIN TÀI KHOẢN
	@GetMapping("/verify") /// mapping từ thanh địa chỉ
	public String verifyAccount(Model model, @RequestParam("code") String code) {
		if (accountService.verify(code)) {
			model.addAttribute("message", "Kích hoạt tài khoản thành công");
			return "security/Login";
		} else {
			model.addAttribute("error", "Kích hoạt tài khoản thất bại");
			return "security/Login";
		}
	}
}
