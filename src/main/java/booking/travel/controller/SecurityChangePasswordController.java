package booking.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.travel.entity.Account;
import booking.travel.service.AccountService;

@Controller
public class SecurityChangePasswordController {

	@Autowired
	AccountService accountService;
	
	//VÀO FORM THAY ĐỔI MẬT KHẨU
	@GetMapping("/profile/profile_change_password")
	public String formChangePassword() {
		return "Profile/_ChangePass";
	}
	
	////LƯU THAY ĐỔI MẬT KHẨU
	@PostMapping("/profile_change_password")
	public String saveChangePassword(Model model,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		Account currentUser = accountService.findByUsername(username);
		
		if(!oldPassword.equals(currentUser.getPassword())) {
			model.addAttribute("error1Pass","Mật khẩu hiện tại không đúng");
			
		}else {
			currentUser.setPassword(newPassword);
			accountService.saveAccount(currentUser);
			model.addAttribute("message","Thay đổi mật khẩu thành công");
			
		}
		return "Profile/_ChangePass";
	}
}
