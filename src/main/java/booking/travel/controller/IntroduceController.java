package booking.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroduceController {
	//Buổi ăn đặt sắt
	@RequestMapping("/introduce1")
	public String index() {
		return "Introduce/introduce1";
	}
	
	//Metting - Hội Nghị
	@RequestMapping("/introduce2")
	public String metting() {
		return "Introduce/metting";
	}
	
	//Event - Sự Kiện
	@RequestMapping("/introduce3")
	public String event() {
		return "Introduce/eventSK";
	}
	
	// Team building
	@RequestMapping("/introduce4")
	public String building() {
		return "Introduce/building";
	}
}
