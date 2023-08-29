package booking.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SecurityController {
	
	//VÀO TRANG LOGIN
	@RequestMapping("/login")
	public String login() {
		return "security/Login";
	}
	
	//SAU KHI ĐĂNG NHẬP THÀNH CÔNG
	@RequestMapping("/security/login/success")
	public String loginSuccessUrl(Model model) {
		model.addAttribute("message","Đăng nhập thành công");
		return "redirect:/";
	}
	
	//SAU KHI ĐĂNG NHẬP LỖI
	@RequestMapping("/security/login/error")
	public String loginFailureUrl(Model model) {
		model.addAttribute("error","tên đăng nhập hoặc mật khẩu không chính xác");
		return "security/Login";
	}
	
	//TRUY CẬP VÀO MÀ KHÔNG CÓ QUYỀN
	@RequestMapping("/security/unauthoried")
	public String accessDeniedPage(Model model) {
		model.addAttribute("error","Bạn không có quyền truy cập!");
		return "security/login";
	}
	
	//ĐĂNG XUẤT
	@RequestMapping("/security/logoff")
	public String logout(Model model) {
		return "/";
	}
	
	//SAU KHI ĐĂNG XUẤT THÀNH CÔNG
	@RequestMapping("/security/logoff/success")
	public String logoutSuccess(Model model) {
		return "security/login";
	}
	

}
