package booking.travel.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.travel.entity.Account;
import booking.travel.entity.Role;
import booking.travel.service.AccountService;

@Controller
@RequestMapping("/admin/")
public class TrashAdminAccountController {

	@Autowired
	AccountService accountService;
	
	@ModelAttribute("listAllRoles")
	public List<Role> listRole(){
		return accountService.getAllRoles();
	}
	
	@GetMapping("list_account_trash")
	public String listTrashAccount(Model model) {
		
		List<Account> list = accountService.listAccoutTrash();
		model.addAttribute("listTrashAccount", list);
		
		return "admin/trash/_danhSachTrashAccount";
	}
	
	@GetMapping("detail_account_trash/{username}")
	public String detailTrashAccount(Model model, @PathVariable("username") String username) {
		
		Account account = accountService.findById(username);
		model.addAttribute("detailAccount", account);
		
		return "admin/trash/_detailTrashAccount";
	}
	
	@PostMapping("khoi_phuc_account")
	public String khoiPhucAccount(Account account) {
		
		account.setIsDeleted(false);
		accountService.saveAccount(account);
		
		return "redirect:/admin/list_account_trash";
	}
	
	@GetMapping("search_trash_account")
	public String searchTrashAccount(Model model,
			@RequestParam("keyword") String keyword) {
		
		List<Account> list = accountService.searchTrashAccount(keyword);
		model.addAttribute("listTrashAccount", list);
		
		return "admin/trash/_danhSachTrashAccount";
	}
}
