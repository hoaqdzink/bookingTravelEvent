package booking.travel.admin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import booking.travel.entity.Type;
import booking.travel.service.TypeService;
import booking.travel.utils.FileService;

@Controller
@RequestMapping("/admin/")
public class TypeAdminController {

	@Autowired
	TypeService typeService;
	
	@Autowired
	FileService fileService;
	
	@GetMapping("form_type")
	public String formType(Model model) {
		Type type = new Type();
		model.addAttribute("type", type);
		return "admin/type/_themMoiType";
	}
	
	@GetMapping("list_type")
	public String listType(Model model) {
		List<Type> list = typeService.findAll();
		model.addAttribute("typeList", list);
		return "admin/type/_danhSachType";
	}
	
	@PostMapping("save_type")
	public String saveType(Type type,
			@RequestParam("imageFile") MultipartFile multipartFile) throws IOException{
		
		Type type1 = typeService.save(type);
		String folder = "type/"+type1.getFolder();
		String fileName = fileService.saveFile(folder, multipartFile);
		if(fileName != null) {
			
			type1.setLogo(fileName);
		}
		typeService.saveType(type1);
		
		return "redirect:/admin/list_type";
	}
	
	@GetMapping("edit_type/{typeId}")
	public String editType(Model model, @PathVariable("typeId") String typeId) {
		
		Type type = typeService.findById(typeId);
		model.addAttribute("type", type);
		
		return "admin/type/_themMoiType";
	}
}
