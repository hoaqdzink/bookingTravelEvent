package booking.travel.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import booking.travel.entity.EventPost;
import booking.travel.entity.OrderEvent;
import booking.travel.entity.OrderTour;
import booking.travel.entity.TourPost;
import booking.travel.service.EventPostService;
import booking.travel.service.OrderEventService;
import booking.travel.service.OrderTourService;
import booking.travel.service.TourPostService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order/")
public class OrderRestController {
	@Autowired
	TourPostService tourPostService;
	
	@Autowired
	OrderTourService orderTourService;
	
	@Autowired
	EventPostService eventPostService;
	
	@Autowired
	OrderEventService orderEventService;
	
	@GetMapping("tour/{id}")
	public TourPost getTour(@PathVariable("id") String id) {
		return tourPostService.findById(id);
	}
	
	@PostMapping("tour")
	public OrderTour createTour(@RequestBody OrderTour order) {
		return orderTourService.create(order);
	}
	
	@GetMapping("event/{id}")
	public EventPost getEvent(@PathVariable("id") String id) {
		return eventPostService.findById(id);
	}
	
	@PostMapping("event")
	public OrderEvent createEvent(@RequestBody OrderEvent order) {
		return orderEventService.create(order);
	}
}
