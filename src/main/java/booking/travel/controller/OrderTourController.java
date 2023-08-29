package booking.travel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import booking.travel.entity.OrderTour;
import booking.travel.entity.RatingTour;
import booking.travel.service.OrderTourService;
import booking.travel.service.RatingTourService;

@Controller
public class OrderTourController {
	
	@Autowired
	RatingTourService ratingTourService;
	
	@Autowired
	OrderTourService orderTourService;

	@RequestMapping("/order/tour/{id}")
	public String order() {
		return "order-tour/thanhToanTour";
	}
	
	////ĐỔ LÊN CÁC TOUR/ EVENT ĐANG CHỜ DUYỆT
	@RequestMapping("/profile/profile_order_tour_status")
	public String orderTourStatus(Model model,
			HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		model.addAttribute("orderListWait", orderTourService.findAllByUsernameWithStatusWait(username));
		
		return "Profile/_OrderStatusTour";
	}
	
	////LẤY ĐƠN THEO ID VÀ RETURN CHUYỂN SANG HÀM HỦY ĐƠN
	@GetMapping("/huy_don_tour/{orderTourId}")
	public String getDonTour(@PathVariable("orderTourId") String orderTourId) {
		
		OrderTour order = orderTourService.findById(orderTourId);
		
		return huyDonTour(order);
	}
	
	///TIẾN HÀNH HỦY ĐƠN
	@PostMapping("/huy_don_tour")
	public String huyDonTour(OrderTour orderTour) {
		
		String huy = "HUY";
		orderTour.setStatus(huy);
		orderTourService.save(orderTour);
		
		return "redirect:/profile/profile_order_tour_status";
	}
	
	///ĐỔ LÊN CÁC TOUR HOẶC EVENT ĐÃ ĐẶT HOÀN TẤT
	@RequestMapping("/profile/profile_order_tour_history")
	public String orderHistoryTour(Model model,
			HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		model.addAttribute("orderListFinal", orderTourService.findAllByUsernameWithStatusFinal(username));
		return "Profile/_OrderHistoryTour";
	}
	
	////HÀM ĐÁNH GIÁ TOUR 
	@PostMapping("/rating/tour")
	public String saveCommentTour(Model model, RatingTour rateTour,
			HttpServletRequest request) {
		
		String stars = request.getParameter("rating");
		if(stars == null) {stars = "0.0";}
		rateTour.setOrderTour(new OrderTour(request.getParameter("layId")));
		rateTour.setComment(request.getParameter("comment"));
		rateTour.setNumStars(Double.parseDouble(stars));
		ratingTourService.save(rateTour);
		
		return "redirect:/profile/profile_order_tour_history";
	}
	
	
}
