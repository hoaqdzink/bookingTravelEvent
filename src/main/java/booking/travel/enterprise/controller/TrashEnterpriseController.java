package booking.travel.enterprise.controller;

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

import booking.travel.entity.Event;
import booking.travel.entity.EventPost;
import booking.travel.entity.FromCity;
import booking.travel.entity.ToCity;
import booking.travel.entity.TourPost;
import booking.travel.entity.Tranport;
import booking.travel.entity.Type;
import booking.travel.service.EventPostService;
import booking.travel.service.EventService;
import booking.travel.service.FromCityService;
import booking.travel.service.ToCityService;
import booking.travel.service.TourPostService;
import booking.travel.service.TranportService;
import booking.travel.service.TypeService;

@Controller
@RequestMapping("/enterprise/")
public class TrashEnterpriseController {

	@Autowired
	EventService eventService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	TranportService tranportService;
	
	@Autowired
	FromCityService fromCityService;
	
	@Autowired
	ToCityService toCityService;
	
	@Autowired
	TourPostService tourPostService;
	
	@Autowired
	EventPostService eventPostService;
	
	//ĐỔ LOẠI EVENT LÊN COMBOBOX
	@ModelAttribute("eventList")
	public List<Event> listEvent(){
		return eventService.findAll();
	}
	
	// ĐỔ LOẠI TOUR LÊN COMBOBOX
	@ModelAttribute("typeList")
	public List<Type> listType() {
		return typeService.findAll();
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
	
	///ĐỔ DANH SÁCH TOUR ĐÃ XÓA
	@GetMapping("trash_list_tour")
	public String listTrashTour(Model model) {
		
		List<TourPost> list = tourPostService.listTrashTour();
		model.addAttribute("listTrashTour", list);
		
		return "enterprise/trash/_danhSachTourXoa";
	}
	
	///ĐỔ DANH SÁCH SỰ KIỆN ĐÃ XÓA
	@GetMapping("trash_list_event")
	public String listTrashEvent(Model model) {
		
		List<EventPost> list = eventPostService.listTrashEvent();
		model.addAttribute("listTrashEvent", list);
		
		return "enterprise/trash/_danhSachEventXoa";
	}
	
	///XEM CHI TIẾT TOUR ĐÃ XÓA
	@GetMapping("trash_detail_tour/{tourId}")
	public String detailTrashTour(Model model, @PathVariable("tourId") String tourId) {
		
		TourPost tourPost = tourPostService.findById(tourId);
		model.addAttribute("tourPost", tourPost);
		
		return "enterprise/trash/_detailTrashTour";
	}
	
	///XEM CHI TIẾT SỤ KIỆN ĐÃ XÓA
	@GetMapping("trash_detail_event/{eventPostId}")
	public String detailTrashEvent(Model model, @PathVariable("eventPostId") String eventPostId) {
		
		EventPost eventPost = eventPostService.findById(eventPostId);
		model.addAttribute("eventPost", eventPost);
		
		return "enterprise/trash/_detailTrashEvent";
	}
	
	@GetMapping("get_khoi_phuc_tour/{tourId}")
	public String getKhoiPhucTour(@PathVariable("tourId") String tourId) {
		
		TourPost tourPost = tourPostService.findById(tourId);
		
		return khoiPhucTour(tourPost);
	}
	
	///KHÔI PHỤC
	@PostMapping("khoi_phuc_tour")
	public String khoiPhucTour(TourPost tourPost) {

		String chua = "CHUA";
		tourPost.setStatus(chua);
		tourPost.setIsDeleted(false);
		tourPostService.saveIsDeleted(tourPost);
		
		return "redirect:/enterprise/trash_list_tour";
	}
	
	@GetMapping("get_khoi_phuc_event/{eventPostId}")
	public String getKhoiPhucEvent(@PathVariable("eventPostId") String eventPostId) {
		
		EventPost eventPost = eventPostService.findById(eventPostId);
		
		return khoiPhucEvent(eventPost);
	}
	
	///KHÔI PHỤC
	@PostMapping("khoi_phuc_event")
	public String khoiPhucEvent(EventPost eventPost) {

		String chua = "CHUA";
		eventPost.setStatus(chua);
		eventPost.setIsDeleted(false);
		eventPostService.saveIsDeleted(eventPost);
		
		return "redirect:/enterprise/trash_list_event";
	}
	
	@GetMapping("search_trash_tour")
	public String searchTrashTour(Model model,
			@RequestParam("keyword") String keyword) {
		
		List<TourPost> list = tourPostService.searchTrashTour(keyword);
		model.addAttribute("listTrashTour", list);
		
		return "enterprise/trash/_danhSachTourXoa";
	}
	
	@GetMapping("search_trash_event")
	public String searchTrashEvent(Model model,
			@RequestParam("keyword") String keyword) {
		
		List<EventPost> list = eventPostService.searchTrashEvent(keyword);
		model.addAttribute("listTrashEvent", list);
		
		return "enterprise/trash/_danhSachEventXoa";
	}
}
