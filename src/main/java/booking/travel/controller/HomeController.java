package booking.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import booking.travel.entity.EventPost;
import booking.travel.entity.TourPost;
import booking.travel.service.EventPostService;
import booking.travel.service.TourPostService;

@Controller
public class HomeController {

	@Autowired
	TourPostService tourPostService;
	
	@Autowired
	EventPostService eventPostService;
	
	//ĐỔ LÊN NHỮNG TOUR ĐƯỢC ĐẨY BÀI
	@RequestMapping({"/","/index"})
	public String home(Model model) {
		List<TourPost> list = tourPostService.findByTopPost();
		model.addAttribute("listTourTop", list);
		
		List<EventPost> lists = eventPostService.findByEventTop();
		model.addAttribute("listEventTop", lists);
		return "index/Home";
	}
	
	
}
