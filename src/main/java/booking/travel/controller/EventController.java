package booking.travel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.travel.entity.EventPost;
import booking.travel.entity.RatingEvent;
import booking.travel.service.EventPostService;
import booking.travel.service.RatingEventService;

@Controller
public class EventController {

	@Autowired
	EventPostService eventPostService;
	
	@Autowired
	RatingEventService ratingEventService;
	
	//ĐỔ TẤT CẢ CÁC EVENT
		@RequestMapping("/event/list")
		public String list(Model model,
				@RequestParam("ridE") Optional<String> ridE,
				@RequestParam("ev") Optional<String > ev,
				@RequestParam("tridE") Optional<String> tridE
				) {
			
			if(ridE.isPresent()) {//NẾU ID CỦA REGION VÙNG CỦA EVENT TỒN TẠI
				List<EventPost> list = eventPostService.findByRegion(ridE.get());
				model.addAttribute("listAllEvent", list);
				
			}else if(ev.isPresent()) {
				List<EventPost> list = eventPostService.findByEventId(ev.get());
				model.addAttribute("listAllEvent", list);
				
			}else if(tridE.isPresent()) {//NẾU ID CỦA TRANPORT TỒN TẠI
				List<EventPost> list = eventPostService.findByTranportId(tridE.get());
				model.addAttribute("listAllEvent", list);
			}
			else {//NGƯỢC LẠI THÌ ĐỔ HẾT DỮ LIỆU
				List<EventPost> list = eventPostService.findByEventStatus();
				model.addAttribute("listAllEvent", list);
			}

			return "index/EventProduct";
		}
		
		//LỌC GIÁ TỪ CAO ĐẾN THẤP
		@RequestMapping("/filter/event/price/high")
		public String filterPriceHigh(Model model) {
			
			List<EventPost> list = eventPostService.filerPriceHigh();
			model.addAttribute("listAllEvent", list);
			
			return "index/EventProduct";
		}
		
		//LỌC GIÁ TỪ THẤP ĐẾN CAO
		@RequestMapping("/filter/event/price/low")
		public String filterPriceLow(Model model) {
			
			List<EventPost> list = eventPostService.filerPriceLow();
			model.addAttribute("listAllEvent", list);
			
			return "index/EventProduct";
		}
		
		//LỌC KHOẢNG GIÁ
		@RequestMapping("/filter/event/between_price")
		public String filterBetweenPrice(Model model,
				@RequestParam(required = false, name = "priceMinE") Double priceMin,
				@RequestParam(required = false, name = "priceMaxE") Double priceMax) {
			
			if(null == priceMax || priceMax == 0.0) {
				priceMax = priceMax.MAX_VALUE;
				List<EventPost> list = eventPostService.filerBetweenPrice(priceMin, priceMax);
				model.addAttribute("listAllEvent", list);
			}else if(null == priceMin || priceMin == 0.0) {
				priceMin = priceMin.MIN_VALUE;
				List<EventPost> list = eventPostService.filerBetweenPrice(priceMin, priceMax);
				model.addAttribute("listAllEvent", list);
			} else {
				List<EventPost> list = eventPostService.filerBetweenPrice(priceMin, priceMax);
				model.addAttribute("listAllEvent", list);
			}
			return "index/EventProduct";
		}
		
		//XEM CHI TIẾT EVENT
		@RequestMapping("/event/detail/{eventPostId}")
		public String detail(Model model, @PathVariable("eventPostId") String eventPostId) {
			
			EventPost item = eventPostService.findById(eventPostId);
			model.addAttribute("itemEvent", item);
			
			List<RatingEvent> rate = ratingEventService.findAllCommentWithEvent(eventPostId);
			model.addAttribute("listComment", rate);
			return "event/detail";
		}
}
