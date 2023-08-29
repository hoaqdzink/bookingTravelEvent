package booking.travel.enterprise.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booking.travel.entity.Account;
import booking.travel.entity.OrderEvent;
import booking.travel.service.OrderEventService;
import booking.travel.utils.SendMailService;

@Controller
@RequestMapping("/enterprise/")
public class OrderEventEnterpriseController {
	
	@Autowired
	OrderEventService orderEventService;
	
	@Autowired
	SendMailService sendMailService;
	
	///ĐỔ CÁC ĐƠN ORDER
	@RequestMapping("order_event")
	public String enterpriseOrderList(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		//CHỜ DUYỆT
		List<OrderEvent> list = orderEventService.findOrderEventByWait(username);
		model.addAttribute("listOrderEventWait", list);
		
		//ĐÃ DUYỆT HOẶC HỦY
		List<OrderEvent> lists = orderEventService.findOrderEventByDuyet(username);
		model.addAttribute("listOrderEventDuyet", lists);
		
		return "enterprise/order/_donDatEvent";
	}
	
	///XEM CHI TIẾT ĐƠN ĐÃ DUYỆT HOẶC HỦY
	@GetMapping("order_event_edit_approve/{orderEventDetailId}")
	public String enterpriseOrderEditApprove(Model model, @PathVariable("orderEventDetailId") String orderEventDetailId) {
		
		OrderEvent orderEvent = orderEventService.findById(orderEventDetailId);
		model.addAttribute("orderDetail", orderEvent);
		
		return "enterprise/order/_detailDonEventApprove";
	}
	
	//XEM CHI TIẾT ĐƠN ĐANG CHỜ
	@GetMapping("order_event_edit_Wait/{orderEventId}")
	public String enterpriseOrderEdit(Model model, @PathVariable("orderEventId") String orderEventId) {
		
		OrderEvent orderEvent = orderEventService.findById(orderEventId);
		model.addAttribute("orderEvent", orderEvent);
		
		return "enterprise/order/_detailDonEventWait";
	}
	
	///HÀM DUYỆT ĐƠN
	@PostMapping("wait_don_event")
	public String enterpriseWaitDon(OrderEvent orderEvent, HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String emailEnterprise = request.getParameter("emailEnterprise");
		String nameEnterprise = request.getParameter("fullname");
		String nduyet = request.getParameter("nutDuyet");
		String orderEventId = request.getParameter("orderEventId");
		String username = request.getParameter("username");
		String sumPrice = new DecimalFormat("#,##0.0").format(Double.parseDouble(request.getParameter("sumPrice")));
		String slNguoiLon = request.getParameter("quantityAdult");
		String slTreEm = request.getParameter("quantityChildren");
		String slEmBe = request.getParameter("quantityBaby");
		String discount = request.getParameter("discount");
		String payment = request.getParameter("payment");
		
		try {
			
			if(nduyet != null) {
				String duyet = "DUYET";
				orderEvent.setAccount(new Account(username));
				orderEvent.setStatus(duyet);
				orderEventService.save(orderEvent);
				sendMailService.sendMailOrderEventDuyet(email, emailEnterprise, nameEnterprise, orderEventId,
						sumPrice, slNguoiLon, slTreEm, slEmBe, discount, payment);
			}else {
				String huy = "HUY";
				orderEvent.setAccount(new Account(username));
				orderEvent.setStatus(huy);
				orderEventService.save(orderEvent);
				sendMailService.sendMailOrderEventHuy(email, emailEnterprise, nameEnterprise, orderEventId,
						sumPrice, slNguoiLon, slTreEm, slEmBe, discount, payment);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/enterprise/order_event";
	}
	
	///TÌM KIẾM ĐƠN EVENT
	@GetMapping("search_order_event")
	public String searchOrderEvent(Model model, HttpServletRequest request,
			@RequestParam("keyword") String keyword) {

		if(keyword.isEmpty()) {
			return "redirect:/enterprise/order_event";
		}
		String username = request.getRemoteUser();
		List<OrderEvent> list = orderEventService.serchOrderEvent(username, keyword);
		model.addAttribute("listOrderEventDuyet", list);
		
		return "enterprise/order/_donDatEvent";
	}
}
