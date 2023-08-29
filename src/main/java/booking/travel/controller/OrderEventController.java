package booking.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import booking.travel.entity.OrderEvent;
import booking.travel.entity.RatingEvent;
import booking.travel.service.OrderEventService;
import booking.travel.service.RatingEventService;

@Controller
public class OrderEventController {
	
	@Autowired
	OrderEventService orderEventService;
	
	@Autowired
	RatingEventService ratingEventService;
	
	@RequestMapping("/order/event/{id}")
	public String order() {
		return "order-event/thanhToanEvent";
	}
	
	////ĐỔ LÊN CÁC EVENT ĐANG CHỜ DUYỆT
	@RequestMapping("/profile/profile_order_event_status")
	public String orderEventStatus(Model model, HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		model.addAttribute("orderEventListWait", orderEventService.findAllByUsernameWithStatusWait(username));
		
		return "Profile/_OrderStatusEvent";
	}
	
	////LẤY ĐƠN THEO ID VÀ RETURN CHUYỂN SANG HÀM HỦY ĐƠN
	@GetMapping("/huy_don_event/{orderEventId}")
	public String getDon(@PathVariable("orderEventId") String orderEventId) {
		
		OrderEvent order = orderEventService.findById(orderEventId);
		
		return huyDon(order);
	}
	
	///TIẾN HÀNH HỦY ĐƠN
	@PostMapping("/huy_don_event")
	public String huyDon(OrderEvent orderEvent) {
		
		String huy = "HUY";
		orderEvent.setStatus(huy);
		orderEventService.save(orderEvent);
		
		return "redirect:/profile/profile_order_event_status";
	}
	
	///ĐỔ LÊN CÁC EVENT ĐÃ ĐẶT HOÀN TẤT
	@RequestMapping("/profile/profile_order_event_history")
	public String orderHistory(Model model, HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		model.addAttribute("orderEventListFinal", orderEventService.findAllByUsernameWithStatusFinal(username));
		return "Profile/_OrderHistoryEvent";
	}
	
	
	
	////HÀM ĐÁNH GIÁ EVENT
	@PostMapping("/rating/event")
	public String saveComment(Model model, RatingEvent eventRate,
			HttpServletRequest request) {
		
		String stars = request.getParameter("rating");
		if(stars == null){
			stars = "0.0";
		}
		eventRate.setOrderEvent(new OrderEvent(request.getParameter("layIdEvent").trim()));
		eventRate.setComment(request.getParameter("comment").trim());
		eventRate.setNumStars(Double.parseDouble(stars));
		ratingEventService.save(eventRate);
		
		return "redirect:/profile/profile_order_event_history";
	}
}
