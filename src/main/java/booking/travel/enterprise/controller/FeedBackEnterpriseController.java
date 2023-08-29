package booking.travel.enterprise.controller;

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

import booking.travel.entity.OrderEvent;
import booking.travel.entity.OrderTour;
import booking.travel.entity.RatingEvent;
import booking.travel.entity.RatingTour;
import booking.travel.service.RatingEventService;
import booking.travel.service.RatingTourService;
import booking.travel.utils.SendMailService;

@Controller
@RequestMapping("/enterprise/")
public class FeedBackEnterpriseController {

	@Autowired
	RatingTourService ratingTourService;
	
	@Autowired
	RatingEventService ratingEventService;
	
	@Autowired
	SendMailService sendMailService;
	
	@GetMapping("list_feedback")
	public String listFeebBackTour(Model model, HttpServletRequest request) {
		
		String username = request.getRemoteUser();
		
		List<RatingTour> list = ratingTourService.findAllCommentTourWithUsername(username);
		model.addAttribute("listTourComment", list);
		
		List<RatingEvent> lists = ratingEventService.findAllCommentEventWithUsername(username);
		model.addAttribute("listEventComment", lists);
		
		return "enterprise/feedback/_listFeedback";
	}
	
	@GetMapping("detail_feedback_tour/{ratingTourId}")
	public String detailFeebBackTour(Model model, @PathVariable("ratingTourId") String ratingTourId) {
		
		RatingTour rate = ratingTourService.findById(ratingTourId);
		model.addAttribute("rateTour", rate);
		
		return "enterprise/feedback/_detailFeedbackTour";
	}
	
	@GetMapping("detail_feedback_event/{ratingEventId}")
	public String detailFeebBackEvent(Model model, @PathVariable("ratingEventId") String ratingEventId) {
		
		RatingEvent rate = ratingEventService.findById(ratingEventId);
		model.addAttribute("rateEvent", rate);
		
		return "enterprise/feedback/_detailFeedbackEvent";
	}
	
	@PostMapping("feebdback_tour")
	public String saveFeedbackTour(RatingTour ratingTour, HttpServletRequest request) {
		String emailUser = request.getParameter("emailRate");
		String emailEnterprise = request.getParameter("emailEnterprise");
		String replyUser = request.getParameter("replyUser").trim();
		
		try {
			ratingTour.setRatingTourId(request.getParameter("idRatingTour").trim());
			ratingTour.setComment(request.getParameter("comment").trim());
			ratingTour.setNumStars(Double.parseDouble(request.getParameter("numstars")));
			ratingTour.setOrderTour(new OrderTour(request.getParameter("orderTourId").trim()));
			ratingTour.setReply(replyUser);
			ratingTour.setStatus(true);
			ratingTourService.save(ratingTour);
			sendMailService.sendMailReplyUser(emailUser, emailEnterprise, replyUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/enterprise/list_feedback";
	}
	
	@PostMapping("feebdback_event")
	public String saveFeedbackEvent(RatingEvent ratingEvent, HttpServletRequest request) {
		String emailUser = request.getParameter("emailRateEvent");
		String emailEnterprise = request.getParameter("emailEnterpriseEvent");
		String replyUser = request.getParameter("replyUserEvent").trim();
		
		try {
			ratingEvent.setRatingEventId(request.getParameter("idRatingEvent").trim());
			ratingEvent.setComment(request.getParameter("commentEvent").trim());
			ratingEvent.setNumStars(Double.parseDouble(request.getParameter("numstarsEvent")));
			ratingEvent.setOrderEvent(new OrderEvent(request.getParameter("orderEventId").trim()));
			ratingEvent.setReply(replyUser);
			ratingEvent.setStatus(true);
			ratingEventService.save(ratingEvent);
			sendMailService.sendMailReplyUser(emailUser, emailEnterprise, replyUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/enterprise/list_feedback";
	}
	
	@GetMapping("search_feedback_tour")
	public String searchFeedbackTour(Model model,
			@RequestParam("keyword") String keyword, HttpServletRequest request) {
		if(keyword.isEmpty()) {
			return "redirect:/enterprise/list_feedback";
		}
		String username = request.getRemoteUser();
		List<RatingTour> list = ratingTourService.searchFeedbackTour(username, keyword);
		model.addAttribute("listTourComment", list);
		
		return "enterprise/feedback/_listFeedback";
	}
}
