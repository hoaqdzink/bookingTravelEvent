package booking.travel.enterprise.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import booking.travel.entity.Account;
import booking.travel.entity.Event;
import booking.travel.entity.EventPost;
import booking.travel.entity.FromCity;
import booking.travel.entity.Region;
import booking.travel.entity.ToCity;
import booking.travel.entity.Tranport;
import booking.travel.service.EventPostService;
import booking.travel.service.EventService;
import booking.travel.service.FromCityService;
import booking.travel.service.RegionService;
import booking.travel.service.ToCityService;
import booking.travel.service.TranportService;
import booking.travel.utils.FileService;

@Controller
@RequestMapping("/enterprise/")
public class EventEnterpriseController {

	@Autowired
	FileService fileService;

	@Autowired
	RegionService regionService;

	@Autowired
	EventService eventService;

	@Autowired
	TranportService tranportService;

	@Autowired
	FromCityService fromCityService;

	@Autowired
	EventPostService eventPostService;

	@Autowired
	ToCityService toCityService;
	
	@RequestMapping("event_form")
	public String enterpriseEventForm(Model model) {
		EventPost eventPost = new EventPost();
		model.addAttribute("event", eventPost);
		return "enterprise/event/_themEvent";
	}
	
	//ĐỔ LOẠI EVENT LÊN COMBOBOX
	@ModelAttribute("eventList")
	public List<Event> listEvent(){
		return eventService.findAll();
	}
	
	// ĐỔ PHƯƠNG TIỆN LÊN COMBOBOX
	@ModelAttribute("tranportList")
	public List<Tranport> listTranport() {
		return tranportService.findAll();
	}

	// ĐỔ ĐIỂM KHỞI HÀNH LÊN COMBOBOX
	@ModelAttribute("fromCityList")
	public List<FromCity> listFromCity() {
		return fromCityService.findAll();
	}

	/// ĐỔ VÙNG LÊN COMBOBOX
	@ModelAttribute("regionList")
	public List<Region> listRegion() {
		return regionService.findAll();
	}

	/// LẤY ĐIỂM ĐẾN THEO VÙNG
	@ResponseBody
	@RequestMapping("api_event/toCity/{regionId}")
	public List<ToCity> toCity(@PathVariable("regionId") String regionId) {
		return toCityService.findAllByRegionId(regionId);
	}
	
	//ĐỔ DANH SÁCH CÁC EVENT THEO USERNAME
	@RequestMapping("event_list")
	public String enterpriseEventList(Model model, HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		List<EventPost> list = eventPostService.findAllEventByUsername(username);
		model.addAttribute("listEventPost", list);
		
		return "enterprise/event/_baiDangEvent";
	}
	
	/// HÀM LƯU EVENT
	@PostMapping("save_eventPost")
	public String saveEvent(EventPost event,
			@RequestParam("mainImage") MultipartFile primaryMultipartFile,
			@RequestParam("extraImage") MultipartFile[] extraMultipartFiles, HttpServletRequest request)
			throws IOException {
		
			if(event.getToCity().getToCityId().isEmpty()) {
				return null;
			}
			event.setToCity(new ToCity(event.getToCity().getToCityId().substring(7)));
			
			EventPost saveEvent = eventPostService.save(event);
			String folder = "event/" + saveEvent.getFolder();

			String fileName = fileService.saveFile(folder, primaryMultipartFile);
			if (fileName != null) {
				saveEvent.setPrimaryImage(fileName);
			}

			int count = 0;
			for (MultipartFile extraMultipart : extraMultipartFiles) {
				String extraImageName = fileService.saveFile(folder, extraMultipart);
				if (extraImageName != null) {
					if (count == 0)
						saveEvent.setExtraImage1(extraImageName);
					if (count == 1)
						saveEvent.setExtraImage2(extraImageName);
					if (count == 2)
						saveEvent.setExtraImage3(extraImageName);
					if (count == 3)
						saveEvent.setExtraImage4(extraImageName);
					if (count == 4)
						saveEvent.setExtraImage5(extraImageName);
					if (count == 5)
						saveEvent.setExtraImage6(extraImageName);
				}
				count++;
			}
			saveEvent.setAccount(new Account(request.getRemoteUser()));

			eventPostService.save(saveEvent);

			return "redirect:/enterprise/event_list";
	}
	
	/// CHỈNH SỬA EVENT
	@GetMapping("event_edit/{eventPostId}")
	public String editEvent(Model model, @PathVariable("eventPostId") String id) {

		EventPost eventPost = eventPostService.findById(id);
		model.addAttribute("event", eventPost);

		return "enterprise/event/_themEvent";
	}

	// LẤY ID EVENT CẦN XÓA
	@GetMapping("event_delete/{eventPostId}")
	public String getEventCanDelete(@PathVariable("eventPostId") String id) {
		
		EventPost eventPost = eventPostService.findById(id);
		
		return deleteEvent(eventPost);
	}
	
	// XÓA EVENT
	@PostMapping("event_save_delete")
	public String deleteEvent(EventPost eventPost) {
		
		eventPost.setIsDeleted(true);
		eventPostService.saveIsDeleted(eventPost);
		
		return "redirect:/enterprise/event_list";
	}
	
	///// HÀM TÌM KIẾM THEO USERNAME
	@GetMapping("search_event_post")
	public String searchPost(Model model, HttpServletRequest request, @RequestParam("keyword") String keyword) {

		String username = request.getRemoteUser();
		List<EventPost> list = eventPostService.searchEventByUsername(username, keyword);
		model.addAttribute("listEventPost", list);

		return "enterprise/event/_baiDangEvent";
	}
}
