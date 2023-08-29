package booking.travel.admin.controller;

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

import booking.travel.entity.Event;
import booking.travel.entity.EventPost;
import booking.travel.entity.FromCity;
import booking.travel.entity.ToCity;
import booking.travel.entity.Tranport;
import booking.travel.service.EventPostService;
import booking.travel.service.EventService;
import booking.travel.service.FromCityService;
import booking.travel.service.ToCityService;
import booking.travel.service.TranportService;
import booking.travel.utils.SendMailService;

@Controller
@RequestMapping("/admin/")
public class EventAdminController {

	@Autowired
	EventPostService eventPostService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	TranportService tranportService;
	
	@Autowired
	SendMailService sendMailService;
	
	@Autowired
	ToCityService toCityService;
	
	@Autowired
	FromCityService fromCityService;
	
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
	
	//ĐỔ ĐIỂM KHỞI HÀNH LÊN COMBOBOX
	@ModelAttribute("fromCityList")
	public List<FromCity> listFromCity(){
		return fromCityService.findAll();
	}
	
	//ĐỔ ĐIỂM ĐIỂM ĐẾN LÊN COMBOBOX
	@ModelAttribute("toCityList")
	public List<ToCity> listToCity(){
		return toCityService.findAll();
	}
	
	@RequestMapping("list_event")
	public String listEvent(Model model) {
		
		List<EventPost> list = eventPostService.findAllByStatusWait();
		model.addAttribute("listEventWait", list);
		
		List<EventPost> lists = eventPostService.findAllByStatusApprove();
		model.addAttribute("listEventApprove", lists);
		
		return "admin/event/_danhSachEvent";
	}
	
	@GetMapping("detail_event_wait/{eventPostId}")
	public String detailEventWait(Model model,
			@PathVariable("eventPostId") String eventPostId) {
		
		EventPost event = eventPostService.findById(eventPostId);
		model.addAttribute("event", event);
		
		return "admin/event/_detailEventWait";
	}
	
	@GetMapping("detail_event_approve/{eventPostId}")
	public String detailEventApprove(Model model,
			@PathVariable("eventPostId") String eventPostId) {
		
		EventPost event = eventPostService.findById(eventPostId);
		model.addAttribute("event", event);
		
		return "admin/event/_detailEventApprove";
	}
	
	//////DUYỆT BÀI VIẾT EVENT
	@PostMapping("duyet_event")
	public String duyetEvent(EventPost event, HttpServletRequest request) {
		
		String nduyet = request.getParameter("nutAdminDuyet");
		String email = request.getParameter("emails");
		String tenBai = event.getName();
		String eventPostId = event.getEventPostId();

		try {
			if(nduyet != null) {
				String duyet = "DUYET";
				event.setStatus(duyet);
				eventPostService.saveAdminEvent(event);
				sendMailService.sendMailDuyetEvent(email, tenBai, eventPostId);
			}
			else {
				String huy = "HUY";
				event.setStatus(huy);
				eventPostService.saveAdminEvent(event);
				sendMailService.sendMailHuyEvent(email, tenBai, eventPostId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			

		return "redirect:/admin/list_event";
	}
	
	//HÀM TÌM KIẾM
	@GetMapping("search_all_event")
	public String searchAllEvent(Model model,
			@RequestParam("keyword") String keyword) {
		if(keyword.isEmpty()) {
			return "redirect:/admin/list_event";
		}else {
			
			List<EventPost> list = eventPostService.SearchAllEvent(keyword);
			model.addAttribute("listEventApprove", list);
		}

		return "admin/event/_danhSachEvent";
	}
	
	/////MẤY CÁI DÒNG NHO NHỎ
	@GetMapping("form_type_event")
	public String formEvent(Model model) {
		Event event = new Event();
		model.addAttribute("typeEvent", event);
		
		return "admin/typeEvent/_themMoiTypeEvent";
	}
	
	@GetMapping("list_type_event")
	public String listTypeEvent(Model model) {
		List<Event> list = eventService.findAll();
		model.addAttribute("typeEventList", list);
		return "admin/typeEvent/_danhSachTypeEvent";
	}
	
	@PostMapping("save_type_event")
	public String saveTypeEvent(Event event) {
		eventService.save(event);
		return "redirect:/admin/list_type_event";
	}
	
	@GetMapping("edit_type_event/{eventId}")
	public String editTypeEvent(Model model, @PathVariable("eventId") String eventId) {
		Event event = eventService.findById(eventId);
		model.addAttribute("typeEvent", event);
		return "admin/typeEvent/_themMoiTypeEvent";
	}
}
