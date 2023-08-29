package booking.travel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.travel.entity.RatingTour;
import booking.travel.entity.TourPost;
import booking.travel.service.RatingTourService;
import booking.travel.service.TourPostService;

@Controller
public class TourController {
	
	@Autowired
	TourPostService tourPostService;
	
	@Autowired
	private RatingTourService ratingTourService;
	

	//ĐỔ TẤT CẢ CÁC TOUR
	@RequestMapping("/tour/list")//GetMapping//PostMapping-> lưu và cập nhật
	public String list(Model model,
			@RequestParam("rid") Optional<String> rid,
			@RequestParam("tid") Optional<String> tid,
			@RequestParam("trid") Optional<String> trid
			) {
		
		if(rid.isPresent()) {//NẾU ID CỦA REGION VÙNG CỦA TOUR TỒN TẠI
			List<TourPost> list = tourPostService.findByRegion(rid.get());
			model.addAttribute("listAllTour", list);
			
		}else if(tid.isPresent()) {//NẾU ID CỦA TYPE LOẠI TOUR TỒN TẠI
			List<TourPost> list = tourPostService.findByTypeId(tid.get());
			model.addAttribute("listAllTour", list);
			
		}else if(trid.isPresent()) {//NẾU ID CỦA TRANPORT TỒN TẠI
			List<TourPost> list = tourPostService.findByTranportId(trid.get());
			model.addAttribute("listAllTour", list);
		}
		else {//NGƯỢC LẠI THÌ ĐỔ HẾT DỮ LIỆU
			List<TourPost> list = tourPostService.findByTourStatus();
			model.addAttribute("listAllTour", list);
		}

		return "index/tourTieuBieu";
	}
	
	//TÌM KIẾM TOUR
	@RequestMapping("/search/tour")
	public String listSearch(Model model, @RequestParam("keyword") String keyword) {
		
			List<TourPost> list = tourPostService.SearchTour(keyword);
			model.addAttribute("listAllTour", list);
		
		return "index/tourTieuBieu";
	}
	
	//LỌC GIÁ TỪ CAO ĐẾN THẤP
	@RequestMapping("/filter/price/high")
	public String filterPriceHigh(Model model) {
		
		List<TourPost> list = tourPostService.filerPriceHigh();
		model.addAttribute("listAllTour", list);
		
		return "index/tourTieuBieu";
	}
	
	//LỌC GIÁ TỪ THẤP ĐẾN CAO
	@RequestMapping("/filter/price/low")
	public String filterPriceLow(Model model) {
		
		List<TourPost> list = tourPostService.filerPriceLow();
		model.addAttribute("listAllTour", list);
		
		return "index/tourTieuBieu";
	}
	
	//LỌC KHOẢNG GIÁ
	@RequestMapping("/filter/between_price")
	public String filterBetweenPrice(Model model,
			@RequestParam(required = false, name = "priceMin") Double priceMin,
			@RequestParam(required = false, name = "priceMax") Double priceMax) {
		
		if(null == priceMax || priceMax == 0.0) {
			priceMax = priceMax.MAX_VALUE;
			List<TourPost> list = tourPostService.filerBetweenPrice(priceMin, priceMax);
			model.addAttribute("listAllTour", list);
		}else if(null == priceMin || priceMin == 0.0) {
			priceMin = priceMin.MIN_VALUE;
			List<TourPost> list = tourPostService.filerBetweenPrice(priceMin, priceMax);
			model.addAttribute("listAllTour", list);
		} else {
			List<TourPost> list = tourPostService.filerBetweenPrice(priceMin, priceMax);
			model.addAttribute("listAllTour", list);
		}
		return "index/tourTieuBieu";
	}
	
	//XEM CHI TIẾT TOUR
	@RequestMapping("/tour/detail/{tourId}")
	public String detail(Model model, @PathVariable("tourId") String id) {
		
		TourPost item = tourPostService.findById(id);
		model.addAttribute("itemTour", item);
		
		List<RatingTour> rate = ratingTourService.findAllCommentWithTour(id);
		model.addAttribute("listComment", rate);
		return "tour/detail";
	}
	
}