package booking.travel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import booking.travel.service.AccountService;
import booking.travel.service.EventService;
import booking.travel.service.RatingTourService;
import booking.travel.service.RegionService;
import booking.travel.service.TranportService;
import booking.travel.service.TypeService;

@Component
public class GlobalInterceptor implements HandlerInterceptor{

	@Autowired
	RegionService regionService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	TranportService tranportService;
	
	@Autowired
	AccountService accountService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//LOAD VÙNG LÊN
		request.setAttribute("regions", regionService.findAll());
		
		//LOAD LOẠI
		request.setAttribute("types", typeService.findAll());
		
		///LOAD EVENT
		request.setAttribute("event", eventService.findAll());
		
		//LOAD PHƯƠNG TIỆN
		request.setAttribute("tranports", tranportService.findAll());

		//LOAD USER CHI TIẾT SAU KHI ĐĂNG NHẬP
		request.setAttribute("userAll", accountService.findByUsernameOrEmail(request.getRemoteUser()));
	}
}
