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

import booking.travel.entity.FromCity;
import booking.travel.entity.ToCity;
import booking.travel.entity.TourPost;
import booking.travel.entity.Tranport;
import booking.travel.entity.Type;
import booking.travel.service.FromCityService;
import booking.travel.service.ToCityService;
import booking.travel.service.TourPostService;
import booking.travel.service.TranportService;
import booking.travel.service.TypeService;
import booking.travel.utils.SendMailService;

@Controller
@RequestMapping("/admin/")
public class TourAdminController {

	@Autowired
	TourPostService tourPostService;
	
	@Autowired
	TranportService tranportService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	SendMailService sendMailService;
	
	@Autowired
	ToCityService toCityService;
	
	@Autowired
	FromCityService fromCityService;
	
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
	
	@RequestMapping("list_tour")
	public String listTour(Model model) {
		
		List<TourPost> list = tourPostService.findAllByStatusWait();
		model.addAttribute("listTourWait", list);
		
		List<TourPost> lists = tourPostService.findAllByStatusApprove();
		model.addAttribute("listTourApprove", lists);
		
		return "admin/tour/_danhSachTour";
	}
	
	////XEM CHI TIẾT CÁC TOUR ĐANG CHỜ
	@GetMapping("detail_tour_wait/{tourId}")
	public String detailTourWait(Model model, 
			@PathVariable("tourId") String tourId) {
		
		TourPost tour = tourPostService.findById(tourId);
		model.addAttribute("tour", tour);
		
		return "admin/tour/_detailTourWait";
	}
	
	///XEM CHI TIẾT CÁC TOUR ĐÃ DUYỆT HOẶC HỦY
	@GetMapping("detail_tour_approve/{tourId}")
	public String detailTourApprove(Model model, 
			@PathVariable("tourId") String tourId) {
		
		TourPost tour = tourPostService.findById(tourId);
		model.addAttribute("tour", tour);
		
		return "admin/tour/_detailTourApprove";
	}
	
	//////DUYỆT BÀI VIẾT TOUR
	@PostMapping("duyet_tour")
	public String duyetTour(TourPost tour, HttpServletRequest request) {
		
		String nduyet = request.getParameter("nutAdminDuyet");
		String email = request.getParameter("emails");
		String tenBai = tour.getName();
		String tourId = tour.getTourId();

		try {
			if(nduyet != null) {
				String duyet = "DUYET";
				tour.setStatus(duyet);
				tourPostService.saveAdminTour(tour);
				sendMailService.sendMailDuyetTour(email, tenBai, tourId);
			}
			else {
				String huy = "HUY";
				tour.setStatus(huy);
				tourPostService.saveAdminTour(tour);
				sendMailService.sendMailHuyTour(email, tenBai, tourId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			

		return "redirect:/admin/list_tour";
	}
	
	//HÀM TÌM KIẾM
	@GetMapping("search_all_tour")
	public String searchAllTour(Model model,
			@RequestParam("keyword") String keyword) {
		if(keyword.isEmpty()) {
			return "redirect:/admin/list_tour";
		}else {
			
			List<TourPost> list = tourPostService.SearchAllTour(keyword);
			model.addAttribute("listTourApprove", list);
		}

		return "admin/tour/_danhSachTour";
	}
}
