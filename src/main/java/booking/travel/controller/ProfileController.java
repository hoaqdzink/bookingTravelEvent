package booking.travel.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import booking.travel.entity.Account;
import booking.travel.service.AccountService;
import booking.travel.utils.FileService;

@Controller
@RequestMapping("/profile/")
public class ProfileController {

	@Autowired
	FileService fileService;
	
	@Autowired
	AccountService accountService;
	
	///////THÔNG TIN DÀNH CHO CÁ NHÂN (KHÁCH HÀNG, NHÂN VIÊN)
	@RequestMapping("profile_information/{username}")
	public String profileInformation(Model model, HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		model.addAttribute("user", accountService.findByUsernameAndActive(username));
		
		return "Profile/_Information";
	}
	
	///////THÔNG TIN DÀNH CHO DOANH NGHIỆP
	@RequestMapping("profile_information_enterprise/{username}")
	public String profileInformationEnterprise(Model model,
			HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		model.addAttribute("user", accountService.findByUsernameAndActive(username));
		
		return "Profile/_InformationEnterprise";
	}
	
	///HÀM LƯU THÔNG TIN TÀI KHOẢN
	@PostMapping("save_profile")
	public String saveProfile(Model model, @ModelAttribute("user") Account account,
			@RequestParam("imageFile") MultipartFile multipartFile,
			HttpServletRequest request) throws IOException{
		
		String folder = "account/" + account.getFolder();
		String fileName = fileService.saveFile(folder, multipartFile);
		
		if(fileName != null) {
			
			account.setImage(fileName);
		}
		try {
			if(request.isUserInRole("CUST")) {
				accountService.saveLogoAccountWithCustomers(account);
				return "redirect:/profile/profile_information/"+request.getRemoteUser();
			}else if(request.isUserInRole("STAF")){
				accountService.saveLogoAccountWithStaffs(account);
				return "redirect:/profile/profile_information/"+request.getRemoteUser();
			}else if(request.isUserInRole("DIRE")) {
				accountService.saveLogoAccountWithDirector(account);
				return "redirect:/profile/profile_information/"+request.getRemoteUser();
			}
			else if(request.isUserInRole("ENTE")) {
				accountService.saveLogoAccountWithEnterprises(account);
				return "redirect:/profile/profile_information_enterprise/"+request.getRemoteUser();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "successProfile";
	}

	
	@RequestMapping("profile_contact")
	public String profileContact() {
		return "Profile/_help";
	}
	
	@RequestMapping("profile_contact_enterprise")
	public String profileContactEnterprise() {
		return "Profile/_helpEnterprise";
	}
	
}
