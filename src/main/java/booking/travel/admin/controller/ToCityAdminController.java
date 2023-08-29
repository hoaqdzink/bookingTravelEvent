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

import booking.travel.entity.Region;
import booking.travel.entity.ToCity;
import booking.travel.service.RegionService;
import booking.travel.service.ToCityService;

@Controller
@RequestMapping("/admin/")
public class ToCityAdminController {

	@Autowired
	RegionService regionService;
	
	@Autowired
	ToCityService toCityService;
	
	/// ĐỔ VÙNG LÊN COMBOBOX
	@ModelAttribute("regionList")
	public List<Region> listRegion() {
		return regionService.findAll();
	}
	
	@RequestMapping("form_to_city")
	public String formToCity(Model model) {
		ToCity to = new ToCity();
		model.addAttribute("toCity", to);
		return "admin/toCity/_themMoiToCity";
	}
	
	@RequestMapping("list_to_city")
	public String listToCity(Model model) {
		
		List<ToCity> list = toCityService.findAll();
		model.addAttribute("toCityList", list);
		
		return "admin/toCity/_danhSachToCity";
	}
	
	@GetMapping("edit_to_city/{toCityId}")
	public String edit(Model model, @PathVariable("toCityId") String toCityId) {
		
		ToCity toCity = toCityService.findById(toCityId);
		model.addAttribute("toCity", toCity);
		
		return "admin/toCity/_themMoiToCity";
	}
	
	@PostMapping("save_to_city")
	public String save(Model model, ToCity toCity) {
		
		toCityService.save(toCity);
		
		return "redirect:/admin/list_to_city";
	}
	
	@GetMapping("delete_to_city/{toCityId}")
	public String delete(@PathVariable("toCityId") String toCityId) {
		
		toCityService.delete(toCityId);
		
		return "redirect:/admin/list_to_city";
	}
	
	@GetMapping("search_to_city")
	public String search(Model model, @RequestParam("keyword") String keyword) {
		
		if(keyword.isEmpty()) {
			return "redirect:/admin/list_to_city";
		}
		List<ToCity> search = toCityService.searchToCity(keyword);
		model.addAttribute("toCityList", search);
		
		return "admin/toCity/_danhSachToCity";
	}
}
