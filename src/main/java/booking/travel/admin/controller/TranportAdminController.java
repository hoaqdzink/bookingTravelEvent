package booking.travel.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import booking.travel.entity.Tranport;
import booking.travel.service.TranportService;

@Controller
@RequestMapping("/admin/")
public class TranportAdminController {

	@Autowired
	TranportService tranportService;
	
	@GetMapping("form_tranport")
	public String newForm(Model model) {
		
		Tranport item = new Tranport();
		model.addAttribute("tranport", item);
		
		return "admin/tranport/_themMoiTranport";
	}
	
	@GetMapping("list_tranport")
	public String listTranport(Model model) {
		
		List<Tranport> list = tranportService.findAll();
		model.addAttribute("tranportList", list);
		
		return "admin/tranport/_danhSachTranport";
	}
	
	@PostMapping("save_tranport")
	public String saveTranport(Tranport tranport) {
		
		tranportService.saveTranport(tranport);
		
		return "redirect:/admin/list_tranport";
	}
	
	@GetMapping("edit_tranport/{tranportId}")
	public String editTranport(Model model, @PathVariable("tranportId") String tranportId) {
		
		Tranport tranport = tranportService.findById(tranportId);
		model.addAttribute("tranport", tranport);
		
		return "admin/tranport/_themMoiTranport";
	}
}
