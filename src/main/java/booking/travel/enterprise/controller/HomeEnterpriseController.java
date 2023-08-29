package booking.travel.enterprise.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import booking.travel.service.AccountService;

@Controller
@RequestMapping("/enterprise/")
public class HomeEnterpriseController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("home")
	public String enterpriseHome(Model model, 
			HttpServletRequest request) {
		return "enterprise/about";
	}
	
}
