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
import booking.travel.entity.OrderTour;
import booking.travel.service.OrderTourService;
import booking.travel.utils.SendMailService;

@Controller
@RequestMapping("/enterprise/")
public class OrderTourEnterpriseController {

	@Autowired
	OrderTourService orderTourService;
	
//	@Autowired
//	OrderTourDetailService orderTourDetailService;
	
	@Autowired
	SendMailService sendMailService;
	
	///ĐỔ CÁC ĐƠN ORDER
	@RequestMapping("order")
	public String enterpriseOrderList(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		//CHỜ DUYỆT
		List<OrderTour> list = orderTourService.findOrderByWait(username);
		model.addAttribute("listOrderTourWait", list);
		
		//ĐÃ DUYỆT HOẶC HỦY
		List<OrderTour> lists = orderTourService.findOrderByDuyet(username);
		model.addAttribute("listOrderTourDuyet", lists);
		
		return "enterprise/order/_donDatTour";
	}
	
	///XEM CHI TIẾT ĐƠN ĐÃ DUYỆT HOẶC HỦY
	@GetMapping("order_edit_approve/{orderTourDetailId}")
	public String enterpriseOrderEditApprove(Model model, @PathVariable("orderTourDetailId") String orderTourDetailId) {
		
		OrderTour order = orderTourService.findById(orderTourDetailId);
		model.addAttribute("orderDetail", order);
		
		return "enterprise/order/_detailDonTourApprove";
	}
	
	//XEM CHI TIẾT ĐƠN ĐANG CHỜ
	@GetMapping("order_edit_Wait/{orderTourDetailId}")
	public String enterpriseOrderEdit(Model model, @PathVariable("orderTourDetailId") String orderTourDetailId) {
		
		OrderTour order = orderTourService.findById(orderTourDetailId);
		model.addAttribute("orderDetail", order);
		
		return "enterprise/order/_detailDonTourWait";
	}
	
	
	///HÀM DUYỆT ĐƠN
	@PostMapping("wait_don_tour")
	public String enterpriseWaitDon(OrderTour orderTour, HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String emailEnterprise = request.getParameter("emailEnterprise");
		String nameEnterprise = request.getParameter("fullname");
		String nduyet = request.getParameter("nutDuyet");
		String orderTourId = request.getParameter("orderTourId");
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
				orderTour.setAccount(new Account(username));
				orderTour.setStatus(duyet);
				orderTourService.save(orderTour);
				sendMailService.sendMailOrderTourDuyet(email, emailEnterprise, nameEnterprise, orderTourId, 
						sumPrice, slNguoiLon, slTreEm, slEmBe, discount, payment);
			}else {
				String huy = "HUY";
				orderTour.setAccount(new Account(username));
				orderTour.setStatus(huy);
				orderTourService.save(orderTour);
				sendMailService.sendMailOrderTourHuy(email, emailEnterprise, nameEnterprise, orderTourId,
						sumPrice, slNguoiLon, slTreEm, slEmBe, discount, payment);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/enterprise/order";
	}
	
	///TÌM KIẾM ĐƠN TOUR
	@GetMapping("search_order_tour")
	public String searchOrderTour(Model model, HttpServletRequest request,
			@RequestParam("keyword") String keyword) {

		if(keyword.isEmpty()) {
			return "redirect:/enterprise/order";
		}
		String username = request.getRemoteUser();
		List<OrderTour> list = orderTourService.serchOrderTour(username, keyword);
		model.addAttribute("listOrderTourDuyet", list);
		
		return "enterprise/order/_donDatTour";
	}

}
