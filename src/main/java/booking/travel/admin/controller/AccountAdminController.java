package booking.travel.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import booking.travel.entity.Account;
import booking.travel.entity.Role;
import booking.travel.service.AccountService;
import booking.travel.utils.FileService;

@Controller
@RequestMapping("/admin/")
public class AccountAdminController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	FileService fileService;
	
	@ModelAttribute("listAllRoles")
	public List<Role> listRole(){
		return accountService.getAllRoles();
	}
	
	@RequestMapping("account_form")
	public String adminAccountForm(Model model) {
		
		Account account = new Account();
		model.addAttribute("account", account);
		
		return "admin/account/_themMoiAccount";
	}
	
	@RequestMapping("account_list")
	public String viewPage(Model model) {
		return accountList(model, "username", "asc");
	}
	
	@RequestMapping("list")
	public String accountList(Model model,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir) {
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		List<Account> list = accountService.findByAdmin(sortField, sortDir);
		model.addAttribute("listAdmin", list);
		
		List<Account> lists = accountService.findByPartner(sortField, sortDir);
		model.addAttribute("listPartner", lists);
		
		return "admin/account/_danhSachAccount";
	}
	
	@GetMapping("account_edit/{username}")
	public String editAccount(Model model,
			@PathVariable("username") String username) {
		
		Account account = accountService.findById(username);	
		model.addAttribute("account", account);
		
		return "admin/account/_themMoiAccount";
	}
	
	///XEM CHI TIẾT TÀI KHOẢN
	@GetMapping("account_detail/{username}")
	public String detailAccount(Model model,
			@PathVariable("username") String username) {
		
		Account account = accountService.findById(username);		
		model.addAttribute("account", account);
		
		return "admin/account/_detailAccount";
	}
	
	@PostMapping("account_save")
	public String saveAdminNew(Model model, Account account,
			@RequestParam("imageFile") MultipartFile multipartFile,
			HttpServletRequest request) throws IOException{
		
			String folder = "account/" + account.getFolder();
			
			String fileName = fileService.saveFile(folder, multipartFile);

			if(fileName != null) {
				
				account.setImage(fileName);
			}
			accountService.saveLogoAccountWithStaffs(account);
			return "redirect:/admin/account_list";
	}
	
	@GetMapping("account_search")
	public String adminSearchAccount(Model model, @RequestParam("keyword") String keyword) {
		
		if(keyword.isEmpty()) {
			return "redirect:/admin/account_list";
		}
		List<Account> list = accountService.searchAccount(keyword);
		model.addAttribute("listPartner", list);
		return "admin/account/_danhSachAccount";
	}
	
	////XÓA TÀI KHOẢN NHÂN VIÊN
	@GetMapping("account_delete/{username}")
	public String adminDeleteAccount(Model model, @PathVariable("username") String username) {
		
		Account account = accountService.findById(username);
		model.addAttribute("account", account);
		
		return adminSaveDeleteAccount(account);
	}
	
	@PostMapping("account_delete")
	public String adminSaveDeleteAccount(Account account) {
		
		account.setIsDeleted(true);
		accountService.saveDelete(account);
		
		return "redirect:/admin/account_list";
	}
	
}
