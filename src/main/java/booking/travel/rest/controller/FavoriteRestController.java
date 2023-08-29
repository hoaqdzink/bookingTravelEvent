package booking.travel.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import booking.travel.entity.EventPost;
import booking.travel.entity.TourPost;
import booking.travel.service.EventPostService;
import booking.travel.service.TourPostService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorite/")
public class FavoriteRestController {
	@Autowired
	EventPostService eventPostService;
	
	@Autowired
	TourPostService tourPostService;
	
	@GetMapping("event/{eventPostId}")
	public EventPost getOneEvent(@PathVariable("eventPostId") String eventPostId) {
		return eventPostService.findById(eventPostId);
	}
	
	@GetMapping("tour/{tourId}")
	public TourPost getOneTour(@PathVariable("tourId") String tourId) {
		return tourPostService.findById(tourId);
	}

}
